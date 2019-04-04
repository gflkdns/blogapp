package com.iezview.sway2.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.iezview.sway2.R

class WellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_well)
        Handler().postDelayed({
            startActivity(Intent(this@WellActivity, WebVideoPlayerActy::class.java))
            finish()
        }, 3000)
    }
}
