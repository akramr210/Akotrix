package com.app.akotrix.utils

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.app.akotrix.R
import java.io.Serializable

object Util {
    fun showDialog(context: Context, callback: (Int) -> Unit) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_change_position)

        val cardCancel = dialog.findViewById<CardView>(R.id.cardCancel)
        val cardSubmit = dialog.findViewById<CardView>(R.id.cardSubmit)
        val editText = dialog.findViewById<EditText>(R.id.edt)

        cardSubmit.setOnClickListener {
            if (editText.text.toString() != "" && editText.text.toString().toInt() > 0) {
                callback.invoke(editText.text.toString().toInt())
                dialog.dismiss()
            } else
                Toast.makeText(context, "Please enter valid Number", Toast.LENGTH_LONG).show()
        }

        cardCancel.setOnClickListener {
            dialog.cancel()
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.show()
    }

     fun deleteDialog(context: Context, callback: () -> Unit) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_delete)

        val cardCancel = dialog.findViewById<CardView>(R.id.cardCancel)
        val cardSubmit = dialog.findViewById<CardView>(R.id.cardSubmit)

        cardSubmit.setOnClickListener {
            dialog.dismiss()
          callback.invoke()
        }

        cardCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val window = dialog.window
        val wlp = window!!.attributes
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT
        dialog.show()
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Parcelable> Intent.getParcelableArrayList(key: String): ArrayList<T>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getParcelableArrayListExtra(key, T::class.java)
        } else {
            this.getParcelableArrayListExtra(key)
        }
    }

    inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }
}