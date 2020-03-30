package com.example.iambeta.mainPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.camera.Camera
import com.example.iambeta.mainPage.popUp.FirstPopUp
import com.example.iambeta.profilePage.ProfilePage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Opening Camera from Main Page
    fun mainToCamera(view: View){
        startActivity(Intent(applicationContext, Camera::class.java))
        finish()
    }

    //Opening Profile Page from Main Page
    fun mainToProfilePage(view: View){
        startActivity(Intent(applicationContext, ProfilePage::class.java))
        finish()
    }

    //Opening PopUps
    fun openPopOne(view: View){
        startActivity(Intent(applicationContext, FirstPopUp::class.java))
    }
}
