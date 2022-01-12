package com.example.projekt_zaliczeniowy.SQLiteOpenHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.projekt_zaliczeniowy.Classes.Note
import java.util.*

object FeedEntry: BaseColumns {
    const val TABLE_NAME = "entry"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_DESCRIPTION = "description"
}

object BasicCommand{
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${FeedEntry.COLUMN_NAME_TITLE} TEXT NOT NULL," +
                "${FeedEntry.COLUMN_NAME_DESCRIPTION} TEXT NOT NULL)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"

}

class DataBaseHandler(context: Context): SQLiteOpenHelper(context, FeedEntry.TABLE_NAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addNote(note: Note)
    {
        val value = ContentValues()
        value.put(FeedEntry.COLUMN_NAME_TITLE, note.title)
        value.put(FeedEntry.COLUMN_NAME_DESCRIPTION, note.description)
        var db = this.writableDatabase
        db.insertOrThrow(FeedEntry.TABLE_NAME, null, value)
        db.close()

    }

    fun removeAt(index: Int){
        var db = this.writableDatabase
        db.delete(FeedEntry.TABLE_NAME, BaseColumns._ID + "=?",
            arrayOf(index.toString()))
        db.close()
    }

    fun getAll(): Int{
        var db = this.writableDatabase
        var cursor = db.query(FeedEntry.TABLE_NAME,null,null,null,
            null,null,null)
        return cursor.count
    }






}




