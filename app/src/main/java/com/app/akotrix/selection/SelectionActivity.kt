package com.app.akotrix.selection

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.akotrix.image_view.ImagesViewActivity
import com.app.akotrix.position_change.NameImageModel
import com.app.akotrix.R
import com.app.akotrix.create_folder.CreateFolderActivity
import com.app.akotrix.create_folder.FolderModel
import com.app.akotrix.utils.SharedPreference
import com.app.akotrix.position_change.ImagePositionModifyActivity
import com.app.akotrix.utils.Constants
import com.app.akotrix.utils.Util

class SelectionActivity : ComponentActivity() {
    var imgArr: MutableList<Int> = mutableListOf(
        R.drawable.img_akotrix_new3,
        R.drawable.sertavel_1,
        R.drawable.img_acehelp_3,
        R.drawable.img_darohelp_4,
        R.drawable.img_darohelp_z_5,
        R.drawable.img_rapidak_6,
        R.drawable.img_nutrihelp_7,
        R.drawable.img_takan_8,
        R.drawable.img_largisure_9,
        R.drawable.uretone_10,
        R.drawable.img_nutrijump_11,
        R.drawable.img_nurodium_12,
        R.drawable.img_zetexyl_dx_20,
        R.drawable.img_uti_zet_13,
        R.drawable.img_velcum_14,
        R.drawable.nutrihelp_lc_16,
        R.drawable.img_nurodium_d_17,
        R.drawable.img_nurodium_ptm_18,
        R.drawable.img_s_citaclon_19,
        R.drawable.img_zetexyl_lx_21,
        R.drawable.img_constiihelp_22,
        R.drawable.img_hyzine_25_23,
        R.drawable.img_kt_zet_24,
        R.drawable.img_zeta_fresh_25,
        R.drawable.img_zeta_fresh_ez_26,
        R.drawable.img_zeta_fresh_sb_27,
        R.drawable.img_platecure_28,
        R.drawable.naprozet,
        R.drawable.nurodium_d,
        R.drawable.akotel_40,
        R.drawable.img_akotel_trio,
        R.drawable.akotel_ln_40,
        R.drawable.img_olenvel,
        R.drawable.img_sertavel,
        R.drawable.img_zetacort_6,
    )

    var strNames = mutableListOf(
        "Akotrix",
        "Sertavel",
        "Acehelp-SP",
        "Darohelp",
        "Darohelp-Z",
        "Rapidak-D",
        "Nutrihelp",
        "Tacan-D3",
        "Largisure",
        "Uretone",
        "NutriJump",
        "Nurodium-XT",
        "ZETEXYL-DX",
        "UTI-ZET",
        "VELCUM",
        "NUTRIHELP-LC",
        "NURODIUM-D",
        "Nurodium-PMT",
        "S-Citaclon",
        "Zetexyl-LX",
        "CONSTIHELP",
        "HYZINE-25",
        "KT-ZET",
        "Zetafresh",
        "Zetafresh-EZ",
        "Zetafresh-SB",
        "Platecure",
        "Naprozet",
        "NURODIUM D",
        "Akotel-40",
        "Akotel-Trio",
        "Akotel-LN 40",
        "Olenvel 2.5",
        "Sertavel 25",
        "Zetacort 6",
    )
    lateinit var folderAdapter : FolderAdapter
    var listFolder : ArrayList<FolderModel> = arrayListOf()
    companion object{
        var isRecall = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        val imgChange = findViewById<ImageView>(R.id.imgChange)
        val imgCreate = findViewById<ImageView>(R.id.imgCreate)
        val rvFolder = findViewById<RecyclerView>(R.id.rvFolder)
        val linearAll = findViewById<LinearLayout>(R.id.linearAll)
        val btn = findViewById<Button>(R.id.btn)

        val arr = SharedPreference.getSharedPreffObject(this, "data")
        if (arr.isNullOrEmpty())
            loadData()
        else if (arr.size != imgArr.size && imgArr.size > arr.size) {
            loadNextData(arr)
        }

        listFolder = SharedPreference.getSharedPrefFolder(this, Constants.FOLDER_SP)?: arrayListOf()

        folderAdapter = FolderAdapter(listFolder,::viewFolder,::changePositionFolder,::deleteFolder,::updateFolder)
        val linearLayoutManager = GridLayoutManager(this, 6)
        rvFolder.layoutManager = linearLayoutManager
        rvFolder.adapter = folderAdapter

        linearAll.setOnClickListener {
            val arr = SharedPreference.getSharedPreffObject(this, "data") ?: arrayListOf()
            gotoViewPage(arr)
        }

        imgChange.setOnClickListener {
            val intent = Intent(this@SelectionActivity, ImagePositionModifyActivity::class.java)
            intent.putExtra("from","main")
            startActivity(intent)
        }
        imgCreate.setOnClickListener {
            val intent = Intent(this@SelectionActivity, CreateFolderActivity::class.java)
            intent.putExtra("from","main")
            startActivity(intent)
        }
        btn.setOnClickListener {
            loadData()
        }
    }

    private fun viewFolder(arrFolder : ArrayList<NameImageModel>){
        gotoViewPage(arrFolder)
    }

    private fun changePositionFolder(arrFolder : FolderModel,pos : Int){
        val intent = Intent(this@SelectionActivity, ImagePositionModifyActivity::class.java)
        intent.putExtra("from","folder")
        intent.putExtra("pos",pos)
        intent.putExtra("data",arrFolder)
        startActivity(intent)
    }

    private fun updateFolder(arrFolder : FolderModel,pos : Int){
        val intent = Intent(this@SelectionActivity, CreateFolderActivity::class.java)
        intent.putExtra("from","folder")
        intent.putExtra("pos",pos)
        intent.putExtra("data",arrFolder)
        startActivity(intent)
    }

    private fun deleteFolder(pos : Int){
        Util.deleteDialog(this){
            listFolder.removeAt(pos)
            folderAdapter.notifyDataSetChanged()
          /*  val listFolder = SharedPreference.getSharedPrefFolder(this, Constants.FOLDER_SP)?: arrayListOf()
            listFolder.removeAt(pos)*/
            SharedPreference.putSharedPrefFolder(this,Constants.FOLDER_SP,listFolder)
        }
    }

    private fun gotoViewPage(arr: java.util.ArrayList<NameImageModel>) {
        val intent = Intent(this@SelectionActivity, ImagesViewActivity::class.java)
        intent.putExtra("data",arr)
        startActivity(intent)
    }

    private fun loadData() {
        val nameImageArr: ArrayList<NameImageModel> = arrayListOf()
        for ((index, data) in imgArr.withIndex()) {
            val nameImageModel = NameImageModel(strNames[index], data)
            nameImageArr.add(nameImageModel)
        }
        SharedPreference.putSharedPrefObject(this, "data", nameImageArr)
    }

    private fun loadNextData(arr: ArrayList<NameImageModel>) {
        for(i in arr.size..imgArr.size.minus(1)){
            val nameImageModel = NameImageModel(strNames[i], imgArr[i])
            arr.add(nameImageModel)
        }
        SharedPreference.putSharedPrefObject(this, "data", arr)
    }

    override fun onResume() {
        super.onResume()
        if(isRecall){
            isRecall = false
            recreate()
        }
    }
}