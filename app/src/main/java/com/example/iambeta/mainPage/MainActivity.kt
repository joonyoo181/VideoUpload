package com.example.iambeta.mainPage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.camera.Camera
import com.example.iambeta.profilePage.ProfilePage

class MainActivity : AppCompatActivity() {

    //Declaring Button Variables
    private var mainToCamera: Button? = null
    private var mainToChatPage: Button? = null
    private var mainVideoComments: Button? = null
    private var mainToDiscoverPage: Button? = null
    private var mainToProfilePage: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing mainToCamera Button & Checking if pressed
        mainToCamera = findViewById(R.id.Button_mainToCamera)
        mainToCamera!!.setOnClickListener{
            openCamera()
        }

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

    //Opening Camera from Main Page
    private fun openCamera(){
        val intent = Intent(this, Camera::class.java)
        startActivity(intent)
        finish()
    }

    //Opening Profile Page from Main Page
    private fun openProfilePage(){
        val intent = Intent(this, ProfilePage::class.java)
        startActivity(intent)
        finish()
    }
}
