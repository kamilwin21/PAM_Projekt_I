package com.example.projekt_zaliczeniowy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projekt_zaliczeniowy.Classes.Note
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.DataBaseHandler
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.FeedEntry
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)
    }



    override fun onResume() {
        var dbHelper = DataBaseHandler(applicationContext)
        var db = dbHelper.writableDatabase
        var notes: ArrayList<Note> = arrayListOf()

        val cursor = db.query(
            FeedEntry.TABLE_NAME,null, null,
            null, null, null, null)
        if (cursor.count > 0)
        {
            cursor.moveToFirst()
            while (!cursor.isAfterLast)
            {
                var note = Note()
                note.id = cursor.getInt(0)
                note.title = cursor.getString(1)
                note.description = cursor.getString(2)
                notes.add(note)
                cursor.moveToNext()

            }
            cursor.close()
        }

        super.onResume()


        recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        recycler_view.adapter = NotesAdapter(applicationContext,dbHelper, notes)

        btn_stats.setOnClickListener(myClickListenerToStats)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_button_add_note, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.add_button -> {
                val intent = Intent(applicationContext, AddNote::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private val myClickListenerToStats = View.OnClickListener {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

}