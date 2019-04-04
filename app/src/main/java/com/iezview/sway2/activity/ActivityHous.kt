package com.iezview.sway2.activity

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.iezview.sway2.proxy.ActionProxy
import com.iezview.sway2.proxy.WebVideoPlayerActyProxy
import com.miqt.wand.activity.ProxyActivity
import com.miqt.wand.anno.BindProxy
import com.miqt.wand.anno.ParentalEntrustmentLevel

@BindProxy(clazz = ActionProxy::class, level = ParentalEntrustmentLevel.NEVER)
class ActionActivity : ProxyActivity()

@BindProxy(clazz = WebVideoPlayerActyProxy::class, level = ParentalEntrustmentLevel.NEVER)
class WebVideoPlayerActy : ProxyActivity() {
    companion object {
        fun start(url: String, showMenu: Boolean?) {
            val intent = Intent(ActivityUtils.getTopActivity(), WebVideoPlayerActy::class.java);
            intent.putExtra("url", url)
            intent.putExtra("showMenu", showMenu)
            ActivityUtils.startActivity(intent)
        }
    }
}