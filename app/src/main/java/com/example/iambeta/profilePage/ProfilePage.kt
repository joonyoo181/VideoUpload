package com.example.iambeta.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity

class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
    }

    //Opening FAQ Page from Profile Page
    fun profileToFAQPage(view: View){
        val intent = Intent(this, FAQPage::class.java)
        startActivity(intent)
        finish()
    }

    //Opening Main Page from Profile Page
    fun profileToMainPage(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
