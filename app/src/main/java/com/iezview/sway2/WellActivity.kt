package com.iezview.sway2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class WellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_well)
        Handler().postDelayed({
            startActivity(Intent(this@WellActivity, WebActivity::class.java))
            finish()
        }, 3000)
    }
}
