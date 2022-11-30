package com.example.a5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.mainBNV) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.btn_complete -> loadFragment(CompleteFragment())
                R.id.btn_home -> loadFragment(HomeFragment())
                R.id.btn_urgent -> loadFragment(UrgentFragment())
            }
            true
        }
        val fab: View = findViewById(R.id.btn_float)
        fab.setOnClickListener { view ->
            val intent  = Intent(this,ShoppingCart::class.java)
            //val intent  = Intent(this,ViewActivity::class.java)
            startActivity(intent)
        }
    }

    fun refreshFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.detach(fragment).attach(fragment).commit()
    }

    fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}