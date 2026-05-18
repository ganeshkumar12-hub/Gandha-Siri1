package com.example.gandhasiri

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ChecklistAdapter(private val items: List<String>) :
    RecyclerView.Adapter<ChecklistAdapter.CheckViewHolder>() {

    private val checked = BooleanArray(items.size)

    inner class CheckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return CheckViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        val prefix = if (checked[position]) "✅ " else "⬜ "
        holder.tvItem.text = "$prefix${items[position]}"
        holder.tvItem.textSize = 14f
        holder.tvItem.setTextColor(Color.parseColor("#5C3A1E"))
        holder.tvItem.setPadding(16, 16, 16, 16)
        holder.itemView.setOnClickListener {
            checked[position] = !checked[position]
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = items.size
}