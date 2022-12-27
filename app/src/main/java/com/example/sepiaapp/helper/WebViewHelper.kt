package com.example.sepiaapp.helper

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide

object WebViewHelper {
    fun load(webView: WebView, url:String, progressBar: ProgressBar){
        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                }
            }
            loadUrl(url)
        }
    }
}