package com.example.a6

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDB(context: Context):
    SQLiteOpenHelper(context, MyDB.DATABASE_NAME,null, MyDB.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS " + tbName + " (" + id + " INTEGER PRIMARY KEY, " +
                title + " TEXT," +
                imageName + " TEXT," +
                latitude + " DOUBLE," +
                longitude + " DOUBLE" + ")"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + tbName)
        onCreate(p0)
    }

    fun addData(ttl:String,imgName:String,longt:Double,lat:Double){
        val temp = ContentValues()
        val db = this.writableDatabase
        temp.put(title,ttl)
        temp.put(imageName,imgName)
        temp.put(latitude,lat)
        temp.put(longitude,longt)
        db.insert(tbName,null,temp)
        db.close()
    }

    fun getData(): ArrayList<MyData>{
        var temp:ArrayList<MyData> = arrayListOf()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName ,null)
        cursor!!.moveToFirst()
        if(cursor.count > 0){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.title)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(MyDB.longitude)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(MyDB.latitude)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.imageName))
            ))
        }
        while (cursor!!.moveToNext()){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.title)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(MyDB.longitude)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(MyDB.latitude)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.imageName))
            ))
        }
        cursor.close()
        return temp
    }

    fun getData2(x:String): MyData{
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName + " where title='"+x+"'",null)
        cursor!!.moveToFirst()
        var temp:MyData = MyData(
            cursor.getInt(cursor.getColumnIndexOrThrow(id)),
            cursor.getString(cursor.getColumnIndexOrThrow(title)),
            cursor.getDouble(cursor.getColumnIndexOrThrow(longitude)),
            cursor.getDouble(cursor.getColumnIndexOrThrow(latitude)),
            cursor.getString(cursor.getColumnIndexOrThrow(imageName))
        )
        cursor.close()
        return temp
    }

    companion object{
        private val DATABASE_NAME = "test113"
        private val DATABASE_VERSION = 1
        val tbName = "wa113"
        val id = "id"
        val title = "title"
        val latitude = "latitude"
        val longitude = "longitude"
        val imageName = "imageName"
    }
}