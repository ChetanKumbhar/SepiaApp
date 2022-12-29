package com.example.sepiaapp.helper

import android.webkit.WebView
import android.widget.ProgressBar
import com.example.sepiaapp.BaseTestClass
import io.mockk.*
import org.junit.Test


class WebViewHelperTest : BaseTestClass() {
    lateinit var sut: WebViewHelper

    override fun setup() {
        super.setup()
        sut = WebViewHelper
    }

    @Test
    fun load(){
        val webView = mockk<WebView>(relaxed = true)
        val progressBar = mockk<ProgressBar>(relaxed = true)
        every { webView.loadUrl(any()) } just Runs
        sut.load(webView ,"url", progressBar)
        verify(exactly = 1) { webView.loadUrl(any())  }
    }
}