package com.example.a5

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class RCAdapter(private val mContacts:List<MyData>,val context:Context): RecyclerView.Adapter<RCAdapter.ViewHolder>() {
    val db = MyDB(context)
    inner class ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
        val txt_item: TextView = itemview.findViewById(R.id.txt_item)
        val txt_quantity: TextView = itemview.findViewById(R.id.txt_quantity)
        val txt_size: TextView = itemview.findViewById(R.id.txt_size)
        val sw_buy: Switch = itemview.findViewById(R.id.sw_buy)
        val img_icon: ImageView = itemview.findViewById(R.id.img_uob)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.rc_row,parent,false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: RCAdapter.ViewHolder, position: Int) {
        val contact:MyData = mContacts.get(position)
        holder.txt_item.setText(contact.item)
        holder.txt_quantity.setText(contact.quantity)
        holder.txt_size.setText(contact.size)

        holder.img_icon.setImageResource(contact.getPicture())
        holder.img_icon.setOnClickListener {
//            val intent: Intent = Intent(holder.itemView.context,BookingForm::class.java)
//            intent.putExtra("hostelName",contact.name)
//            holder.itemView.context.startActivity(intent)
        }

        holder.sw_buy.setOnClickListener(){
            val x = MyDB(context)
            x.buybuybuy(contact)
            (context as MainActivity).loadFragment(HomeFragment())
        }

        holder.itemView.setOnClickListener(){
            val intent  = Intent(context,ViewActivity::class.java)
            intent.putExtra("id",contact.id.toString())
            //val intent  = Intent(this,ViewActivity::class.java)
            context.startActivity(intent)
        }

        val btnDelete =  {dialog:DialogInterface,which:Int->
            db.amen(contact.id)
            (context as MainActivity).loadFragment(HomeFragment())
        }

        val btnCancel = {dialog:DialogInterface,which:Int->
            Toast.makeText(context,"cancel",Toast.LENGTH_LONG).show()
        }

        holder.itemView.setOnLongClickListener(){
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Item")
            builder.setMessage("Are you sure you want to delete "+ contact.item +" from the list?")
            builder.setPositiveButton("Delete",DialogInterface.OnClickListener(function = btnDelete))
            builder.setNegativeButton("Cancel",DialogInterface.OnClickListener(function = btnCancel))
            builder.show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}