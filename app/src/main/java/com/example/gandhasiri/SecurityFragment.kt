package com.example.gandhasiri

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecurityFragment : Fragment() {

    private val checkItems = listOf(
        "Install Perimeter Fencing",
        "Solar Lights Installed",
        "CCTV / Camera Trap Set Up",
        "Neighbors Informed",
        "Local Police Informed",
        "GPS Tags on All Trees"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_security, container, false)

        view.findViewById<Button>(R.id.btnPanic).setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("🆘 ALERT SENT!")
                .setMessage(
                    "SMS Alerts sent to:\n\n" +
                            "• Neighbor 1: +91-98XXX-XXXXX ✅\n" +
                            "• Neighbor 2: +91-97XXX-XXXXX ✅\n" +
                            "• Forest Dept: +91-080-XXXX ✅\n\n" +
                            "Your GPS location has been shared. Stay safe!"
                )
                .setPositiveButton("OK, Stay Safe", null)
                .show()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerChecklist)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = ChecklistAdapter(checkItems)

        return view
    }
}