package com.example.iambeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FAQPage : AppCompatActivity() {

    private var faqToMainPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq_page)

        faqToMainPage = findViewById(R.id.Button_faqToMainPage)
        faqToMainPage!!.setOnClickListener{
            openMainPage()
        }
    }

    private fun openMainPage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
