package com.example.iambeta.camera

import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.iambeta.R

class camera: AppCompatActivity() {

    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        camera = getCameraInstance()

    }

    private fun getCameraInstance(): Camera? {
        return try {
            Camera.open()
        }
    }
}
