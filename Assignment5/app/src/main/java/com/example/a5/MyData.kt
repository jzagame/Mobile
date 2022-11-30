package com.example.a5

data class MyData(val id:Int,val item:String,val details:String,val quantity:String,val size:String,val urgent:String,val buy:String,val date:String){

    fun getPicture():Int{
        var pic:Int
        if(urgent == "false"){
            pic = R.drawable.buy
        }else{
            pic = R.drawable.urgent
        }
        return pic
    }

    fun getChecked():Boolean{
        var flag:Boolean
        if(urgent == "false"){
            flag = false
        }else{
            flag = true
        }
        return flag
    }

    fun getPicture2():Int{
        return R.drawable.bought
    }

    fun getPosition():Int{
        var x:Int = 3
        if(size == "Default"){
            x = 0
        }else if(size == "Small"){
            x = 1
        }else if(size == "Medium"){
            x = 2
        }
        return x
    }
}
