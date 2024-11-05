package com.app.akotrix.position_change

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.R
import com.app.akotrix.position_change.ImageModifyAdapter.NameViewHolder
import com.bumptech.glide.Glide

class ImageModifyAdapter( val callback: (Int) -> Unit) :
    ListAdapter<NameImageModel, NameViewHolder>(DiffCallback()) {
    lateinit var context: Context
    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val card = itemView.findViewById<CardView>(R.id.card)
        val nameTv = itemView.findViewById<TextView>(R.id.tv)
        val tvNumber = itemView.findViewById<TextView>(R.id.tvNumber)
        val image = itemView.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        context = parent.context
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_image_modify, parent, false)
        return NameViewHolder(view)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val nameArr = getItem(holder.absoluteAdapterPosition)
        holder.nameTv.text = nameArr.name
        holder.tvNumber.text = (holder.absoluteAdapterPosition.plus(1)).toString()

        Glide.with(context)
            .asBitmap()
            .load(nameArr.drawable)
            .into(holder.image)
        holder.card.tag = holder.absoluteAdapterPosition
        holder.card.setOnClickListener {
            val pos = it.tag as Int
            Log.v("akram","pos "+pos +"position "+holder.absoluteAdapterPosition)
            callback.invoke(holder.absoluteAdapterPosition)
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<NameImageModel>() {
        override fun areItemsTheSame(
            oldItem: NameImageModel,
            newItem: NameImageModel
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: NameImageModel,
            newItem: NameImageModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    fun updateList(list: MutableList<NameImageModel>?) {
        list?.let {
            val newList = ArrayList<NameImageModel>(list)
            submitList(newList)
        }
    }

}