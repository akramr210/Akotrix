package com.app.akotrix

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity

class SelectionActivity : ComponentActivity() {
    var imgArr: MutableList<Int> = mutableListOf(
        R.drawable.sertavel_1,
        R.drawable.acehelp_3,
        R.drawable.darohelp_4,
        R.drawable.darohelp_z_5,
        R.drawable.rapidak_6,
        R.drawable.nutrihelp_7,
        R.drawable.takan_8,
        R.drawable.largisure_9,
        R.drawable.uretone_10,
        R.drawable.nutrijump_11,
        R.drawable.nurodium_12,
        R.drawable.zetexyl_dx_20,
        R.drawable.uti_zet_13,
        R.drawable.velcum_14,
        R.drawable.zetacool_15,
        R.drawable.nutrihelp_lc_16,
        R.drawable.nurodium_d_17,
        R.drawable.nurodium_ptm_18,
        R.drawable.s_citaclon_19,
        R.drawable.zetexyl_dx_20,
        R.drawable.zetexyl_lx_21,
        R.drawable.constiihelp_22,
        R.drawable.hyzine_25_23,
        R.drawable.kt_zet_24,
        R.drawable.zeta_fresh_25,
        R.drawable.zeta_fresh_ez_26,
        R.drawable.zeta_fresh_sb_27,
        R.drawable.platecure_28,
        R.drawable.zetaca_thankyou_29
    )

    var strNames = mutableListOf(
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
        "Zetacool Gel",
        "NUTRIHELP-LC",
        "NURODIUM-D",
        "Nurodium-PMT",
        "S-Citaclon",
        "ZETEXYL-DX",
        "Zetexyl-LX",
        "CONSTIHELP",
        "HYZINE-25",
        "KT-ZET",
        "Zetafresh",
        "Zetafresh-EZ",
        "Zetafresh-SB",
        "Platecure",
        "Zetaca Thankyou"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        val imgView = findViewById<ImageView>(R.id.imgView)
        val imgChange = findViewById<ImageView>(R.id.imgChange)

        val arr = SharedPreference.getSharedPreffObject(this, "data")
        if (arr.isNullOrEmpty())
            loadData()
        else if (arr.size != imgArr.size && imgArr.size > arr.size) {

        }

        imgView.setOnClickListener {
            val intent = Intent(this@SelectionActivity, MainActivity::class.java)
            startActivity(intent)
        }

        imgChange.setOnClickListener {
            val intent = Intent(this@SelectionActivity, ImagePositionModifyActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val nameImageArr: ArrayList<NameImageModel> = arrayListOf()
        for ((index, data) in imgArr.withIndex()) {
            val nameImageModel = NameImageModel(strNames[index], data)
            nameImageArr.add(nameImageModel)
        }
        SharedPreference.putSharedPrefObject(this, "data", nameImageArr)
    }
}