package com.example.sepiaapp.helper

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.sepiaapp.model.Config
import com.example.sepiaapp.model.PetList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class GsonHelper @Inject constructor(
    private val context: Context,
    private val gson: Gson) {
    @VisibleForTesting
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

    fun getPetList() : PetList {
        val jsonFileString = getJsonDataFromAsset(context, "pets.json")
        val listPersonType = object : TypeToken<PetList>() {}.type
        return gson.fromJson(jsonFileString, listPersonType)
    }

    fun getTimeConfig() : Config {
        val jsonFileString = getJsonDataFromAsset(context, "config.json")
        val config = object : TypeToken<Config>() {}.type
        return gson.fromJson(jsonFileString, config)
    }
}
