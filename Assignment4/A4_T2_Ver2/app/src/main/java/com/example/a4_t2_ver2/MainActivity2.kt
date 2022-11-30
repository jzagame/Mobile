package com.example.a4_t2_ver2

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val txt:TextView = findViewById(R.id.txt)
        val img:ImageView = findViewById(R.id.img)

        txt.setText(intent.getStringExtra("name"))
        Picasso.get().load(intent.getStringExtra("url")).into(img)
    }
}