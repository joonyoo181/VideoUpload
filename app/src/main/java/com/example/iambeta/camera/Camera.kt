package com.example.iambeta.camera

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity
import io.fotoapparat.Fotoapparat
import io.fotoapparat.configuration.CameraConfiguration
import io.fotoapparat.log.logcat
import io.fotoapparat.log.loggers
import io.fotoapparat.parameter.ScaleType
import io.fotoapparat.selector.back
import io.fotoapparat.selector.front
import io.fotoapparat.selector.off
import io.fotoapparat.selector.torch
import io.fotoapparat.view.CameraView
import java.io.File

class Camera : AppCompatActivity() {

    //Declaring Fotoapparat Variables
    private var fotoapparat: Fotoapparat? = null

    //Declaring Variables for Managing States (ex. flash on/off)
    private var fotoapparatState: FotoapparatState? = null
    private var cameraStatus: CameraState? = null
    private var flashState: FlashState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        //Initializing fotoapparat Variable
        createFotoApparant()

        //Initializing Variables for Managing States (ex. Flash on/off)
        fotoapparatState = FotoapparatState.OFF
        cameraStatus = CameraState.BACK
        flashState = FlashState.OFF
    }

    //Opening Main Page from Camera Page
    fun cameraToMainPage(view: View){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    //Turning the flash on/off
    fun cameraFlash(view: View){
        fotoapparat?.updateConfiguration(
            CameraConfiguration(
                flashMode = if(flashState == FlashState.ON) off() else torch()
            )
        )

        if(flashState == FlashState.ON) flashState = FlashState.OFF else flashState = FlashState.ON
    }

    //Checking Permission and Taking a Picture and Storing it inside the android device
    fun cameraTakePicture(view: View) {
        val filename = "text.png"
        val sd = Environment.getExternalStorageDirectory() //Need to Change This
        val dest = File(sd, filename)
        if(hasNoCameraPermissions()){
            requestCameraPermission()
        }else{
            fotoapparat?.takePicture()?.saveToFile(dest)
            Toast.makeText(applicationContext, "Picture Taken", Toast.LENGTH_SHORT).show()
        }
    }

    //Switch between front and back camera when the button is pressed
    fun cameraSwitch(view: View){
        fotoapparat?.switchTo(
            lensPosition = if(cameraStatus == CameraState.BACK) front() else back(),
            cameraConfiguration = CameraConfiguration()
        )
        if(cameraStatus == CameraState.BACK) cameraStatus = CameraState.FRONT else cameraStatus = CameraState.BACK
    }

    //Check user's permission of camera
    private fun hasNoCameraPermissions(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    //Requests User's permission of camera
    private fun requestCameraPermission(){
        val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        permissions.let { ActivityCompat.requestPermissions(this, it, 0) }
    }

    //Create fotoapparat variable
    private fun createFotoApparant(){
        val cameraView = findViewById<CameraView>(R.id.CameraView_cameraView)

        fotoapparat = Fotoapparat(
            context = this,
            view = cameraView,
            scaleType = ScaleType.CenterCrop,
            lensPosition = back(),
            logger = loggers(
                logcat()
            ),
            cameraErrorCallback = { error ->
                println("Recorder errors: $error")
            }
        )
    }

    //Start the preview of the Camera
    override fun onStart(){
        super.onStart()
        if( hasNoCameraPermissions() ){
            requestCameraPermission()
        }else{
            fotoapparat?.start()
            fotoapparatState = FotoapparatState.ON
        }
    }

    //Resume the preview of the Camera
    override fun onResume(){
        super.onResume()
        if(!hasNoCameraPermissions() && fotoapparatState == FotoapparatState.OFF){
            startActivity(Intent(baseContext, Camera::class.java))
            finish()
        }
    }

    //Stop the preview of the Camera
    override fun onStop(){
        super.onStop()
        fotoapparat?.stop()
        FotoapparatState.OFF
    }

    //Enum class for FotoappratStatus
    enum class FotoapparatState{
        ON, OFF
    }

    //Enum class for CameraState
    enum class CameraState{
        FRONT, BACK
    }

    //Enum class for FlashState
    enum class FlashState{
        ON, OFF
    }
}
