package com.carrot.presentation.view.auth.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.carrot.presentation.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initWebView()
    }

    private fun initWebView() {
        with(binding.webViewWebViewActivity.settings) {
            // 웹뷰 자바스크립트 허용
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            setSupportMultipleWindows(true)
        }

        with(binding.webViewWebViewActivity) {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            loadUrl("https://sites.google.com/view/carrotdiary")
        }
    }

    private fun init() {
        val action = supportActionBar
        action?.hide()
        binding.buttonCheckWebViewActivity.setOnClickListener {
            finish()
        }
    }

}