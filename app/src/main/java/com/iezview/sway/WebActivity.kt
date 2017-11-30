package com.iezview.sway

import android.Manifest
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v4.widget.SwipeRefreshLayout
import com.umeng.socialize.UMShareAPI
import android.support.v4.app.ActivityCompat
import android.Manifest.permission
import android.Manifest.permission.WRITE_APN_SETTINGS
import android.Manifest.permission.GET_ACCOUNTS
import android.Manifest.permission.SYSTEM_ALERT_WINDOW
import android.Manifest.permission.SET_DEBUG_APP
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_PHONE_STATE
import android.Manifest.permission.READ_LOGS
import android.Manifest.permission.CALL_PHONE
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Handler
import android.webkit.*
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb


/**
 * 网页展示界面
 */
class WebActivity : AppCompatActivity() {

    lateinit var web_view: WebView
    lateinit var srl_layout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        findView()
        settingView()
        if (Build.VERSION.SDK_INT >= 23) {
            val pm = packageManager
            val permission = PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", packageName)
            if (!permission) {
                val mPermissionList = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS)
                ActivityCompat.requestPermissions(this, mPermissionList, 123)
            }

        }

        web_view.loadUrl(cfg.url)
        Handler().postDelayed({
            javascriptShare("标题", "http://img4.imgtn.bdimg.com/it/u=128308122,770382628&fm=27&gp=0.jpg", "https://www.duitang.com/", "这里是描述")
        }, 3000)

    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

    }

    @JavascriptInterface
    private fun javascriptShare(title: String, imgUrl: String, url: String, text: String) {
        val web = UMWeb(url, title, text, UMImage(this, imgUrl))
        ShareAction(this)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(umShareListener)
                .open()
    }

    private fun settingView() {
        settingRefview()
        settingWebView()
    }

    private fun settingRefview() {
        srl_layout.setColorSchemeColors(Color.RED, Color.GRAY)
        srl_layout.setOnRefreshListener {
            web_view.loadUrl(web_view.url)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data)
    }

    private fun settingWebView() {
        web_view.settings.javaScriptEnabled = true
        web_view.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val url = request.url.toString()
                    when {
                    //打开网页
                        url.contains("http") -> view.loadUrl(request.url.toString())
                    //发邮件
                        url.contains("mailto") -> mailto(url)
                    //打电话
                        url.contains("tel") -> callTo(url)
                    }
                }
                return true
            }
        })
        web_view.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (!srl_layout.isRefreshing && newProgress != 100) {
                    srl_layout.isRefreshing = true
                }
                if (newProgress == 100) {
                    srl_layout.isRefreshing = false
                }
                super.onProgressChanged(view, newProgress)
            }
        })
    }

    private fun findView() {
        web_view = findViewById(R.id.webview) as WebView
        srl_layout = findViewById(R.id.srl_layout) as SwipeRefreshLayout
    }

    /**
     * 拨打电话
     */
    private fun callTo(url: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL   //android.intent.action.DIAL
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    /**
     * 发送邮件
     */
    private fun mailto(url: String) {
        val data = Intent(Intent.ACTION_SENDTO)
        data.data = Uri.parse(url)
        data.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.id.mail_subject))
        data.putExtra(Intent.EXTRA_TEXT, resources.getString(R.id.mail_text))
        startActivity(data)
    }

    /**
     * 监听手机按键
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //优先在web页面中back
        if (web_view.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            web_view.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

object umShareListener : UMShareListener {
    override fun onResult(p0: SHARE_MEDIA?) {
    }

    override fun onCancel(p0: SHARE_MEDIA?) {
    }

    override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
    }

    override fun onStart(p0: SHARE_MEDIA?) {
    }

}
