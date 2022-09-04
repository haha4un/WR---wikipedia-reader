package com.example.wr_wikipediareader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class start : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        var goto: Button = findViewById(R.id.go)
        var spinner: Spinner = findViewById(R.id.spinner)
        var selectedSites: ArrayList<String> = arrayListOf("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0", "https://naked-science.ru/","https://www.nature.com/")

        var  placeadapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectedSites)
        spinner.adapter = placeadapter

        goto.setOnClickListener()
        {
            var i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("tog", spinner.selectedItem.toString())
            startActivity(i)
        }

        var searchView: EditText = findViewById(R.id.Edittxt)
        searchView.setMaxLines(1)
        searchView.setHorizontallyScrolling(false)

        var searchButton: Button = findViewById(R.id.start_search)
        searchButton.setOnClickListener()
        {
            var i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("tog", "https://www.google.com/search?q=${searchView.text}")
            startActivity(i)
        }

    }
}