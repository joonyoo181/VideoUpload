package com.example.iambeta.mainPage.popUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.example.iambeta.R

class ThirdPopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_three)

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

    //open PopTwo from PopThree
    fun popThreeToPopTwo(view: View){
        startActivity(Intent(applicationContext, SecondPopUp::class.java))
        finish()
    }

    //open PopFour from PopThree
    fun popThreeToPopFour(view: View){
        startActivity(Intent(applicationContext, FourthPopUp::class.java))
        finish()
    }
}
