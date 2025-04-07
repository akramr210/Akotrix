package com.app.akotrix

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity

class DummyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_dummy)

    }
}