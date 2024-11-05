package com.app.akotrix

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView

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
}