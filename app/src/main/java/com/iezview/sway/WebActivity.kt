package com.iezview.sway

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class WebActivity : AppCompatActivity() {

    lateinit var web_view: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        web_view = findViewById(R.id.webview) as WebView
        web_view.settings.javaScriptEnabled=true
        web_view.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.url.toString())
                }
                return true
            }
        })
        web_view.loadUrl(cfg.url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (web_view.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            web_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
