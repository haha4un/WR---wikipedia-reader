package com.example.wr_wikipediareader

import android.annotation.TargetApi
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var w: WebView ?= null;
    var search: String ?= "f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var actB: ActionBar = getSupportActionBar()!!
        actB.setCustomView(R.layout.actionbarnw)
        var wht = actB.customView.findViewById<EditText>(R.id.what)
        var back = actB.customView.findViewById<Button>(R.id.back)
        var reload = actB.customView.findViewById<Button>(R.id.reload)
        var home = actB.customView.findViewById<Button>(R.id.toHome)
        var favourite = actB.customView.findViewById<Button>(R.id.favourite)
        actB.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)

        w = findViewById(R.id.web)
        var web: WebView = findViewById(R.id.web)
        web.settings.javaScriptEnabled = true

//        var ok: Button = findViewById(R.id.ok)
//        var txt: EditText = findViewById(R.id.what)

        var start = intent.getSerializableExtra("tog").toString()
        w?.loadUrl(start)
        w?.webViewClient = MyWebViewClient()
        web.webViewClient = MyWebViewClient()
        web.settings.javaScriptEnabled = true
        w?.settings?.javaScriptEnabled = true
//        ok.setOnClickListener()
//        {
//            search = txt.text.toString()
//            goToSearch(search.toString(), web)
//        }
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
        home.setOnClickListener()
        {
            var i: Intent = Intent(this, com.example.wr_wikipediareader.start::class.java)
            startActivity(i)
        }
        favourite.setOnClickListener()
        {
            var help = dbhelp()
                var  base: SQLiteDatabase = baseContext.openOrCreateDatabase("urls.db", MODE_PRIVATE, null)
                if (!help.createUrl(base, w?.url.toString(), "urls"))
                    Toast.makeText(this, "Запись уже есть", Toast.LENGTH_LONG)
        }
        wht.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    goToSearch(wht.text.toString(), web)
                }
                return false
            }
        })
    }
    fun goToSearch(search: String, w: WebView)
    {
        if (search.startsWith("https://"))
            w.loadUrl(search)
        else
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