package com.example.projekt_zaliczeniowy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.DataBaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DataBaseHandler(applicationContext)
        stats_text_view_count_of_notes.text = dbHelper.getAll().toString()


    }



}