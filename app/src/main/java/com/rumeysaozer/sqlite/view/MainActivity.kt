package com.rumeysaozer.sqlite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rumeysaozer.sqlite.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            val myDatabase = this.openOrCreateDatabase("notes", MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS notes( id INTEGER PRIMARY KEY, baslik VARCHAR, text VARCHAR)")
            myDatabase.execSQL("INSERT INTO notes(baslik,text) VALUES('NOT','fjgkf')")
            myDatabase.execSQL("UPDATE notes SET baslik = 'nn' WHERE id = 1")
            myDatabase.execSQL("DELETE FROM notes WHERE id > 2 ")
            //val cursor = myDatabase.rawQuery("SELECT * FROM notes WHERE id = 1",null)
            val cursor = myDatabase.rawQuery("SELECT * FROM notes",null)
            val baslıkIx = cursor.getColumnIndex("baslik")
            val textIx = cursor.getColumnIndex("text")
            val idIx = cursor.getColumnIndex("id")
            while(cursor.moveToNext()){
                println("Başlık"+cursor.getString(baslıkIx))
                println("Text"+cursor.getString(textIx))
                println("Id"+cursor.getInt(idIx))
            }
            cursor.close()
        }catch(e: Exception){
            e.printStackTrace()
        }
    }
}