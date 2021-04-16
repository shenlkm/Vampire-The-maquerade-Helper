package com.example.vampiremasterhelper.utils

import android.content.Context
import com.example.vampiremasterhelper.model.DialogSelectItem
import com.example.vampiremasterhelper.model.PointGroupSetModel
import com.google.gson.Gson
import java.io.IOException

class Refrigerator {

    companion object {

        fun getDialogItems(context: Context, fileName: String): List<DialogSelectItem> {
            val jsonFileString = getJsonDataFromAsset(context, "$fileName.json")
            val gson = Gson()
            return gson.fromJson(jsonFileString, Array<DialogSelectItem>::class.java).toMutableList()
        }

        fun getPointSetGroupItems(context: Context, fileName: String): List<PointGroupSetModel> {
            val jsonFileString = getJsonDataFromAsset(context, "$fileName.json")
            val gson = Gson()
            return gson.fromJson(jsonFileString, Array<PointGroupSetModel>::class.java).toMutableList()
        }

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }
            return jsonString
        }
    }
}