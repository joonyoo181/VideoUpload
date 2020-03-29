package com.example.iambeta.mainPage.popUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.example.iambeta.R

class FifthPopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_five)

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

    //open PopFour from PopFive
    fun popFiveToPopFour(view: View){
        startActivity(Intent(applicationContext, FourthPopUp::class.java))
        finish()
    }

    //open PopSix from PopFive
    fun popFiveToPopSix(view: View){
        startActivity(Intent(applicationContext, SixthPopUp::class.java))
        finish()
    }
}
