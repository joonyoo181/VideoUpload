package com.example.iambeta.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R

class FAQPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq_page)
    }

    //Opening Profile Page From FAQ Page
    fun faqToProfilePage(view: View){
        startActivity(Intent(applicationContext, ProfilePage::class.java))
        finish()
    }
}
