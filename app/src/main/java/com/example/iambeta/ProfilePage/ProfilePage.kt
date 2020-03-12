package com.example.iambeta.ProfilePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.MainPage.MainActivity
import com.example.iambeta.R

class ProfilePage : AppCompatActivity() {

    //Declaring Button Variables
    private var profileToFAQPage: Button? = null
    private var profileToLogOut: Button? = null
    private var profileToEditPage: Button? = null
    private var profileToMainPage: Button? = null
    private var profileToDiscoverPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        //Initializing profileToFAQPage Button & Checking if pressed
        profileToFAQPage = findViewById(R.id.Button_profileToFAQPage)
        profileToFAQPage!!.setOnClickListener {
            openFAQPage()
        }

        //Initializing profileLogOut Button
        profileToLogOut = findViewById(R.id.Button_profileLogOut)

        //Initializing profileEditPage Button
        profileToEditPage = findViewById(R.id.Button_profileToEditPage)

        //Initializing profileToMainPage Button & Checking if pressed
        profileToMainPage = findViewById(R.id.Button_profileToMainPage)
        profileToMainPage!!.setOnClickListener {
            openMainPage()
        }

        //Initializing profileToDiscoverPage Button & Checking if pressed
        profileToDiscoverPage = findViewById(R.id.Button_profileToMainPage)
    }

    //Opening FAQ Page from Profile Page
    private fun openFAQPage(){
        val intent = Intent(this, FAQPage::class.java)
        startActivity(intent)
    }

    //Opening Main Page from Profile Page
    private fun openMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
