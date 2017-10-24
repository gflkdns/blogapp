package com.iezview.sway

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.webkit.WebView

class WelcomeActy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_acty)
        Handler().postDelayed({
            startActivity(Intent(this@WelcomeActy, WebActivity::class.java))
            finish()
        }, 1000)
    }
}
