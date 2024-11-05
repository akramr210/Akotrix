package com.app.akotrix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(val name: List<NameModel>, val click: (Int) -> Unit) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    lateinit var context: Context

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTv = itemView.findViewById<TextView>(R.id.tv)
        val card = itemView.findViewById<CardView>(R.id.card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        context = parent.context
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_name, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return name.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val nameArr = name[position]
        holder.nameTv.text = nameArr.name
        if (nameArr.isSelected) {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.teal_700))
        } else {
            holder.card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))
        }
        holder.card.tag = position
        holder.card.setOnClickListener {
            val pos = it.tag as Int
            click.invoke(pos)
        }

    }
}