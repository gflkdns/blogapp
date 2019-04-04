package com.iezview.sway2

import android.app.Application
import android.webkit.JavascriptInterface
import cn.bmob.v3.Bmob
import com.blankj.utilcode.util.Utils
import com.miqt.wand.Wand


/**
 * Created by Administrator on 2017/11/29.
 */

class App : Application() {
    @JavascriptInterface
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Wand.get().init(this)
        Bmob.initialize(this, "9de701ecbdf29f956d2d0a951cf9d66d");
    }
}
