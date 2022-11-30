package com.example.a3_t2

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.DecimalFormat

class order1: Fragment() {
    var totalPrice:Double = 0.00;
    var totalMains:Int = 0;
    var price:Double = 0.00;
    var discount:Double = 0.00;
    var txtPrice : String = ""
    var txtSummary: String = ""
    var txtSide: String = ""
    var txtBeverage: String = ""
    private val main = mutableMapOf<Int,Double>();
    private val sideCost = mutableMapOf<Int,Double>();
    private val beverageCost = mutableMapOf<Int,Double>();
    private val cbBreakfastId:Map<Int,Double> = mapOf(R.id.cb_beefbacon to 6.50,R.id.cb_eggs to 5.00,R.id.cb_omellete to 5.00,
        R.id.cb_pancake to 4.00,R.id.cb_ham to 6.00,R.id.cb_sausage to 6.50);
    private val cbSideId:Map<Int,Double> =  mapOf(R.id.cb_beans to 2.50,R.id.cb_tomato to 1.50,R.id.cb_hashbrown to 2.00,
        R.id.cb_sauteVege to 3.50,R.id.cb_toast to 2.00, R.id.cb_salad to 3.00);
    private val cvSideVegeID:Map<Int,Double> = mapOf(R.id.cb_beans to 2.50,R.id.cb_hashbrown to 2.00,R.id.cb_sauteVege to 3.50,
        R.id.cb_salad to 3.00);
    private val rbBeverageId:Map<Int,Double> = mapOf(R.id.rd_coffee to 4.00,R.id.rd_juice to 5.50, R.id.rd_tea to 4.00,
        R.id.rd_milk to 5.00);
    private var checkPoint:Boolean = true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.order1, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val btnReset: Button = view.findViewById(R.id.btn_reset);
        //val btnOrder: Button = view.findViewById(R.id.btn_order);
        val btn_next:Button = view.findViewById(R.id.btn_next)
        btn_next.setOnClickListener(){
            (context as MainActivity).PassData(txtSummary,txtSide,txtBeverage,txtPrice,order2())

        }

