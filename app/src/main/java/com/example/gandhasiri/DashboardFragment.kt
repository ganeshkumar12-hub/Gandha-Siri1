package com.example.gandhasiri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        // onResume fires every time you come back to this screen
        view?.let { updateStats(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        updateStats(view)
        return view
    }

    private fun updateStats(view: View) {
        val db = AppDatabase.getInstance(requireContext())
        val treeCount = db.treeDao().getTreeCount()
        val matureCount = db.treeDao().getMatureCount()

        view.findViewById<TextView>(R.id.tvStatTrees).text = treeCount.toString()
        view.findViewById<TextView>(R.id.tvStatMature).text = matureCount.toString()
    }
}