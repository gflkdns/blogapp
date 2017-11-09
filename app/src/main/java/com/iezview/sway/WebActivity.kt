package com.iezview.sway

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.v4.widget.SwipeRefreshLayout
import android.webkit.WebChromeClient

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

        web_view.loadUrl(cfg.url)
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
