package com.example.a4_t2_ver2

data class img(val name:String,val thumb:String,val img:String,val type:String)

class MyData {
    val data:ArrayList<img> = arrayListOf()
    var url:String = ""
    fun add(temp:img){
        data.add(temp)
    }

    fun specific(temp:String):ArrayList<img>{
        val t:ArrayList<img> = arrayListOf()
        for(i in 0..data.size-1){
            if(data[i].type.compareTo(temp) == 0 || temp.compareTo("all") == 0){
                t.add(data[i])
            }
        }
        return t
    }

    fun Url(temp:String){
        url = temp
    }

    fun Url():String{
        return url
    }

    fun allImg():ArrayList<img>{
        return data
    }

    fun dropdown():ArrayList<String>{
        val temp:ArrayList<String> = arrayListOf()
        temp.add("All")
        temp.add("Animal")
        temp.add("Plant")

        return temp
    }
}