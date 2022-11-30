package com.example.a2_t2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var totalPrice:Double = 0.00;
    var totalMains:Int = 0;
    var price:Double = 0.00;
    var discount:Double = 0.00;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val btnReset:Button = findViewById(R.id.btn_reset);
        val btnOrder:Button = findViewById(R.id.btn_order);
        val txtPrice :TextView = findViewById(R.id.txt_cost);
        val txtSummary:TextView = findViewById(R.id.txt_summary);
        val txtSide:TextView  = findViewById(R.id.txt_side);
        val txtBeverage:TextView = findViewById(R.id.txt_beverage);
        var title:String = "";
        btnReset.setOnClickListener(){
            totalPrice = 0.00;
            totalMains = 0;
            discount = 0.00;
            checkPoint = true;
            sideCost.clear();
            main.clear();
            price = 0.00;
            for((key,value) in cbBreakfastId){
                val temp:CheckBox = findViewById(key);
                temp.isChecked = false;
            }
            for((key,value) in rbBeverageId){
                val temp:RadioButton = findViewById(key);
                temp.isChecked = false;
            }
            txtSummary.text = "";
            txtSide.text = "";
            txtBeverage.text = "";
            txtPrice.text = "0.00";
            enableDisableSide(false);
        }
        btnOrder.setOnClickListener(){
            val dialogBuilder = AlertDialog.Builder(this);
            var message:String = "";

            if(main.isNotEmpty()){
                title = "Main\n============================\n";
                message += printReceipe(title,main, cbBreakfastId as MutableMap<Int, Double>);
            }else{
                message = "Please select at least one mains for your order\n";
            }
            if (sideCost.isNotEmpty()){
                title = "\nSide\n============================\n";
                message += printReceipe(title,sideCost,cbSideId as MutableMap<Int, Double>);
            }
            if(beverageCost.isNotEmpty()){
                message += "\nBeverage\n============================\n";
                for((key,value) in beverageCost){
                    var temp:RadioButton = findViewById(key);
                    message += "    " + temp.text.toString() + " - RM $value\n";
                }
            }
            if(main.isNotEmpty()){
                var d1 = String.format("%.2f",discount);
                message += "============================\n";
                message += "Total: RM $totalPrice (- RM $d1)";
            }
            // set message of alert dialog
            dialogBuilder.setMessage(message)
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Confirm", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Your Order")
            // show alert dialog
            alert.show()
        }
    }

    fun printReceipe(title:String,order:MutableMap<Int,Double>,ori:MutableMap<Int,Double>):String{
        var message:String = title;
        for((key,value) in order){
            var temp:CheckBox = findViewById(key);
            message += "    " + temp.text.toString() + " - RM "+ori[key]+"\n";
            discount += (ori[key]!! - order[key]!!);
        }
        return message;
    }

    fun checkedBreakfast(view: View){

        val txtPrice :TextView = findViewById(R.id.txt_cost);
        val txtSummary:TextView = findViewById(R.id.txt_summary);
        var temp:String = "-Main : ";
        if(view is CheckBox){
            val checked:Boolean = view.isChecked;
            for((key,value) in cbBreakfastId){
                if(view.id == key){
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
            val x:CheckBox = findViewById(key);
            temp += x.text;
            if(n < main.size){
                temp += ", ";
            }

        }
        if(totalMains > 0 && checkPoint){
            checkPoint = false;
            enableDisableSide(true);
        }else if(!checkPoint && totalMains == 0){
            temp = "";
            checkPoint = true;
            enableDisableSide(false);
            sideCost.clear();
        }
        updatePrice();
        txtPrice.text = totalPrice.toString();
        txtSummary.text = temp;

    }

    private fun updatePrice(){
        for((key,value) in sideCost){
            var x:Boolean = cvSideVegeID.containsKey(key);
            if(x){ // exist in choosed side list
                if(totalMains >= 3){ // true means main choose equal or more than 3
                    if(cvSideVegeID[key] == value){ // if both price equal
                        totalPrice -= value;
                        totalPrice += (value*0.8);
                        sideCost[key] = (value*0.8);
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

    fun checkedSides(view:View){
        if(view is CheckBox){
            val checked:Boolean = view.isChecked;
            val exist:Boolean = sideCost.containsKey(view.id);
            if(exist){
                totalPrice -= sideCost[view.id]!!;
                sideCost.remove(view.id);
            }else{
                for((ley,value) in cbSideId){
                    if(view.id == ley){
                        if(checked){
                            if(totalMains >= 3 && view.id != R.id.cb_toast && view.id != R.id.cb_tomato){
                                totalPrice += (value*0.8);
                                sideCost[ley] = (value*0.8);
                            }else{
                                totalPrice += value;
                                sideCost[ley] = value;
                            }
                        }
                    }
                }
            }
        }
        val txtSide:TextView  = findViewById(R.id.txt_side);
        val txtPrice :TextView = findViewById(R.id.txt_cost);
        var temp:String = "-Side : ";
        var n:Int = 0;
        for((key,value) in sideCost){
            n++;
            var x = findViewById<TextView>(key);
            temp += x.text;
            if(n != sideCost.size){
                temp += ", ";
            }
        }
        txtSide.text = temp;
        txtPrice.text = totalPrice.toString();
    }

    private fun enableDisableSide(temp:Boolean){
        for((key,value) in cbSideId){
            var x:CheckBox = findViewById(key);
            if(temp){
                x.isEnabled = true;
            }else{
                x.isChecked = false;
                x.isEnabled = false;
                totalPrice = price;
            }
        }
        val txtSide:TextView  = findViewById(R.id.txt_side);
        txtSide.text = "";
    }

    fun selectBeverage(view:View){
        var x:Int = 0;
        if(view is RadioButton){
            var checked:Boolean = view.isChecked;
            for((key,value) in rbBeverageId){
                if(view.id == key){
                    if(checked){
                        if(price == 0.0){
                            price = value;

                        }else{
                            totalPrice -= price;
                            price = value;
                        }
                        x = key;
                        totalPrice += value;
                    }
                }
            }
        }
        val rd:RadioButton = findViewById(x);
        val txtPrice :TextView = findViewById(R.id.txt_cost);
        val txtBeverage:TextView = findViewById(R.id.txt_beverage);
        txtBeverage.text = "-Beverage : " + rd.text;
        txtPrice.text = totalPrice.toString();
        beverageCost.clear();
        beverageCost[x] = rbBeverageId[x]!!;
    }
}