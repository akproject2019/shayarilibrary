package com.example.shayarilibrary

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.shayarilibrary.models.Shayari
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShayariSDK private constructor(
    private val context: Context
) {

    companion object {
        @Volatile
        private var instance: ShayariSDK? = null

        fun initialize(context: Context): ShayariSDK {
            return instance ?: synchronized(this) {
                instance ?: ShayariSDK(context.applicationContext).also {
                    instance = it
                }
            }
        }
    }

    fun showToast() {
        Toast.makeText(context, "Hello from SDK", Toast.LENGTH_SHORT).show()
    }

    fun getShayariList(): List<Shayari> {
        Log.d("SDK", "getShayariList called")
        val json = context.assets
            .open("shayari.json")
            .bufferedReader()
            .use {
                it.readText()
            }

        Log.d("SDK", "JSON = $json")
        val type = object : TypeToken<List<Shayari>>() {}.type

        val list: List<Shayari> = Gson().fromJson(json, type)

        Log.d("SDK", "List size = ${list.size}")

        return list
    }
}