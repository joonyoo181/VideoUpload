package com.example.iambeta.MainPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.iambeta.ProfilePage.ProfilePage
import com.example.iambeta.R

class MainActivity : AppCompatActivity() {

    //Declaring Button Variables
    var mainToChatPage: Button? = null
    var mainVideoComments: Button? = null
    var mainToDiscoverPage: Button? = null
    var mainToProfilePage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing mainToChatPage Button
        mainToChatPage = findViewById(R.id.Button_mainToChatPage)

        //Initializing mainVideoComments Button
        mainVideoComments = findViewById(R.id.Button_mainVideoComments)

        //Initializing mainToDiscoverPage Button
        mainToDiscoverPage = findViewById(R.id.Button_mainToDiscoverPage)

        //Initializing mainToProfilePage Button & Checking if pressed
        mainToProfilePage = findViewById(R.id.Button_mainToProfilePage)
        mainToProfilePage!!.setOnClickListener{
            openProfilePage()
        }
    }

    //Opening Profile Page from Main Page
    private fun openProfilePage(){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
    }
}
