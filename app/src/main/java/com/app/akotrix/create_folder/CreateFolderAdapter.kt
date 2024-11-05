package com.app.akotrix.create_folder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.R
import com.app.akotrix.position_change.NameImageModel
import com.bumptech.glide.Glide

class CreateFolderAdapter(val name: List<NameImageModel>, val callback: (Int) -> Unit) :
    RecyclerView.Adapter<CreateFolderAdapter.NameViewHolder>() {

    lateinit var context: Context

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val card = itemView.findViewById<CardView>(R.id.card)
        val nameTv = itemView.findViewById<TextView>(R.id.tv)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val imgRight = itemView.findViewById<ImageView>(R.id.imgRight)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        context = parent.context
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_create_folder, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return name.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val nameArr = name[position]
        holder.nameTv.text = nameArr.name
        nameArr.isSelected?.let {
            holder.imgRight.visibility = if(it) View.VISIBLE else View.INVISIBLE
        } ?: run {
            holder.imgRight.visibility = View.INVISIBLE
        }

        Glide.with(context)
            .asBitmap()
            .load(nameArr.drawable)
            .into(holder.image)
        holder.card.tag = position
        holder.card.setOnClickListener {
            val pos = it.tag as Int
            callback.invoke(pos)
        }

    }
}