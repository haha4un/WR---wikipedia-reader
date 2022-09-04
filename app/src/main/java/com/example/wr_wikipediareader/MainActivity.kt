package com.example.wr_wikipediareader

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    var w: WebView ?= null;
    var search: String ?= "f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getSupportActionBar()?.hide()

        w = findViewById(R.id.web)
        var web: WebView = findViewById(R.id.web)
        web.settings.javaScriptEnabled = true

        var ok: Button = findViewById(R.id.ok)
        var back: Button = findViewById(R.id.back)
        var reload: Button = findViewById(R.id.reload)

        var txt: EditText = findViewById(R.id.what)

        var start = intent.getSerializableExtra("tog").toString()
        w?.loadUrl(start)
        w?.webViewClient = MyWebViewClient()
        web.webViewClient = MyWebViewClient()
        web.settings.javaScriptEnabled = true


        ok.setOnClickListener()
        {
            search = txt.text.toString()
            goToSearch(search.toString(), web)
        }
        back.setOnClickListener()
        {
            if (w?.canGoBack() == true) {
                w?.goBack()
            }
        }
        reload.setOnClickListener()
        {
            w?.reload()
        }
        var toHome: Button = findViewById(R.id.toHome)
        toHome.setOnClickListener()
        {
            var i: Intent = Intent(this, com.example.wr_wikipediareader.start::class.java)
            startActivity(i)
        }
    }

    fun goToSearch(search: String, w: WebView)
    {
        w.loadUrl("https://www.google.com/search?q=$search")
    }


    override fun onBackPressed() {
        if (w?.canGoBack() == true) {
            w?.goBack()
        }
    }
}
//
private class MyWebViewClient : WebViewClient() {

    @TargetApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        view.loadUrl(request.url.toString())
        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}