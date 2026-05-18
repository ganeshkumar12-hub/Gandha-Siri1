package com.example.gandhasiri

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TreesFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        view?.let { loadTrees(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_trees, container, false)
        loadTrees(view)
        return view
    }

    private fun loadTrees(view: View) {
        val trees = AppDatabase.getInstance(requireContext()).treeDao().getAllTrees()
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTrees)
        recycler.layoutManager = LinearLayoutManager(context)

        if (trees.isEmpty()) {
            // Show empty message
            recycler.visibility = View.GONE
            view.findViewById<TextView>(R.id.tvEmptyMessage).visibility = View.VISIBLE
        } else {
            recycler.visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.tvEmptyMessage).visibility = View.GONE
            recycler.adapter = TreeAdapter(trees)
        }
    }
}