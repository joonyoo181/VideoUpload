package com.example.iambeta.mainPage.popUp

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R

class FirstPopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_one)

        //Setting the layout over another
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width: Int = dm.widthPixels
        val height: Int = dm.heightPixels

        window.setLayout((width*.8).toInt(),(height*.7).toInt())
    }

    //Back button cannot be pressed during this activity
    override fun onBackPressed(){
    }

    //open PopTwo from PopOne
    fun popOneToPopTwo(view: View){
        startActivity(Intent(applicationContext, SecondPopUp::class.java))
        finish()
    }
}
