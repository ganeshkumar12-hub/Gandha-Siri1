package com.example.gandhasiri

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class TreeAdapter(private val trees: List<Tree>) :
    RecyclerView.Adapter<TreeAdapter.TreeViewHolder>() {

    inner class TreeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTreeId: TextView = view.findViewById(R.id.tvTreeId)
        val tvDetails: TextView = view.findViewById(R.id.tvTreeDetails)
        val tvHealth: TextView = view.findViewById(R.id.tvTreeHealth)
        val imgTree: ImageView = view.findViewById(R.id.imgTreeThumb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeViewHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_tree, parent, false)
        return TreeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreeViewHolder, position: Int) {
        val tree = trees[position]

        holder.tvTreeId.text = tree.treeId
        holder.tvDetails.text = "📏 ${tree.girth}cm  |  📅 ${tree.age} yrs  |  ${tree.dateAdded}"

        val (emoji, color) = when (tree.health) {
            "Mature"  -> Pair("✅ Mature",  Color.parseColor("#4A7C59"))
            "Growing" -> Pair("🌱 Growing", Color.parseColor("#E67E22"))
            else      -> Pair("🌰 Sapling", Color.parseColor("#C0392B"))
        }
        holder.tvHealth.text = emoji
        holder.tvHealth.setTextColor(color)

        // Show photo if it exists, otherwise show emoji placeholder
        if (tree.photoPath.isNotEmpty() && File(tree.photoPath).exists()) {
            val bitmap = BitmapFactory.decodeFile(tree.photoPath)
            holder.imgTree.setImageBitmap(bitmap)
            holder.imgTree.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            holder.imgTree.setImageResource(android.R.color.transparent)
            holder.imgTree.setBackgroundColor(Color.parseColor("#E8D5A3"))
        }
    }

    override fun getItemCount() = trees.size
}