package com.app.akotrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImagePositionModifyActivity : ComponentActivity() {

    var arrModel: ArrayList<NameImageModel>? = arrayListOf()
    lateinit var imageModifyAdapter: ImageModifyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_position_modify)

        val rv = findViewById<RecyclerView>(R.id.rvImage)

        arrModel = SharedPreference.getSharedPreffObject(this, "data")
        arrModel?.let { arr ->
            imageModifyAdapter = ImageModifyAdapter(arr) { currentPos ->
                Util.showDialog(this) { newPos ->
                    val element = arr.removeAt(currentPos)
                    if ((newPos - 1) <= arr.size) {
                        arr.add(newPos.minus(1), element)
                    } else {
                        arr.add(element)
                    }
                    imageModifyAdapter.notifyDataSetChanged()
                    SharedPreference.putSharedPrefObject(this, "data", arr)
                }
            }
            val linearLayoutManager = GridLayoutManager(this, 6)
            rv.layoutManager = linearLayoutManager
            rv.adapter = imageModifyAdapter
        }


    }
}