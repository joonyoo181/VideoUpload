package com.example.iambeta.MainPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.FAQPage.FAQPage
import com.example.iambeta.ProfilePage.ProfilePage
import com.example.iambeta.R

class MainActivity : AppCompatActivity() {

    //Declaring Button Variables
    private var mainToProfilePage: Button? = null
    private var mainToFAQPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing mainToProfilePage Button & Checking if pressed
        mainToProfilePage = findViewById(R.id.Button_mainToProfilePage)
        mainToProfilePage!!.setOnClickListener{
            openProfilePage()
        }

        //Initializing mainToFAQPage Button & Checking if pressed
        mainToFAQPage = findViewById(R.id.Button_mainToFAQPage)
        mainToFAQPage!!.setOnClickListener{
            openFAQPage()
        }
    }

    //Opening Profile Page From Main Page
    private fun openProfilePage(){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
    }

    //Opening FAQ Page From Main Page
    private fun openFAQPage(){
        val intent = Intent(this, FAQPage::class.java)
        startActivity(intent)
    }
}
