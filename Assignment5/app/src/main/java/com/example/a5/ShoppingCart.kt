package com.example.a5

import android.content.ContentValues
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*

class ShoppingCart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        val db = MyDB(this)
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("Default")
        arrayList.add("Small")
        arrayList.add("Medium")
        arrayList.add("Large")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        val spn_size: Spinner = findViewById(R.id.spn_size)
        spn_size.adapter = arrayAdapter

        val etItem:EditText = findViewById(R.id.ET_item)
        val etDetails:EditText = findViewById(R.id.ET_details)
        val txtQuantity:TextView = findViewById(R.id.txt_num)
        val spnSize:Spinner = findViewById(R.id.spn_size)
        val cbUrgent:CheckBox = findViewById(R.id.cb_urgent)
        val btnSubmit: Button = findViewById(R.id.btn_addlist)
        val imgUp: ImageView = findViewById(R.id.img_up)
        val imgDown:ImageView = findViewById(R.id.img_down)
        btnSubmit.setOnClickListener(){
            if(TextUtils.isEmpty(etItem.text)){
                etItem.setError("Please enter the item to be purchased")
                return@setOnClickListener
            }else{
                db.addData(etItem.text.toString(),etDetails.text.toString(),txtQuantity.text.toString(),
                    spnSize.selectedItem.toString(),cbUrgent.isChecked.toString(),"false")
                etItem.text.clear()
                etDetails.text.clear()
                txtQuantity.text = "1"
                spnSize.setSelection(0)
                cbUrgent.isChecked = false
            }
        }
        imgUp.setOnClickListener(){
            var x:Int = Integer.parseInt(txtQuantity.text.toString())
            x += 1
            txtQuantity.text = x.toString()
        }

        imgDown.setOnClickListener(){
            var x:Int = Integer.parseInt(txtQuantity.text.toString())
            if(x > 1){
                x -= 1
                txtQuantity.text = x.toString()
            }
        }



    }
}