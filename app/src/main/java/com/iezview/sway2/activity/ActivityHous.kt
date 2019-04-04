package com.iezview.sway2.activity

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.iezview.sway2.proxy.ActionProxy
import com.iezview.sway2.proxy.WebVideoPlayerActyProxy
import com.miqt.wand.activity.ProxyActivity
import com.miqt.wand.anno.BindProxy
import com.miqt.wand.anno.ParentalEntrustmentLevel

@BindProxy(clazz = ActionProxy::class, level = ParentalEntrustmentLevel.PROJECT)
class ActionActivity : ProxyActivity()

@BindProxy(clazz = WebVideoPlayerActyProxy::class, level = ParentalEntrustmentLevel.PROJECT)
class WebVideoPlayerActy : ProxyActivity() {
    companion object {
        fun start(url: String) {
            val intent = Intent(ActivityUtils.getTopActivity(), WebVideoPlayerActy::class.java);
            intent.putExtra("url", url)
            ActivityUtils.startActivity(intent)
        }
    }
}