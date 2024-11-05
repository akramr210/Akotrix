package com.app.akotrix.create_folder

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.R
import com.app.akotrix.position_change.NameImageModel
import com.app.akotrix.selection.SelectionActivity
import com.app.akotrix.utils.Constants
import com.app.akotrix.utils.SharedPreference


class CreateFolderActivity : ComponentActivity() {

    var arrModel: ArrayList<NameImageModel>? = arrayListOf()
    var arrSelected: ArrayList<NameImageModel> = arrayListOf()
    lateinit var createFolderAdapter: CreateFolderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_folder)

        val rv = findViewById<RecyclerView>(R.id.rvImage)
        val edt = findViewById<EditText>(R.id.edt)
        val cardCreate = findViewById<CardView>(R.id.cardCreate)

        cardCreate.setOnClickListener {
            if (edt.text.toString() != "") {
                val listFolder = SharedPreference.getSharedPrefFolder(this, Constants.FOLDER_SP) ?: arrayListOf()
                val folderModel = FolderModel(edt.text.toString(), arrSelected)
                listFolder.add(folderModel)
                SharedPreference.putSharedPrefFolder(this,Constants.FOLDER_SP,listFolder)
                Toast.makeText(this, "Created", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,SelectionActivity::class.java))
                finishAffinity()
            } else
                Toast.makeText(this, "Please enter Name", Toast.LENGTH_LONG).show()
        }

        arrModel = SharedPreference.getSharedPreffObject(this, "data")
        arrModel?.let { arr ->
            createFolderAdapter = CreateFolderAdapter(arr) { currentPos ->
                val element = arr[currentPos]
                element.isSelected?.let {
                    if (it) {
                        arrSelected.removeIf { it.name == element.name }
                        /* for((index,value) in arrSelected.withIndex()){
                             if(value.name == element.name){
                                 arrSelected.removeAt(index)
                                 break
                             }
                         }*/
                    } else {
                        arrSelected.add(element)
                    }
                    element.isSelected = !it
                } ?: run {
                    element.isSelected = true
                    arrSelected.add(element)
                }

                arr[currentPos] = element
                createFolderAdapter.notifyDataSetChanged()
            }
            val linearLayoutManager = GridLayoutManager(this, 6)
            rv.layoutManager = linearLayoutManager
            rv.adapter = createFolderAdapter
        }
    }
}