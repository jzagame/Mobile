package com.example.a4_t1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.io.InputStream
import java.net.URL

class fragPhone: Fragment() {
    private lateinit var recyclerViewAdapter: rcAdapter
    private lateinit var recyclerView: RecyclerView
    var ImageList: ArrayList<Int> = arrayListOf()
    var NameList:ArrayList<String> = arrayListOf()
    var SpecList:ArrayList<String> = arrayListOf()
    var CameraList:ArrayList<String> = arrayListOf()
    var CPUList:ArrayList<String> = arrayListOf()
    var URLList:ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var x:View =inflater.inflate(R.layout.rcview, container, false)
        getData()
        recyclerView = x.findViewById(R.id.rcViewStagged)

        // setting recyclerView layoutManager
        val layoutManager: RecyclerView.LayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter = rcAdapter(x.context,ImageList,NameList,SpecList,CameraList,CPUList,URLList)

        // setting recycle view adapter
        recyclerView.adapter = recyclerViewAdapter
        return x;
    }

    private fun getData(){
        val txt:InputStream = this.resources.openRawResource(R.raw.task1_data)
        val buffer = txt.bufferedReader()
        var line = buffer.readLine();
        var flag = false;
        while(line != null){
            val temp = line!!.split(":").toTypedArray()
            if(temp[0] == "Phone" || temp[0] == "Tablet"){
                if(temp[0] == "Phone"){
                    NameList.add(temp[1])
                    flag = true
                }else{
                    flag = false
                }
            }else if(temp[0] == "Chipset" && flag){
                CPUList.add(temp[1])
            }else if(temp[0] == "Memory" && flag){
                SpecList.add(temp[1])
            }else if(temp[0] == "Camera" && flag) {
                CameraList.add(temp[1])
            }else if(temp[0] == "Image" && flag){
                val temp1 = temp[1]!!.split(".").toTypedArray()
                val id:Int = resources.getIdentifier(temp1[0],"drawable",requireActivity().packageName)
                ImageList.add(id)
            }else if(temp[0] == "Url" && flag){
                URLList.add(temp[1]+":"+temp[2])
            }
            line = buffer.readLine()
        }
    }
}