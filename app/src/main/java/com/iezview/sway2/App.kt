package com.iezview.sway2

import android.app.Application
import android.util.Log
import android.webkit.JavascriptInterface
import com.avos.avoscloud.AVOSCloud
import com.blankj.utilcode.util.Utils
import com.miqt.wand.Wand
import com.avos.avoscloud.AVException
import com.avos.avoscloud.AVObject
import com.avos.avoscloud.SaveCallback


/**
 * Created by Administrator on 2017/11/29.
 */

class App : Application() {
    @JavascriptInterface
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        Wand.get().init(this)
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"DMkb08sHKI9ewtfWndD1Cj4n-gzGzoHsz","A97QWxdf01FUL0e4inBFCKRg");
        AVOSCloud.setDebugLogEnabled(true);
        // 测试 SDK 是否正常工作的代码
        val testObject = AVObject("TestObject")
        testObject.put("words", "Hello World!")
        testObject.saveInBackground(object : SaveCallback() {
            override fun done(e: AVException?) {
                if (e == null) {
                    Log.d("saved", "success!")
                }
            }
        })
    }
}
