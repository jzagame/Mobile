package com.example.a3_t1

class Contact(val name:String,val rate:String,val price:String,val area:String,val img:Int) {
    companion object{
        fun createContactList(room:Room):ArrayList<Contact>{
            val contacts = ArrayList<Contact>()
            for(i in 0..room.HostelNameList().size-1){
                val temp = room.infoApartOrHotel(room.HostelNameList().get(i).toString())
                val temp_price_with_name = temp.get(5).toString().split(",").toTypedArray()
                val temp_price_only = temp_price_with_name.get(temp_price_with_name.size - 1).toString().split("-").toTypedArray()
                contacts.add(Contact(
                    temp.get(0).toString(),
                    temp.get(2).toString(),
                    temp_price_only.get(temp_price_only.size-1).toString(),
                    temp.get(1).toString(),
                    temp.get(7).toString().toInt()
                ))
            }
            return contacts
        }
    }
}