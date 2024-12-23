package com.app.akotrix.selection

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.R
import com.app.akotrix.create_folder.FolderModel
import com.app.akotrix.position_change.NameImageModel

class FolderAdapter(
    val name: ArrayList<FolderModel>,
    val callbackView: (ArrayList<NameImageModel>) -> Unit,
    val callbackChange: (FolderModel,Int) -> Unit,
    val callbackDelete: (Int) -> Unit,
    val callbackAdd: (FolderModel,Int) -> Unit
) :
    RecyclerView.Adapter<FolderAdapter.NameViewHolder>() {

    lateinit var context: Context

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFolder = itemView.findViewById<TextView>(R.id.tvFolder)
        val imgDelete = itemView.findViewById<ImageView>(R.id.imgDelete)
        val imgChange = itemView.findViewById<ImageView>(R.id.imgChange)
        val imgAdd = itemView.findViewById<ImageView>(R.id.imgAdd)
        val relativeView = itemView.findViewById<RelativeLayout>(R.id.relativeView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        context = parent.context
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_show_folder, parent, false)
        return NameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return name.size
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val nameArr = name[position]
        holder.tvFolder.text = nameArr.name
        holder.relativeView.setOnClickListener {
            callbackView.invoke(nameArr.arrFolder)
        }
        holder.imgChange.tag = position
        holder.imgChange.setOnClickListener {
            val pos = it.tag as Int
            callbackChange.invoke(nameArr,pos)
        }
        holder.imgDelete.tag = position
        holder.imgDelete.setOnClickListener {
            val pos = it.tag as Int
           /* name.removeAt(pos)
            notifyDataSetChanged()*/
            callbackDelete.invoke(pos)
        }
        holder.imgAdd.tag = position
        holder.imgAdd.setOnClickListener {
            val pos = it.tag as Int
            callbackAdd.invoke(nameArr,pos)
        }
    }
}