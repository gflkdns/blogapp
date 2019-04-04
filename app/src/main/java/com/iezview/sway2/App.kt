package com.iezview.sway2

import android.app.Application
import android.util.Log
import android.webkit.JavascriptInterface
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.DownloadFileListener
import cn.bmob.v3.listener.FindListener
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.iezview.sway2.bean.Action
import com.iezview.sway2.bean.HotFix
import com.miqt.wand.Wand
import java.io.File


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

        val query = BmobQuery<HotFix>()
        query.setLimit(1).order("-createdAt")
                .findObjects(object : FindListener<HotFix>() {
                    override fun done(`object`: List<HotFix>, e: BmobException?) {
                        if (`object`.size > 0 && e == null) {
                            val fix = `object`.get(0)
                            if (fix.versionCode > SPUtils.getInstance().getInt("wand_last_file", -1)) {
                                downloadAndTouch(fix)
                            }
                        } else {
                           Log.d("wandfix","\"更新失败：${e?.message}\"")
                        }
                    }
                })
    }

    private fun downloadAndTouch(fix: HotFix) {
        fix.dexFile.download(object : DownloadFileListener() {
            override fun onProgress(p0: Int?, p1: Long) {
                Log.d("wandfix_onProgress", "$p0/$p1")
            }

            override fun done(p0: String?, p1: BmobException?) {
                Wand.get().attachPack(File(p0))
                Log.d("wandfix", p0)
                SPUtils.getInstance().put("wand_last_file", fix.versionCode)
            }
        })
    }
}
