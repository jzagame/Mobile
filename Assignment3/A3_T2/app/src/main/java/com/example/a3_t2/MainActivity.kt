package com.example.a3_t2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    val first = order1()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.fl,first).addToBackStack("ok").commit()
    }

    fun PassData(main:String,side:String,Beverage:String,cost:String,fragment: Fragment){
        val bundle = Bundle()
        bundle.putString("main",main)
        bundle.putString("side",side)
        bundle.putString("beverage",Beverage)
        bundle.putString("cost",cost)
        SwitchPage(fragment,bundle)
    }

    fun SwitchPage(fragment:Fragment,bundle: Bundle){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.hide(first)
        fragment.arguments = bundle
        ft.add(R.id.fl,fragment).addToBackStack("ok").commit()
    }
}