        listenOfMainCB(view)
        listenOfSidesCB(view)
        listenOfTeaRB(view)
    }

    fun listenOfTeaRB(view:View){
        val cof:RadioButton = view.findViewById(R.id.rd_coffee)
        val tea:RadioButton = view.findViewById(R.id.rd_tea)
        val juice:RadioButton = view.findViewById(R.id.rd_juice)
        val milk:RadioButton = view.findViewById(R.id.rd_milk)
        cof.setOnClickListener(){
            selectBeverage(view,cof)
        }
        tea.setOnClickListener(){
            selectBeverage(view,tea)
        }
        juice.setOnClickListener(){
            selectBeverage(view,juice)
        }
        milk.setOnClickListener(){
            selectBeverage(view,milk)
        }

    }

    fun listenOfMainCB(view:View){
        val egg:CheckBox = view.findViewById(R.id.cb_eggs)
        val ome:CheckBox = view.findViewById(R.id.cb_omellete)
        val pan:CheckBox = view.findViewById(R.id.cb_pancake)
        val sau:CheckBox = view.findViewById(R.id.cb_sausage)
        val beef:CheckBox = view.findViewById(R.id.cb_beefbacon)
        val ham:CheckBox = view.findViewById(R.id.cb_ham)
        egg.setOnClickListener(){
            checkedBreakfast(view,egg)
        }
        ome.setOnClickListener(){
            checkedBreakfast(view,ome)
        }
        pan.setOnClickListener(){
            checkedBreakfast(view,pan)
        }
        sau.setOnClickListener(){
            checkedBreakfast(view,sau)
        }
        beef.setOnClickListener(){
            checkedBreakfast(view,beef)
        }
        ham.setOnClickListener(){
            checkedBreakfast(view,ham)
        }
    }

    fun listenOfSidesCB(view: View){
        val beans:CheckBox = view.findViewById(R.id.cb_beans)
        val toma:CheckBox = view.findViewById(R.id.cb_tomato)
        val hash:CheckBox = view.findViewById(R.id.cb_hashbrown)
        val vege:CheckBox = view.findViewById(R.id.cb_sauteVege)
        val toast:CheckBox = view.findViewById(R.id.cb_toast)
        val salad:CheckBox = view.findViewById(R.id.cb_salad)
        beans.setOnClickListener(){
            checkedSides(view,beans)
        }
        toma.setOnClickListener(){
            checkedSides(view,toma)
        }
        hash.setOnClickListener(){
            checkedSides(view,hash)
        }
        vege.setOnClickListener(){
            checkedSides(view,vege)
        }
        toast.setOnClickListener(){
            checkedSides(view,toast)
        }
        salad.setOnClickListener(){
            checkedSides(view,salad)
        }
    }

    fun printReceipe(title:String,order:MutableMap<Int,Double>,ori:MutableMap<Int,Double>,view:View):String{
        var message:String = title;
        for((key,value) in order){
            var temp: CheckBox = view.findViewById(key);
            message += "    " + temp.text.toString() + " - RM "+ori[key]+"\n";
            discount += (ori[key]!! - order[key]!!);
        }
        return message;
    }

    fun checkedBreakfast(view: View,cb:CheckBox){

        var temp:String = "-Main : ";
        if(cb is CheckBox){
            val checked:Boolean = cb.isChecked;
            for((key,value) in cbBreakfastId){
                if(cb.id == key){
                    if(checked){
                        totalPrice += value;
                        totalMains += 1;
                        main[key] = value;
                    }else{
                        totalPrice -= value;
                        totalMains -= 1;
                        main.remove(key);
                    }
                }
            }
        }
        var n:Int = 0;

        for((key,value ) in main){
            n++;
            val x: CheckBox = view.findViewById(key);
            temp += x.text;
            if(n < main.size){
                temp += ", ";
            }

        }
        if(totalMains > 0 && checkPoint){
            checkPoint = false;
            enableDisableSide(true,view)
        }else if(!checkPoint && totalMains == 0){
            temp = "";
            checkPoint = true;
            enableDisableSide(false,view);
            sideCost.clear();
        }
        checkDiscountBeverage()
        updatePrice();
        val df = DecimalFormat("#.##")
        txtPrice = df.format(totalPrice).toString();
        txtSummary = temp;
    }

    private fun updatePrice(){
        val df = DecimalFormat("#.##")
        for((key,value) in sideCost){
            var x:Boolean = cvSideVegeID.containsKey(key);
            if(x){ // exist in choosed side list
                if(totalMains >= 3){ // true means main choose equal or more than 3
                    if(cvSideVegeID[key] == value){ // if both price equal
                        totalPrice -= value;
                        totalPrice += df.format(value*0.8).toDouble();
                        sideCost[key] = df.format(value*0.8).toDouble();
                    }
                }else{
                    if(cvSideVegeID[key] != value){
                        totalPrice -= value;
                        totalPrice += cvSideVegeID[key]!!;
                        sideCost[key] = cvSideVegeID[key]!!;
                    }
                }
            }
        }

    }

    fun checkDiscountBeverage(){
        totalPrice -= price
        val df = DecimalFormat("#.##")
        if(totalPrice >= 20){
            for((key,value) in beverageCost){
                price = df.format(value*0.9).toDouble()
                totalPrice += price
            }
        }else{
            for((key,value) in beverageCost){
                price = value.toDouble()
                totalPrice += price
            }
        }
    }

    fun checkedSides(view: View,cb:CheckBox){
        val df = DecimalFormat("#.##")
        if(cb is CheckBox){
            val checked:Boolean = cb.isChecked;
            val exist:Boolean = sideCost.containsKey(cb.id);
            if(exist){
                totalPrice -= sideCost[cb.id]!!;
                sideCost.remove(cb.id);
            }else{
                for((ley,value) in cbSideId){
                    if(cb.id == ley){
                        if(checked){
                            if(totalMains >= 3 && cb.id != R.id.cb_toast && cb.id != R.id.cb_tomato){
                                totalPrice += df.format(value*0.8).toDouble();
                                sideCost[ley] = df.format(value*0.8).toDouble();
                            }else{
                                totalPrice += value;
                                sideCost[ley] = value;
                            }
                        }
                    }
                }
            }
        }
        var temp:String = "-Side : ";
        var n:Int = 0;
        for((key,value) in sideCost){
            n++;
            var x = view.findViewById<TextView>(key);
            temp += x.text;
            if(n != sideCost.size){
                temp += ", ";
            }
        }
        txtSide = temp;
        txtPrice = totalPrice.toString();
    }

    private fun enableDisableSide(temp:Boolean,view: View){
        for((key,value) in cbSideId){
            var x: CheckBox = view.findViewById(key);
            if(temp){
                x.isEnabled = true;
            }else{
                x.isChecked = false;
                x.isEnabled = false;
                totalPrice = price;
            }
        }
        txtSide = "";
    }

    fun selectBeverage(view: View,cb:RadioButton){
        var x:Int = 0;
        if(cb is RadioButton){
            var checked:Boolean = cb.isChecked;
            for((key,value) in rbBeverageId){
                if(cb.id == key){
                    if(checked){
                        if(price == 0.0){
                            price = discountBeverage(value);

                        }else{
                            totalPrice -= price;
                            price = discountBeverage(value);
                        }
                        x = key;
                        totalPrice += price;
                    }
                }
            }
        }
        val rd: RadioButton = view.findViewById(x);
        txtBeverage = "-Beverage : " + rd.text;
        txtPrice = String.format("%.2f", totalPrice).toDouble().toString()
        beverageCost.clear();
        beverageCost[x] = rbBeverageId[x]!!;
    }

    fun discountBeverage(value:Double):Double{
        var x:Double = value
        val df = DecimalFormat("#.##")
        if(totalPrice >= 20){
            x = x*0.9
        }
        return String.format("%.2f", x).toDouble()
    }
}