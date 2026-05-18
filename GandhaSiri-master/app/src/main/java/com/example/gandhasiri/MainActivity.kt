package com.example.gandhasiri

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Insert sample data if database is empty
        insertSampleDataIfEmpty()

        loadFragment(DashboardFragment(), "Dashboard")

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> loadFragment(DashboardFragment(), "Dashboard")
                R.id.nav_register  -> loadFragment(RegisterFragment(),  "Register Tree")
                R.id.nav_trees     -> loadFragment(TreesFragment(),     "My Trees")
                R.id.nav_security  -> loadFragment(SecurityFragment(),  "Security Center")
                R.id.nav_legal     -> loadFragment(LegalFragment(),     "Legal Guide")
            }
            true
        }
    }

    private fun insertSampleDataIfEmpty() {
        val dao = AppDatabase.getInstance(this).treeDao()

        // Only insert if no trees exist yet
        if (dao.getTreeCount() == 0) {
            dao.insertTree(Tree(
                treeId = "GS-KA-1001",
                girth = 65,
                age = 18,
                gps = "12.9716°N, 77.5946°E",
                notes = "Old tree near east boundary, very healthy",
                health = "Mature",
                dateAdded = "01/01/2023"
            ))
            dao.insertTree(Tree(
                treeId = "GS-MB-2034",
                girth = 45,
                age = 10,
                gps = "12.9720°N, 77.5950°E",
                notes = "Near north fence",
                health = "Growing",
                dateAdded = "15/03/2023"
            ))
            dao.insertTree(Tree(
                treeId = "GS-PC-3071",
                girth = 18,
                age = 3,
                gps = "12.9710°N, 77.5940°E",
                notes = "New sapling, needs care",
                health = "Sapling",
                dateAdded = "10/06/2024"
            ))
        }
    }

    private fun loadFragment(fragment: Fragment, title: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .commit()
        findViewById<TextView>(R.id.tvTopBarTitle).text = title
    }
}