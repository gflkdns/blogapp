package com.iezview.sway

import android.app.Application
import android.webkit.JavascriptInterface

import com.umeng.socialize.Config
import com.umeng.socialize.PlatformConfig
import com.umeng.socialize.UMShareAPI

/**
 * Created by Administrator on 2017/11/29.
 */

class App : Application() {
    @JavascriptInterface
    override fun onCreate() {
        super.onCreate()
        UMShareAPI.get(this)
        PlatformConfig.setWeixin("wxce5512ac15a03044", "83e9fe5d49145027dfc3d2692b36fe40")
        PlatformConfig.setQQZone("1105443967", "xS04nuVhKWdW1tL1")
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com")
        Config.DEBUG = true
    }
}
