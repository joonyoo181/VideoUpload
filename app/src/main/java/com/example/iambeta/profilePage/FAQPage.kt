package com.example.iambeta.profilePage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.R

class FAQPage : AppCompatActivity() {

    //Declaring Button Variables
    private var faqToProfilePage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq_page)

        ///Initializing faqToProfilePage Button & Checking if pressed
        faqToProfilePage = findViewById(R.id.Button_faqToMainPage)
        faqToProfilePage!!.setOnClickListener{
            openProfilePage()
        }
    }

    //Opening Profile Page From FAQ Page
    private fun openProfilePage(){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
    }
}
