package com.example.a3_t1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var room = Room()
    val name = listOf("Heritage Department","Ameron Hotel","Dorsett Studio Apartment","Travelodge Apartment","Clover Apartment","Wonderloft Hostel")
    val heritageApartment = listOf(name.get(0),"Old Town Area","5","28 meter","10 mins to city center","Executive-RM 1200,Deluxe-RM 700," +
            "Superior-RM 500","Housekeeping,Toiletries,Wi-Fi,Mini Bar",R.drawable.room1_icon,R.drawable.room1);
    val AmeronHotel = listOf(name.get(1),"Shenton Way,Down Town","3","25 meter","25 mins to Subway","Deluxe-RM 500,Superiord-RM 415,Single-RM 300",
        "Housekeeping,Toiletries,Wi-Fi,Refrigerator",R.drawable.room2_icon,R.drawable.room2)
    val DorsertStudioApart = listOf(name.get(2),"City Center","4","28 meter","5 mins to Bus Station","Premier-RM 900,Deluxe-RM 600,Superior-RM 415",
        "Kitchenette,Toiletries,Wi-FI,Refrigerator",R.drawable.room3_icon,R.drawable.room3)
    val TravelodgeApart = listOf(name.get(3),"Harbourfront","3","20 meter","1.5km to City Center","Family Room-RM 600,Deluxe- RM 400","Breakfast,Toiletries,Wi-Fi,Refrigerator",
        R.drawable.room4_icon,R.drawable.room4)
    val CloverApart = listOf(name.get(4),"East-West Coast","2","19 meter","10 km to City Center","Deluxe-RM 450,Superior-RM 370,Single-RM 200","Toiletries,Wi-Fi,Drinking Water",
        R.drawable.room5_icon,R.drawable.room5)
    val WonderloftHostel = listOf(name.get(5),"China Town","3","30 meter","220 meter to public transportation","Premium Room-RM 350,Dormitory Room-RM 160",
        "Wi-Fi,Shower,Laundry",R.drawable.room6_icon,R.drawable.room6)
    lateinit var contacts:ArrayList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        room.add(RoomData(heritageApartment))
        room.add(RoomData(AmeronHotel))
        room.add(RoomData(DorsertStudioApart))
        room.add(RoomData(TravelodgeApart))
        room.add(RoomData(CloverApart))
        room.add(RoomData(WonderloftHostel))

        createHostelRV(this)

    }

    fun createHostelRV(me:Context){
        val rvContact = findViewById<View>(R.id.rv_hostel) as RecyclerView
        contacts = Contact.createContactList(room)
        val adapter = ContactsAdapter(contacts)
        rvContact.adapter = adapter
        rvContact.layoutManager = LinearLayoutManager(me,RecyclerView.VERTICAL,false)
    }
}