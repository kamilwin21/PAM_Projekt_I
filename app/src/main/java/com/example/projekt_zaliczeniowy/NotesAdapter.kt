package com.example.projekt_zaliczeniowy

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projekt_zaliczeniowy.Classes.Note
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.DataBaseHandler
import com.example.projekt_zaliczeniowy.SQLiteOpenHelper.FeedEntry
import kotlinx.android.synthetic.main.position_in_recycler_view.view.*


    class NotesAdapter(val context: Context, val dbHelper:DataBaseHandler, val list: ArrayList<Note>): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val positionList = inflater.inflate(R.layout.position_in_recycler_view,parent,false)
        return MyViewHolder(positionList)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val layoutNote = holder.view.linear_layout_in_position_in_recycler_view
        val noteTitle = holder.view.text_view_in_layout_position_recycler_view
        val noteDescription = holder.view.text_view2_in_layout_position_recycler_view

        noteTitle.text = list[position].title
        noteDescription.text = list[position].description

        layoutNote.setOnClickListener{
            var intent = Intent(holder.view.context.applicationContext, EditNote::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("title", list[position].title)
            intent.putExtra("description", list[position].description)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.view.context.applicationContext.startActivity(intent)


        }
        holder.view.imageViewOptionsOnNote.setOnClickListener{
            var popupMenu: PopupMenu = PopupMenu(holder.view.context, holder.view.imageViewOptionsOnNote)
            popupMenu.inflate(R.menu.menu_settings_notes_in_recycler_view)

            popupMenu.setOnMenuItemClickListener {item ->
                if (item?.itemId == R.id.btn_delete)
                {
                    dbHelper.removeAt(list[position].id)
//                    db.delete(FeedEntry.TABLE_NAME, BaseColumns._ID + "=?",
//                    arrayOf(list[position].id.toString()))
//                    db.close()
                    Toast.makeText(holder.view.context.applicationContext,
                        "Usunięto notatkę", Toast.LENGTH_SHORT).show()
                    var intent = Intent(holder.view.context.applicationContext, NotesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    holder.view.context.applicationContext.startActivity(intent)


                }
                true
            }
            popupMenu.show()
        }

    }

    override fun getItemCount(): Int {
        return dbHelper.getAll()

    }

}



class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}