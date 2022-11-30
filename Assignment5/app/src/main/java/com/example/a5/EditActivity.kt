package com.example.a5

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class EditActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val id =  Integer.parseInt(intent.getStringExtra("id"))
        val db = MyDB(this)
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("Default")
        arrayList.add("Small")
        arrayList.add("Medium")
        arrayList.add("Large")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        val spn_size: Spinner = findViewById(R.id.spn_size)
        spn_size.adapter = arrayAdapter

        val etItem: EditText = findViewById(R.id.ET_item)
        val etDetails: EditText = findViewById(R.id.ET_details)
        val txtQuantity: TextView = findViewById(R.id.txt_num)
        val spnSize: Spinner = findViewById(R.id.spn_size)
        val cbUrgent: CheckBox = findViewById(R.id.cb_urgent)
        val btnSubmit: Button = findViewById(R.id.btn_addlist)
        val imgUp: ImageView = findViewById(R.id.img_up)
        val imgDown: ImageView = findViewById(R.id.img_down)

        val ddd = db.getData2(id)

        etItem.setText(ddd[0].item)
        etDetails.setText(ddd[0].details)
        txtQuantity.setText(ddd[0].quantity)
        cbUrgent.isChecked = ddd[0].getChecked()
        spn_size.setSelection(ddd[0].getPosition())


        btnSubmit.setOnClickListener(){
            if(TextUtils.isEmpty(etItem.text)){
                etItem.setError("Please enter the item to be purchased")
                return@setOnClickListener
            }else{
                db.updateData(id,etItem.text.toString(),etDetails.text.toString(),txtQuantity.text.toString(),
                    spnSize.selectedItem.toString(),cbUrgent.isChecked.toString(),"false")
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
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