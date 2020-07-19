package com.example.iambeta.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.iambeta.R
import com.example.iambeta.authentication.SignInActivity
import com.example.iambeta.authentication.SignUpActivity
import com.example.iambeta.mainPage.MainActivity
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        mAuth = FirebaseAuth.getInstance()
    }

    fun signup(view: View){
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
    }

    fun signin(view: View){
        startActivity(Intent(applicationContext, SignInActivity::class.java))
    }

    override fun onStart() {
        super.onStart()

        if(mAuth!!.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}