package com.example.a3_t1

import android.app.DatePickerDialog
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import androidx.core.view.setMargins
import java.text.SimpleDateFormat
import java.util.*


class BookingForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_form)
        val hostelName = intent.getStringExtra("hostelName")
        val room = Room()
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
        room.add(RoomData(heritageApartment))
        room.add(RoomData(AmeronHotel))
        room.add(RoomData(DorsertStudioApart))
        room.add(RoomData(TravelodgeApart))
        room.add(RoomData(CloverApart))
        room.add(RoomData(WonderloftHostel))
        val booking = Booking(room.infoApartOrHotel(hostelName.toString()))
        val txt_checkInDate:TextView = findViewById(R.id.txt_checkIn)
        val txt_checkOutDate:TextView = findViewById(R.id.txt_checkOut)
        val txt_in:TextView =  findViewById(R.id.txt_ShowCheckInDate)
        val txt_out:TextView = findViewById(R.id.txt_ShowCheckOutDate)
        val txt_ttl:TextView = findViewById(R.id.txt_totalPrice)
        val cal = Calendar.getInstance()
        val spn_price:Spinner = findViewById(R.id.spn_RoomPrice)
        val btn_book:Button = findViewById(R.id.btn_book)

        btn_book!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                booking.insertApartmentName(hostelName.toString())
                booking.insertCheckInDate(txt_in.text.toString())
                booking.insertCheckOutDate(txt_out.text.toString())
                booking.insertRoomType(spn_price.selectedItem.toString())
                val temp = txt_ttl.text.toString().split(" ").toTypedArray()
                booking.insertTotal(temp.get(1).toString().toInt())
                ShowBookingInformation(booking)
            }
        })

        spn_price.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                UpdatePrice(txt_in,txt_out,booking,spn_price.selectedItem.toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        txt_checkInDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@BookingForm,
                    dateListener(cal,"In"),
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
                    spn_price.setSelection(0)
                    txt_ttl.setText("RM 0.0")
                    btn_book.isEnabled = false
            }
        })

        txt_checkOutDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@BookingForm,
                    dateListener(cal,"Out"),
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
                    spn_price.setSelection(0)
                    txt_ttl.setText("RM 0.0")
                    btn_book.isEnabled = false
            }
        })

        FacilitiesShow(booking)
        RoomPrice(booking)
        roomImageSizeDistance(booking)
    }

    fun ShowBookingInformation(booking:Booking){
        val bookinfo:LinearLayout = findViewById(R.id.ll_content)
        bookinfo.removeAllViews()

        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(160,20,0,20)

        val txt = TextView(this)
        txt.setText(booking.apartmentName)
        txt.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        txt.setTextSize(20f)
        txt.textAlignment = View.TEXT_ALIGNMENT_CENTER
        txt.setTextColor(resources.getColor(R.color.black))
        bookinfo.addView(txt)
        bookinfo.marginLeft
        val txt1 = TextView(this)
        txt1.setText("Your Booking Information")
        txt1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
        txt1.setTextSize(25f)
        txt1.setTextColor(resources.getColor(R.color.white))
        txt1.setBackgroundColor(resources.getColor(R.color.lightblue))
        bookinfo.addView(txt1,params)

        val params_1: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params_1.setMargins(160,0,0,0)

        val info:List<String> = listOf("Room Type: ","Check-In Date: ","Check-Out Date: ","Total Amount: ")
        val info_data:List<String> = listOf(booking.RoomType(),booking.CheckInDate(),booking.CheckOutDate(),"RM " + booking.total().toString())
        for(i in 0..info.size -1){
            val temp = TextView(this)
            temp.setText(info.get(i) + info_data.get(i))
            temp.setTextColor(resources.getColor(R.color.black))
            bookinfo.addView(temp,params_1)
        }


    }

    fun dateListener(cal:Calendar,type:String): DatePickerDialog.OnDateSetListener {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(cal,type)
            }
        }
        return dateSetListener
    }

    fun updateDateInView(cal:Calendar,Type:String) {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val selectedDate:String = sdf.format(cal.getTime()).toString()
        val txt_in:TextView =  findViewById(R.id.txt_ShowCheckInDate)
        val txt_out:TextView = findViewById(R.id.txt_ShowCheckOutDate)

        if(Type == "In"){
            if(checkDate(selectedDate,txt_out.text.toString()) == true){
                txt_in.setText(selectedDate)
            }else{
                txt_in.setText("Invalid Date")
            }
        }else if(Type == "Out"){
            if(checkDate(txt_in.text.toString(),selectedDate) == true){
                txt_out.setText(selectedDate)
            }else {
                txt_out.setText("Invalid Date")
            }
        }
    }

    fun checkDate(DateIn:String,DateOut:String):Boolean{
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        var flag:Boolean = false
        if(DateIn.isEmpty() || DateOut.isEmpty() || DateIn == "Invalid Date" || DateOut == "Invalid Date"){
            flag = true
        }else{
            val dateIn = sdf.parse(DateIn.toString())
            val dateOut = sdf.parse(DateOut.toString())
            val check = dateIn.compareTo(dateOut) // >0(1 > 2), < 0 (1 < 2) , = 0 (1==2)
            if(check > 0 )
                flag = false
            else if( check < 0)
                flag = true
            else if (check == 0)
                flag = true

        }
        return flag
    }

    fun UpdatePrice(txt_in:TextView,txt_out:TextView,booking: Booking,selectedRoom:String){
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        val btn_book:Button = findViewById(R.id.btn_book)
        if (txt_in.text.isNotEmpty() && txt_out.text.isNotEmpty() && txt_out.text != "Invalid Date" && txt_in.text != "Invalid Date" &&
                selectedRoom != "select room"){
            val dateIn = sdf.parse(txt_in.text.toString())
            val dateOut = sdf.parse(txt_out.text.toString())
            val diff = kotlin.math.abs(dateOut.time - dateIn.time)
            val diffDay = diff / (24*60*60*1000)
            val txt_price:TextView = findViewById(R.id.txt_totalPrice)
            val x = (diffDay * booking.generateRoomPrice(selectedRoom)).toString()
            txt_price.setText("RM " + x)
            btn_book.isEnabled = true
        }else{
            btn_book.isEnabled = false
        }

    }

    fun roomImageSizeDistance(booking: Booking){
        val txt_distance:TextView = findViewById(R.id.txt_distance)
        val txt_roomSize:TextView = findViewById(R.id.txt_roomsize)
        val img_room:ImageView = findViewById(R.id.img_room)
        img_room.setImageResource(booking.roomImage())
        txt_distance.setText(booking.distanceInfo())
        txt_roomSize.setText(booking.roomSize())
    }

    fun RoomPrice(booking:Booking){
        val spin_price:Spinner = findViewById(R.id.spn_RoomPrice)
        val x:MutableList<String?> = booking.roomPriceInfo()
        val arrayAdapter:ArrayAdapter<String?> = ArrayAdapter<String?>(this,android.R.layout.simple_list_item_1,x)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin_price.adapter = arrayAdapter
    }

    fun FacilitiesShow(booking:Booking){
        val txt_fac_item:LinearLayout = findViewById(R.id.txt_facilities_item)
        val facilitiesinfo:List<Any> = booking.facilitiesInfo()
        for(i in 0..facilitiesinfo.size-1){
            val txt = TextView(this)
            txt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet,0,0,0)
            txt.setCompoundDrawablePadding(20)
            txt.setText(facilitiesinfo.get(i).toString())
            txt.setTextColor(resources.getColor(R.color.blue))
            txt.setPadding(8,0,8,0)
            txt_fac_item.addView(txt)
        }
    }
}

