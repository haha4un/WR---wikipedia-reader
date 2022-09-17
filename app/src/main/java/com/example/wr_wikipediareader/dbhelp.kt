package com.example.wr_wikipediareader

import android.app.ProgressDialog.show
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

class dbhelp {

    fun createUrl(db: SQLiteDatabase, value: String, table: String) : Boolean
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM $table where url = '$value'", null)
        var str: String = value

        while(cursor.moveToNext()) {

            if (str == cursor.getString(0))
            {
                return false
            }
        }
        db.execSQL("INSERT INTO $table VALUES ('$value')")
        return true
    }

    fun delUrl(db: SQLiteDatabase, value: String, table: String) {
        var cursor: Cursor = db.rawQuery("SELECT * FROM $table where url = '$value'", null)
        while(cursor.moveToNext())
            if (value == cursor.getString(0))
                db.execSQL("Delete from $table where url = '$value'") }

    fun tableToarr(db: SQLiteDatabase, table: String) : Array<String>
    {
        var cursor: Cursor = db.rawQuery("SELECT * FROM $table", null)
        var arr = emptyArray<String>()
        while(cursor.moveToNext()) {
            arr += cursor.getString(0)
        }
        arr += "https://github.com/haha4un"
        arr += "https://www.google.com/"
        return arr
    }
}