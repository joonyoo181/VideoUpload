package com.example.iambeta.mainPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.authentication.SignInActivity
import com.example.iambeta.camera.Camera
import com.example.iambeta.profilePage.ProfilePage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Opening Camera from Main Page
    fun mainToCamera(view: View){
        val intent = Intent(this, Camera::class.java)
        startActivity(intent)
        finish()
    }

    //Opening Profile Page from Main Page
    fun mainToProfilePage(view: View){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
        finish()
    }

    //Opening SignIn Page from Main Page
    fun mainToSignInPage(view: View) {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}