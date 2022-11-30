package com.example.a5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RCAdapter_1(private val mContacts:List<MyData>,val context: Context): RecyclerView.Adapter<RCAdapter_1.ViewHolder>() {
    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val txt_item: TextView = itemview.findViewById(R.id.txt_item)
        val txt_quantity: TextView = itemview.findViewById(R.id.txt_quantity)
        val txt_size: TextView = itemview.findViewById(R.id.txt_size)
        val img_icon: ImageView = itemview.findViewById(R.id.img_uob)
        val txt_date:TextView = itemview.findViewById(R.id.txt_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.rc_row_1,parent,false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: RCAdapter_1.ViewHolder, position: Int) {
        val contact:MyData = mContacts.get(position)
        holder.txt_item.setText(contact.item)
        holder.txt_quantity.setText(contact.quantity)
        holder.txt_size.setText(contact.size)
        val current = contact.date.split(" ").toTypedArray()
        holder.txt_date.setText(current[0] + " " + current[1].take(3) + " " + current[2])

        holder.img_icon.setImageResource(contact.getPicture2())
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}