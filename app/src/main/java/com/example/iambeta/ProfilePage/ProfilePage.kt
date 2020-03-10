package com.example.iambeta.ProfilePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.MainPage.MainActivity
import com.example.iambeta.R

class ProfilePage : AppCompatActivity() {

    //Declaring Button Variables
    private var profileToEditPage: Button? = null
    private var profileToMainPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        //Initializing profileToEditPage Button
        profileToEditPage = findViewById(R.id.Button_profileToEditPage)

        //Initializing profileToMainPage Button & Checking if pressed
        profileToMainPage = findViewById(R.id.Button_profileToMainPage)
        profileToMainPage!!.setOnClickListener {
            openMainPage()
        }
    }

    private fun openMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
