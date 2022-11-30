package com.example.a4_t1


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class rcAdapter(private var context: Context, private var Image: ArrayList<Int>, private var Name:ArrayList<String>
                , private var Spec:ArrayList<String>, private var Camera:ArrayList<String>, private var CPU:ArrayList<String>,
                private var URL:ArrayList<String>) :

    RecyclerView.Adapter<rcAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.rcrow, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        // setting image resource
        viewHolder.imgview.setImageResource(Image[i])
        viewHolder.txtName.setText(Name[i])
        viewHolder.txtCamera.setText(Camera[i])
        viewHolder.txtSpec.setText(Spec[i])
        viewHolder.txtChipset.setText(CPU[i])
        viewHolder.itemView.setOnClickListener(){
            val intent = Intent(context,MainActivity2::class.java)
            intent.putExtra("url",URL[i])
            context.startActivity(intent)
//            Toast.makeText(context,URL[i],Toast.LENGTH_LONG).show()
        }

    }
    override fun getItemCount(): Int {
        return Image.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgview: ImageView
        var txtName: TextView
        var txtSpec: TextView
        var txtCamera: TextView
        var txtChipset: TextView
        init {
            // getting ImageView reference
            imgview = itemView.findViewById<View>(R.id.imgPhone) as ImageView
            txtName = itemView.findViewById<View>(R.id.txtName) as TextView
            txtSpec = itemView.findViewById<View>(R.id.txtSpec) as TextView
            txtCamera = itemView.findViewById<View>(R.id.txtCamera) as TextView
            txtChipset = itemView.findViewById<View>(R.id.txtCPU) as TextView
        }
    }
}