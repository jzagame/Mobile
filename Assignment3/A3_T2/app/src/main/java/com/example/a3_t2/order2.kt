package com.example.a3_t2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class order2 : Fragment() {
    var main:String = ""
    var side:String = ""
    var beverage:String = ""
    var cost:String = ""
    var DiningMethodCost:Double = 0.00
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            var view: View = inflater.inflate(R.layout.order2, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        main = arguments?.getString("main").toString()
        side = arguments?.getString("side").toString()
        beverage = arguments?.getString("beverage").toString()
        cost = arguments?.getString("cost").toString()
        val txt_main:TextView = view.findViewById(R.id.txt_summary)
        val txt_side:TextView = view.findViewById(R.id.txt_side)
        val txt_beverage:TextView = view.findViewById(R.id.txt_beverage)
        val txt_cost:TextView = view.findViewById(R.id.txt_cost)
        txt_main.setText(main)
        txt_side.setText(side)
        txt_beverage.setText(beverage)
        txt_cost.setText(cost)
        listenOfDiningMethodRB(view)
    }

    fun listenOfDiningMethodRB(view:View){
        var temp_cost:Double = 0.00
        val rb_dinein:RadioButton = view.findViewById(R.id.rb_dinein)
        val rb_takeaway:RadioButton = view.findViewById(R.id.rb_takeaway)
        val rb_delivery:RadioButton = view.findViewById(R.id.rb_delivery)
        rb_dinein.setOnClickListener(){
            DeliveryMethodPriceAddMinus(view,0.00,"Dine In")
        }
        rb_takeaway.setOnClickListener(){
            DeliveryMethodPriceAddMinus(view,1.50,"Take Away")
        }
        rb_delivery.setOnClickListener(){
            DeliveryMethodPriceAddMinus(view,4.00,"Home Delivery")
        }
    }

    fun DeliveryMethodPriceAddMinus(view:View,price:Double,DineMethod:String){
        var temp_cost:Double = cost.toDouble()
        if(DiningMethodCost == 0.00){
            DiningMethodCost = price
        }else{
            temp_cost = cost.toDouble()
            temp_cost -= DiningMethodCost
            DiningMethodCost = price
        }
        temp_cost += price
        cost = temp_cost.toString()
        val txt_cost:TextView = view.findViewById(R.id.txt_cost)
        txt_cost.setText(cost)
        val txt_dine:TextView = view.findViewById(R.id.txt_dineMethod)
        txt_dine.setText("-Dining Method : " + DineMethod)
    }

}