package com.example.wr_wikipediareader

import android.annotation.TargetApi
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var w: WebView ?= null;
    var search: String ?= "f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        w = findViewById(R.id.web)
        var web: WebView = findViewById(R.id.web)
        web.settings.javaScriptEnabled = true

        var ok: Button = findViewById(R.id.ok)

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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.for_mainact, menu)
        return true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.back ->
                if (w?.canGoBack() == true) {
                    w?.goBack()
                }
            R.id.reload -> w?.reload()
            R.id.toHome -> {
                var i: Intent = Intent(this, com.example.wr_wikipediareader.start::class.java)
                startActivity(i)
            }
            R.id.favourite ->
            {
                var help = dbhelp()
                var  base: SQLiteDatabase = baseContext.openOrCreateDatabase("urls.db", MODE_PRIVATE, null)
                if (!help.createUrl(base, w?.url.toString(), "urls"))
                    Toast.makeText(this, "Запись уже есть", Toast.LENGTH_LONG)
            }
        }
        return super.onOptionsItemSelected(item)
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