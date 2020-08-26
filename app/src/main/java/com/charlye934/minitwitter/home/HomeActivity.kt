package com.charlye934.minitwitter.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.charlye934.minitwitter.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar!!.hide()
        eventsNavigation()
    }

    private fun eventsNavigation(){
        navigationHome.setOnNavigationItemReselectedListener{
            when(it.itemId){
                R.id.navigation_home ->{Toast.makeText(applicationContext, "home",Toast.LENGTH_SHORT).show()}
                R.id.navigation_dashboard ->{Toast.makeText(applicationContext, "dashboard",Toast.LENGTH_SHORT).show()}
                R.id.navigation_notification ->{Toast.makeText(applicationContext, "notification",Toast.LENGTH_SHORT).show()}
            }
        }
    }
}