package com.example.a2_t1

import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.DrawableWrapper
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.toColor
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val img_player1: ImageView = findViewById(R.id.img_player1);
        val img_player2: ImageView = findViewById(R.id.img_player2);
        val txt2: TextView = findViewById(R.id.txt_throw2);
        val txt1: TextView = findViewById(R.id.txt_throw);
        // Checks the orientation of the screen
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
                val txt_p1 : TextView = findViewById(R.id.textView);
                val txt_p2 : TextView = findViewById(R.id.textView6);
                txt_p1.setTextColor(Color.parseColor("#000000"));
                txt_p2.setTextColor(Color.parseColor("#FF0000"))
                val num = (1..6).random();
                txt1.text = num.toString();
                img_player1.setImageResource(imageGenerate(num));
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
                val txt_p1 : TextView = findViewById(R.id.textView);
                val txt_p2 : TextView = findViewById(R.id.textView6);
                txt_p2.setTextColor(Color.parseColor("#000000"));
                txt_p1.setTextColor(Color.parseColor("#FF0000"))
                val num = (1..6).random();
                txt2.text = num.toString();
                img_player2.setImageResource(imageGenerate(num));
                reset(img_player1,img_player2,txt1,txt2);
        }
    }
    private fun imageGenerate(num:Int) : Int{
        val temp = when(num){
            1 -> R.drawable.dice_one;
            2 -> R.drawable.dice_two;
            3 -> R.drawable.dice_three;
            4 -> R.drawable.dice_four;
            5 -> R.drawable.dice_five;
            6 -> R.drawable.dice_six;
            else -> { 0 }
        }
        return temp;
    }

    private fun reset(p1:ImageView,p2:ImageView,p1throw:TextView,p2throw:TextView){
        p1.setImageResource(R.drawable.dice_one);
        p2.setImageResource(R.drawable.dice_one);
        val p1score: TextView = findViewById(R.id.txt_score);
        val p2score: TextView = findViewById(R.id.txt_score2);
        var score1 = Integer.parseInt(p1score.text.toString());
        var score2 = Integer.parseInt(p2score.text.toString());
        var scorethrow1 = Integer.parseInt(p1throw.text.toString());
        var scorethrow2 = Integer.parseInt(p2throw.text.toString());
        if(scorethrow1 == scorethrow2 ){
            score1 += scorethrow1;
            score2 += scorethrow2;
        }else if(scorethrow1 > scorethrow2){
            score1 += scorethrow1;
        }else{
            score2 += scorethrow2;
        }
        p1score.text = score1.toString();
        p2score.text = score2.toString();
    }
}