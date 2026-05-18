package com.example.gandhasiri

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : Fragment() {

    private var currentGps = "Location not set"
    private var currentPhotoPath = ""
    private lateinit var tvGps: TextView
    private lateinit var imgPreview: ImageView
    private var photoUri: Uri? = null

    // ── Camera launcher ──
    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && currentPhotoPath.isNotEmpty()) {
            // Photo saved! Show preview
            val bitmap = BitmapFactory.decodeFile(currentPhotoPath)
            imgPreview.setImageBitmap(bitmap)
            imgPreview.scaleType = ImageView.ScaleType.CENTER_CROP
            Toast.makeText(context, "✅ Photo saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "❌ Photo not taken", Toast.LENGTH_SHORT).show()
        }
    }

    // ── Camera permission launcher ──
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            openCamera()
        } else {
            Toast.makeText(context, "❌ Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    // ── Location permission launcher ──
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            getLocation()
        } else {
            useSimulatedLocation()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val fusedClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        tvGps = view.findViewById(R.id.tvGpsCoords)
        imgPreview = view.findViewById(R.id.imgTreePreview)
        val etGirth = view.findViewById<EditText>(R.id.etGirth)
        val etAge = view.findViewById<EditText>(R.id.etAge)
        val etNotes = view.findViewById<EditText>(R.id.etNotes)

        // ── Camera Button ──
        view.findViewById<Button>(R.id.btnTakePhoto).setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    openCamera()
                }
                else -> {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        }

        // ── GPS Button ──
        view.findViewById<Button>(R.id.btnGetLocation).setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getLocation()
                }
                else -> {
                    locationPermissionRequest.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
        }

        // ── Register Button ──
        view.findViewById<Button>(R.id.btnRegisterTree).setOnClickListener {
            val girthStr = etGirth.text.toString().trim()
            val ageStr = etAge.text.toString().trim()
            val notes = etNotes.text.toString().trim()

            // Validation
            if (girthStr.isEmpty() || ageStr.isEmpty()) {
                Toast.makeText(context, "⚠️ Please enter girth and age!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (currentGps == "Location not set") {
                Toast.makeText(context, "⚠️ Please get GPS location first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (currentPhotoPath.isEmpty()) {
                Toast.makeText(context, "⚠️ Please take a photo of the tree!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val girth = girthStr.toInt()
            val age = ageStr.toInt()
            val treeId = generateTreeId()
            val health = when {
                girth >= 60 -> "Mature"
                girth >= 30 -> "Growing"
                else        -> "Sapling"
            }
            val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

            val tree = Tree(
                treeId = treeId,
                girth = girth,
                age = age,
                gps = currentGps,
                notes = notes,
                health = health,
                dateAdded = date,
                photoPath = currentPhotoPath  // save photo path
            )

            AppDatabase.getInstance(requireContext()).treeDao().insertTree(tree)

            Toast.makeText(
                context,
                "✅ Tree Registered!\nID: $treeId\nHealth: $health",
                Toast.LENGTH_LONG
            ).show()

            // Reset form
            etGirth.setText("")
            etAge.setText("")
            etNotes.setText("")
            tvGps.text = "📡 Tap button to get location..."
            imgPreview.setImageResource(android.R.color.transparent)
            imgPreview.setBackgroundColor(
                ContextCompat.getColor(requireContext(), R.color.sand_light)
            )
            currentGps = "Location not set"
            currentPhotoPath = ""
        }

        return view
    }

    // ── Open Camera ──
    private fun openCamera() {
        val photoFile = createImageFile()
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.gandhasiri.fileprovider",
            photoFile
        )
        takePictureLauncher.launch(photoUri)
    }

    // ── Create a file to store the photo ──
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TREE_${timeStamp}_", ".jpg", storageDir).also {
            currentPhotoPath = it.absolutePath
        }
    }

    // ── Get GPS ──
    private fun getLocation() {
        tvGps.text = "📡 Getting location..."
        try {
            LocationServices.getFusedLocationProviderClient(requireActivity())
                .getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                ).addOnSuccessListener { location ->
                    if (location != null) {
                        currentGps = "%.4f°N, %.4f°E".format(location.latitude, location.longitude)
                        tvGps.text = "📍 $currentGps"
                        Toast.makeText(context, "✅ Location captured!", Toast.LENGTH_SHORT).show()
                    } else {
                        useSimulatedLocation()
                    }
                }.addOnFailureListener {
                    useSimulatedLocation()
                }
        } catch (e: SecurityException) {
            useSimulatedLocation()
        }
    }

    private fun useSimulatedLocation() {
        currentGps = "12.9716°N, 77.5946°E (simulated)"
        tvGps.text = "📍 $currentGps"
        Toast.makeText(context, "📡 Using simulated location", Toast.LENGTH_SHORT).show()
    }

    private fun generateTreeId(): String {
        val chars = "ABCDEFGHJKLMNPQRSTUVWXYZ"
        val rand = Random()
        val num = rand.nextInt(9000) + 1000
        return "GS-${chars[rand.nextInt(chars.length)]}${chars[rand.nextInt(chars.length)]}-$num"
    }
}