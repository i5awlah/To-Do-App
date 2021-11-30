package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*


class ToDoAdaptor(private val items: ArrayList<ToDoItem>) : RecyclerView.Adapter<ToDoAdaptor.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.itemView.apply {
            tvItem.text = item.content
            checkBox.isChecked = item.checked
            checkBox.setOnClickListener{
                if (checkBox.isChecked) {
                    tvItem.setTextColor(Color.GRAY)
                } else {
                    tvItem.setTextColor(Color.BLACK)
                }
                item.checked = !item.checked
            }

        }
    }

    override fun getItemCount() = items.size

    fun deleteItems() {
        items.removeAll{ item -> item.checked }
        notifyDataSetChanged()
    }

}