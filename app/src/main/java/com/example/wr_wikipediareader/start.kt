package com.example.wr_wikipediareader

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        getSupportActionBar()?.hide()

        var  base: SQLiteDatabase = baseContext.openOrCreateDatabase("urls.db", MODE_PRIVATE, null)
        base.execSQL("CREATE TABLE IF NOT EXISTS urls (url TEXT NOT NULL);")
        var help = dbhelp()

        var goto: Button = findViewById(R.id.go)
        var spinner: Spinner = findViewById(R.id.spinner)
        spinner.setPrompt("Bookmarks")

        var  placeadapter = ArrayAdapter(this, R.layout.row,R.id.newS, help.tableToarr(base, "urls"))
        spinner.adapter = placeadapter

        goto.setOnClickListener()
        {
            var i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("tog", spinner.selectedItem.toString())
            startActivity(i)
        }

        var searchView: EditText = findViewById(R.id.Edittxt)
        //searchView.setMaxLines(1)

        var searchButton: Button = findViewById(R.id.start_search)
        searchButton.setOnClickListener()
        {
            var i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("tog", "https://www.google.com/search?q=${searchView.text}")
            startActivity(i)
        }

        var del: Button = findViewById(R.id.del)
        del.setOnClickListener()
        {
            help.delUrl(base, spinner.selectedItem.toString(), "urls")

            var  placeadapter = ArrayAdapter(this,R.layout.row,R.id.newS, help.tableToarr(base, "urls"))
            spinner.adapter = placeadapter
        }
    }
}