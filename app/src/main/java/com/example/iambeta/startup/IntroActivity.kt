package com.example.iambeta.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.iambeta.R
import com.example.iambeta.authentication.SignInActivity
import com.example.iambeta.mainPage.MainActivity

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
    }

    fun signin(view: View){
        startActivity(Intent(applicationContext, SignInActivity::class.java))
    }
}