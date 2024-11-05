package com.app.akotrix

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smarteist.autoimageslider.SliderPager
import com.smarteist.autoimageslider.SliderView

class MainActivity : ComponentActivity() {
    var imgArr: MutableList<Int> = mutableListOf()

    var strNames: MutableList<String> = mutableListOf()

    val modelList = mutableListOf<NameModel>()
    lateinit var nameAdapter: NameAdapter
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main)

        val sliderView = findViewById<SliderView>(R.id.sliderDashBoard)
        rv = findViewById<RecyclerView>(R.id.rv)
        getData()
        val adapter =
            SliderAdapter(imgArr)
        sliderView.apply {
            setSliderAdapter(adapter)
            isAutoCycle = false
            sliderPager.setCurrentItem(0, false)
        }
        nameAdapter = NameAdapter(modelList) {
            sliderView.sliderPager.setCurrentItem(it, false)
            loadNames(strNames, it)
        }
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.layoutManager = linearLayoutManager
        rv.adapter = nameAdapter
        loadNames(strNames, 0)
        sliderView.sliderPager.addOnPageChangeListener(listener)

    }

    private fun loadNames(strNames: List<String>, selectedPos: Int) {
        modelList.clear()
        for ((index, str) in strNames.withIndex()) {
            val isFirst = index == selectedPos
            modelList.add(NameModel(str, isFirst))
        }
        nameAdapter.notifyDataSetChanged()
    }

    val listener = object : SliderPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

        }

        override fun onPageSelected(position: Int) {
            loadNames(strNames, position)
            rv.scrollToPosition(position)
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    fun getData() {
        strNames.clear()
        imgArr.clear()
        val arr = SharedPreference.getSharedPreffObject(this, "data")!!
        for (data in arr) {
            strNames.add(data.name)
            imgArr.add(data.drawable)
        }
    }
}
