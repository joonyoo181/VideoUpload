package com.example.iambeta.FAQPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.MainPage.MainActivity
import com.example.iambeta.R

class FAQPage : AppCompatActivity() {

    //Declaring Button Variables
    private var faqToMainPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq_page)

        ///Initializing faqToMainPage Button & Checking if pressed
        faqToMainPage = findViewById(R.id.Button_faqToMainPage)
        faqToMainPage!!.setOnClickListener{
            openMainPage()
        }
    }

    //Opening Main Page From FAQ Page
    private fun openMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
