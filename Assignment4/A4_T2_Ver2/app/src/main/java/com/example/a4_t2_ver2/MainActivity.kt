package com.example.a4_t2_ver2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val data:MyData = GenerateData()
        val gv:GridView = findViewById(R.id.GVimg)
        var ll:ArrayList<img> = data.specific("all")
        var dataAdapter = GVAdapter(ll, this@MainActivity,data.Url())
        gv.adapter = dataAdapter
        gv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // inside on click method we are simply displaying
            // a toast message with course name.
            val intent:Intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("name",ll[position].name)
            intent.putExtra("url",data.Url() + ll[position].img)
            startActivity(intent)
//            Toast.makeText(
//                applicationContext, ll[position].name + " selected",
//                Toast.LENGTH_SHORT
//            ).show()
        }

        val spn:Spinner = findViewById(R.id.spinSelect)
        val spinAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, data.dropdown())
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn.adapter = spinAdapter
        spn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 1){
                    ll = data.specific("animal")
                    dataAdapter = GVAdapter(ll,this@MainActivity,data.Url())
                    gv.adapter = dataAdapter
                }else if(p2 == 2){
                    ll = data.specific("plant")
                    dataAdapter = GVAdapter(ll,this@MainActivity,data.Url())
                    gv.adapter = dataAdapter
                }else{
                    ll = data.specific("all")
                    dataAdapter = GVAdapter(ll,this@MainActivity,data.Url())
                    gv.adapter = dataAdapter
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        // on below line we are setting adapter to our grid view.


    }

    fun GenerateData():MyData{
        val mm:MyData = MyData()
        mm.Url("http://172.17.3.254/mobile_a4t2/")
        var ii:img = img("koala","koala_thumb.jpg","koala.jpg","animal")
        mm.add(ii)
        ii = img("orchid","orchid_thumb.jpg","prchid.jpg","plant")
        mm.add(ii)
        ii = img("pitcher","pitcher_thumb.jpg","pitcher.jpg","animal")
        mm.add(ii)
        ii = img("quokka","quokka_thumb.jpg","quokka.jpg","animal")
        mm.add(ii)
        ii = img("rabbit","rabbit_thumb.jpg","rabbit.jpg","animal")
        mm.add(ii)
        ii = img("rafflesia","rafflesia_thumb.jpg","rafflesia.jpg","plant")
        mm.add(ii)
        ii = img("seal","seal_thumb.jpg","seal.jpg","animal")
        mm.add(ii)
        ii = img("sloth","sloth_thumb.jpg","sloth.jpg","animal")
        mm.add(ii)
        ii = img("succulent","succulent_thumb.png","succulent.png","plant")
        mm.add(ii)
        ii = img("titan arum","titan_arum_thumb.jpg","titan_arum.jpg","plant")
        mm.add(ii)
        return mm
    }
}