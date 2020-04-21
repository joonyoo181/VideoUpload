package com.example.iambeta.profilePage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.authentication.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
    }

    //Signs out the user
    fun profileLogOut(view: View){
        try{
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(applicationContext, "Successfully Logged Out!", Toast.LENGTH_LONG).show()
            startActivity(Intent(applicationContext, SignInActivity::class.java))
            finish()
        }catch (e: Exception){
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    //Opening FAQ Page from Profile Page
    fun profileToFAQPage(view: View){
        startActivity(Intent(applicationContext, FAQPage::class.java))
    }

    //Opening Main Page from Profile Page
    fun profileToMainPage(view: View){
        finish()
    }
}
