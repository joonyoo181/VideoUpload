package com.example.iambeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var mainToProfilePage: Button? = null
    private var mainToFAQPage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainToProfilePage = findViewById(R.id.Button_mainToProfilePage)
        mainToProfilePage!!.setOnClickListener{
            openProfilePage()
        }

        mainToFAQPage = findViewById(R.id.Button_mainToFAQPage)
        mainToFAQPage!!.setOnClickListener{
            openFAQPage()
        }
    }

    private fun openProfilePage(){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
    }

    private fun openFAQPage(){
        val intent = Intent(this, FAQPage::class.java)
        startActivity(intent)
    }
}
