package com.example.sepiaapp

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :  AppCompatActivity(R.layout.activity_main){
    override fun onResume() {
        super.onResume()
        Log.d("chetan", "onResume: MainActivity")
    }
}
