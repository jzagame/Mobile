package com.example.a3_t1

class Booking(val listOfRoomInfo:List<Any>) {
    var RoomType:String = "";
    var apartmentName:String = "";
    var CheckInDate:String = "";
    var CheckOutDate:String = "";
    var total:Int = 0;

    fun apartmentName():String{
        return apartmentName;
    }

    fun RoomType():String{
        return RoomType
    }

    fun CheckInDate():String{
        return CheckInDate
    }

    fun CheckOutDate():String{
        return CheckOutDate
    }

    fun total():String{
        return total.toString()
    }

    fun insertApartmentName(temp:String){
        apartmentName = temp
    }

    fun insertRoomType(temp:String){
        RoomType = temp
    }

    fun insertCheckInDate(temp:String){
        CheckInDate = temp
    }

    fun insertCheckOutDate(temp:String){
        CheckOutDate = temp
    }

    fun insertTotal(temp:Int){
        total = temp
    }

    fun roomPriceInfo():MutableList<String?>{
        var temp = listOfRoomInfo.get(5).toString()
        var temp_2 = temp.split(",").toTypedArray()
        var temp_list:MutableList<String?> = ArrayList()
        temp_list.add(0,"select room");
        for(i in 0 .. temp_2.size -1){
            temp_list.add(i+1,temp_2.get(i).toString());
        }
        return temp_list
    }

    fun facilitiesInfo():List<Any>{
        var temp = listOfRoomInfo.get(6).toString()
        var temp_2 = temp.split(",").toTypedArray()
        var temp_list:List<Any> = listOfNotNull()
        for(i in 0..temp_2.size -1){
            temp_list += temp_2.get(i)
        }
        return temp_list
    }

    fun generateRoomPrice(priceWithName:String):Int{
        val temp = priceWithName.split("-").toTypedArray()
        val temp_1 = temp.get(1).split(" ").toTypedArray()
        val price:Int = temp_1.get(1).toInt()
        return price
    }

    fun roomImage():Int{
        return listOfRoomInfo.get(8).toString().toInt()
    }

    fun distanceInfo():String{
        return listOfRoomInfo.get(4).toString()
    }

    fun roomSize():String{
        return "Room Size : " + listOfRoomInfo.get(3).toString()
    }
}