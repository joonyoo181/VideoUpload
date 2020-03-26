package com.example.iambeta.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.authentication.SignInActivity
import com.example.iambeta.mainPage.MainActivity
import com.google.firebase.auth.FirebaseAuth

class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
    }

    //Signs out the user
    fun profileLogOut(view: View){
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(applicationContext,"Successfully Logged Out!", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    //Opening FAQ Page from Profile Page
    fun profileToFAQPage(view: View){
        startActivity(Intent(this, FAQPage::class.java))
        finish()
    }

    //Opening Main Page from Profile Page
    fun profileToMainPage(view: View){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
