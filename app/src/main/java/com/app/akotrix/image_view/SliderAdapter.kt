package com.app.akotrix.image_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.app.akotrix.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(val sliderDataArrayList: MutableList<Int>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    override fun getCount(): Int {
        return sliderDataArrayList.size
    }

    lateinit var mContext: Context

    //We are inflating the slider_layout inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        mContext = parent.context
        val inflate: View =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.row_slider, null)
        return SliderAdapterViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        Glide.with(mContext)
            .asBitmap()
            .load(sliderDataArrayList[position])
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }


    inner class SliderAdapterViewHolder(
        var itemView: View
    ) : ViewHolder(itemView) {
        var imageViewBackground: ImageView = itemView.findViewById<ImageView>(R.id.imgSlider)
    }
}
