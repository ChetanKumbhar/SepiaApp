package com.example.sepiaapp.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop

object GlideHelper {
    fun loadImage(imageView : ImageView, url:String, shouldCircle : Boolean){
        if(shouldCircle) {
            Glide
                .with(imageView.rootView.context)
                .load(url)
                .override(100, 100)
                .transform(CircleCrop())
                .into(imageView)
        }else{
            Glide
                .with(imageView.rootView.context)
                .load(url)
                .override(100, 100)
                .into(imageView)
        }
    }
}