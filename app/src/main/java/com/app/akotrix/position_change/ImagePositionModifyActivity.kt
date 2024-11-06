package com.app.akotrix.position_change

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.R
import com.app.akotrix.create_folder.FolderModel
import com.app.akotrix.selection.SelectionActivity
import com.app.akotrix.utils.Constants
import com.app.akotrix.utils.SharedPreference
import com.app.akotrix.utils.Util
import com.app.akotrix.utils.Util.serializable

class ImagePositionModifyActivity : ComponentActivity() {

    var arrModel: ArrayList<NameImageModel>? = arrayListOf()
    lateinit var imageModifyAdapter: ImageModifyAdapter
    var folderModel: FolderModel? = null
    var folderPos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_position_modify)

        val rv = findViewById<RecyclerView>(R.id.rvImage)
        val from = intent.getStringExtra("from")
        if (from == "main")
            arrModel = SharedPreference.getSharedPreffObject(this, "data")
        else {
            folderModel = intent.serializable<FolderModel>("data")
            arrModel = folderModel?.arrFolder
            folderPos = intent.getIntExtra("pos",0)
        }

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
                    if (from == "main")
                        SharedPreference.putSharedPrefObject(this, "data", arr)
                    else {
                        SelectionActivity.isRecall = true
                        val listFolder = SharedPreference.getSharedPrefFolder(this, Constants.FOLDER_SP)?: arrayListOf()
                        val folderModelTemp = FolderModel(folderModel?.name?:"",arr)
                        listFolder.set(folderPos,folderModelTemp)
                        SharedPreference.putSharedPrefFolder(this,Constants.FOLDER_SP,listFolder)
                    }
                }
            }
            val linearLayoutManager = GridLayoutManager(this, 6)
            rv.layoutManager = linearLayoutManager
            rv.adapter = imageModifyAdapter
        }
    }
}