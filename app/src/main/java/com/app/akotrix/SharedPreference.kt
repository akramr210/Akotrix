package com.app.akotrix

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object SharedPreference {

    fun putSharedPrefObject(context: Context, key: String?, `object`: ArrayList<NameImageModel>) {
        val gson: Gson = Gson()
        val json: String = gson.toJson(`object`)
        val preferences =
            context.getSharedPreferences(context.packageName + "_PREFS", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putString(key, json)
        edit.commit()
    }

    fun getSharedPreffObject(context: Context, key: String?): ArrayList<NameImageModel>? {
        val type: Type = object : TypeToken<ArrayList<NameImageModel>?>() {}.type

        val preferences =
            context.getSharedPreferences(context.packageName + "_PREFS", Context.MODE_PRIVATE)
        val gson: Gson = Gson()
        val json = preferences.getString(key, "")
        val obj = gson.fromJson(json, type) as ArrayList<NameImageModel>?

        return obj
    }

}