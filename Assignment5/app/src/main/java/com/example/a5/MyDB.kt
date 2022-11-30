package com.example.a5

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MyDB(context: Context):
    SQLiteOpenHelper(context, MyDB.DATABASE_NAME,null, MyDB.DATABASE_VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS " + tbName + " (" + id + " INTEGER PRIMARY KEY, " +
                item + " TEXT," +
                details + " TEXT," +
                quantity + " TEXT," +
                size + " TEXT," +
                urgent + " TEXT," +
                buy + " TEXT," +
                purchasedate + " TEXT" + ")"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS " + tbName)
        onCreate(p0)
    }

    fun updateData(id:Int,name:String,info:String,num:String,ttl:String,flag:String,flag2:String){
        val temp = ContentValues()
        val db = this.writableDatabase
        temp.put(item,name)
        temp.put(details,info)
        temp.put(quantity,num)
        temp.put(size,ttl)
        temp.put(urgent,flag)
        temp.put(buy,flag2)
        temp.put(purchasedate,"")
        db.update(tbName, temp,"id="+ id,null)
        db.close()
    }

    fun addData(name:String,info:String,num:String,ttl:String,flag:String,flag2:String){
        val temp = ContentValues()
        val db = this.writableDatabase
        temp.put(item,name)
        temp.put(details,info)
        temp.put(quantity,num)
        temp.put(size,ttl)
        temp.put(urgent,flag)
        temp.put(buy,flag2)
        temp.put(purchasedate,"")
        db.insert(tbName,null,temp)
        db.close()
    }

    fun amen(temp:Int){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(id,temp)
        db.delete(tbName,"id=" + temp,null)
        db.close()
    }

    fun getData(): ArrayList<MyData>{
        var temp:ArrayList<MyData> = arrayListOf()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName + " where buy != 'true'",null)
        cursor!!.moveToFirst()
        if(cursor.count > 0){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        while (cursor!!.moveToNext()){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        cursor.close()
        return temp
    }

    fun getData2(x:Int): ArrayList<MyData>{
        var temp:ArrayList<MyData> = arrayListOf()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName + " where id=" + x,null)
        cursor!!.moveToFirst()
        if(cursor.count > 0){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        cursor.close()
        return temp
    }

    fun getData3(): ArrayList<MyData>{
        var temp:ArrayList<MyData> = arrayListOf()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName + " where buy != 'true' and urgent == 'true'",null)
        cursor!!.moveToFirst()
        if(cursor.count > 0){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        while (cursor!!.moveToNext()){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        cursor.close()
        return temp
    }

    fun getData1(): ArrayList<MyData>{
        var temp:ArrayList<MyData> = arrayListOf()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + tbName + " where buy == 'true'",null)
        cursor!!.moveToFirst()
        if(cursor.count > 0){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        while (cursor!!.moveToNext()){
            temp.add(MyData(
                cursor.getInt(cursor.getColumnIndexOrThrow(MyDB.id)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.item)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.details)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.quantity)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.size)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.urgent)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.buy)),
                cursor.getString(cursor.getColumnIndexOrThrow(MyDB.purchasedate))
            ))
        }
        cursor.close()
        return temp
    }

    fun buybuybuy(md:MyData){
        val current = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        var formattedDate = current.format(formatter)

        val db = this.writableDatabase
        val temp = ContentValues()
        temp.put(buy,"true")
        temp.put(purchasedate,formattedDate.toString())
        db.update(tbName,temp,"id=" + md.id,null)
        db.close()
    }

    companion object{
        private val DATABASE_NAME = "test"
        private val DATABASE_VERSION = 1
        val tbName = "wa"
        val id = "id"
        val item = "item"
        val details = "details"
        val quantity = "quantity"
        val size = "size"
        val urgent = "urgent"
        val buy = "buy"
        val purchasedate = "date"
    }
}