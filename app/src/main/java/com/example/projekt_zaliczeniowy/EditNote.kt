package com.example.projekt_zaliczeniowy

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt_zaliczeniowy.Classes.Note
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.DataBaseHandler
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.FeedEntry
import kotlinx.android.synthetic.main.activity_edit_note.*
import org.w3c.dom.Text

class EditNote : AppCompatActivity() {
    private var totalTitleWords: Int = 100
    private var totalDescription: Int = 300
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        if (intent.hasExtra("title") || intent.hasExtra("description"))
        {
            edit_text_title_edit_note.setText(intent.getStringExtra("title"))
            edit_text_description_edit_note.setText(intent.getStringExtra("description"))
            var id = intent.getIntExtra("id",0)
            var dbHelper = DataBaseHandler(applicationContext)
            var db= dbHelper.writableDatabase

            btn_edit_note.setOnClickListener {
                val value = ContentValues()
                value.put(FeedEntry.COLUMN_NAME_TITLE,edit_text_title_edit_note.text.toString() )
                value.put(FeedEntry.COLUMN_NAME_DESCRIPTION, edit_text_description_edit_note.text.toString())

                db.update(FeedEntry.TABLE_NAME,value,BaseColumns._ID + "=?",
                    arrayOf(id.toString()))
                Toast.makeText(applicationContext,"Zaktualizowano notatkę", Toast.LENGTH_SHORT).show()
                onBackPressed()
            }



        }
        counter_title_total_words.text = "${ (totalTitleWords - edit_text_title_edit_note.text.length).toString() } / ${totalTitleWords.toString()}"
        counter_description_total_words.text = "${ totalDescription - edit_text_description_edit_note.text.length } / ${totalDescription}"
        edit_text_title_edit_note.addTextChangedListener(titleWatcher)
        edit_text_description_edit_note.addTextChangedListener(descriptionWatcher)


    }
    private var descriptionWatcher: TextWatcher = object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(counter_description_total_words.text.isEmpty())
                {

                }
        }

        override fun afterTextChanged(s: Editable?) {
            counter_description_total_words.text = "${(totalDescription - s!!.length).toString()} / ${totalDescription}"
        }

    }

    private var titleWatcher:TextWatcher = object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            counter_title_total_words.text ="${(totalTitleWords - s!!.length).toString()} / ${totalTitleWords}"

        }

    }


}