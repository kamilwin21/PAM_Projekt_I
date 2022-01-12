package com.example.projekt_zaliczeniowy

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projekt_zaliczeniowy.Classes.Note
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.DataBaseHandler
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.FeedEntry
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        var note: Note = Note()

        btn_add_note.setOnClickListener {
            note.title = edit_text_add_title.text.toString()
            note.description = edit_text_add_description.text.toString()

            if (edit_text_add_title.text.isEmpty() || edit_text_add_description.text.isEmpty())
            {
                onBackPressed()
                Toast.makeText(applicationContext,"Nie było nic do zapisania", Toast.LENGTH_SHORT).show()
            }else{
                var dbHelper = DataBaseHandler(applicationContext)
                dbHelper.addNote(note)
                Toast.makeText(applicationContext, "Dodano notatke", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }


        }

    }
}