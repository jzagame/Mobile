package com.example.a3_t1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ContactsAdapter(private val mContacts:List<Contact>):RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    inner class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val txt_rate:TextView = itemview.findViewById(R.id.txt_rate)
        val txt_name:TextView = itemview.findViewById(R.id.txt_name)
        val txt_area:TextView = itemview.findViewById(R.id.txt_area)
        val txt_price:TextView = itemview.findViewById(R.id.txt_price)
        val img_icon:ImageView = itemview.findViewById(R.id.img_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.room_per_row,parent,false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ContactsAdapter.ViewHolder, position: Int) {
        val contact:Contact = mContacts.get(position)
        holder.txt_name.setText(contact.name)
        holder.txt_area.setText(contact.area)
        holder.txt_price.setText(contact.price)
        holder.txt_rate.setText(contact.rate + " stars")
        holder.img_icon.setImageResource(contact.img)
        holder.img_icon.setOnClickListener {
            val intent:Intent = Intent(holder.itemView.context,BookingForm::class.java)
            intent.putExtra("hostelName",contact.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}