package com.example.a5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        val id = Integer.parseInt(intent.getStringExtra("id"))
        val db = MyDB(this)
        val temp = db.getData2(id)
        val txt_item: TextView = findViewById(R.id.txt_item)
        val txt_details:EditText = findViewById(R.id.ET_detail)
        val txt_quantity:TextView = findViewById(R.id.txt_quantity)
        val txt_size:TextView = findViewById(R.id.txt_size)
        val img:ImageView = findViewById(R.id.img_urgent)
        txt_item.setText(temp[0].item)
        txt_details.setText(temp[0].details)
        txt_quantity.setText(temp[0].quantity)
        txt_size.setText(temp[0].size)
        if(temp[0].urgent == "false"){
            img.setImageResource(R.drawable.unchecked)
        }else{
            img.setImageResource(R.drawable.checked)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.right_corner, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.shareButton -> {
            val id = intent.getStringExtra("id")
            val intent = Intent(this,EditActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
            true
        }else ->{
            super.onOptionsItemSelected(item)
        }
    }
}