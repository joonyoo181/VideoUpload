package com.example.iambeta.camera

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.util.Size
import android.view.TextureView
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.iambeta.R
import com.example.iambeta.storage.UploadActivity
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

//variables for requesting permission
private const val REQUEST_CODE_PERMISSION = 10
private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
val tag = Camera::class.java.simpleName

//SupressLint used to ignore methods that show error due to camerax bugs
@SuppressLint("RestrictedApi")
class Camera : AppCompatActivity() {

    //declaring variables for stopwatch
    private lateinit var meter: Chronometer

    //declaring variables for recording videos
    private lateinit var preview: Preview
    private lateinit var cameraPreview: TextureView
    private lateinit var recordButton: com.google.android.material.floatingactionbutton.FloatingActionButton
    private lateinit var flashButton: com.google.android.material.floatingactionbutton.FloatingActionButton
    private var recordingStatus: recordingState? = null
    private var flashStatus: flashState? = null
    private lateinit var videoCapture: VideoCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        //initializing variables for stopwatch
        meter = findViewById(R.id.Chronometer_cameraStopWatchTimer)
        meter.visibility = View.GONE

        //initializing variables for recording videos
        cameraPreview = findViewById(R.id.TextureView_cameraPreview)
        recordButton = findViewById(R.id.Button_cameraRecord)
        flashButton = findViewById(R.id.Button_cameraFlash)
        recordingStatus = recordingState.NOTRECORDING
        flashStatus = flashState.OFF

        //variable for storing the video/recording
        val file = File(externalMediaDirs.first(), "${System.currentTimeMillis()}.mp4")

        //if record button is clicked
        recordButton.setOnClickListener{
            if(recordingStatus == recordingState.NOTRECORDING) {
                recordingStatus = recordingState.RECORDING
                recordButton.setImageResource(R.drawable.camera_stop_record_vector)
                Button_cameraToMainPage.visibility = View.GONE
                Button_cameraFlash.visibility = View.GONE
                Button_cameraUpload.visibility = View.GONE
                meter.visibility = View.VISIBLE
                meter.base = SystemClock.elapsedRealtime()
                meter.start()
                videoCapture.startRecording(file, object:VideoCapture.OnVideoSavedListener{
                    //saved to: /internalstorage/Android/media/com.example.iambeta
                    override fun onVideoSaved(file: File?) {
                        Log.i(tag, "Video File: $file")
                    }

                    override fun onError(useCaseError: VideoCapture.UseCaseError?, message: String?, cause: Throwable?){
                        Log.i(tag, "Video Error: $message")
                    }
                })
            }else if(recordingStatus == recordingState.RECORDING){
                recordingStatus = recordingState.NOTRECORDING
                recordButton.setImageResource(R.drawable.camera_record_vector)
                videoCapture.stopRecording()
                Button_cameraToMainPage.visibility = View.VISIBLE
                Button_cameraFlash.visibility = View.VISIBLE
                Button_cameraUpload.visibility = View.VISIBLE
                meter.visibility = View.GONE
                meter.stop()
                Toast.makeText(this, "Saved to /internalstorage/Android/media/com.example.iambeta", Toast.LENGTH_SHORT).show()
                Log.i(tag, "Video File Stopped")
            }
        }

        //if flash button is click
        flashButton.setOnClickListener{
            if(this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                if (flashStatus == flashState.OFF) {
                    Toast.makeText(this, "Flash Turned On", Toast.LENGTH_SHORT).show()
                    flashStatus = flashState.ON
                    flashButton.setImageResource(R.drawable.camera_flash_off_vector)
                    preview.enableTorch(true)
                } else if (flashStatus == flashState.ON) {
                    Toast.makeText(this, "Flash Turned Off", Toast.LENGTH_SHORT).show()
                    flashStatus = flashState.OFF
                    flashButton.setImageResource(R.drawable.camera_flash_on_vector)
                    preview.enableTorch(false)
                }
            }
        }
    }

    //Opening MainPage from Camera Page
    fun cameraToMainPage(view: View){
        finish()
    }

    //Opening Upload Page from Camera Page
    fun cameraToUploadPage(view: View){
        startActivity(Intent(applicationContext, UploadActivity::class.java))
    }

    //returns the result of requesting permission (i.e. failed obtaining permission)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
        if( requestCode == REQUEST_CODE_PERMISSION){
            if(allPermissionsGranted()){
                cameraPreview.post{startCamera()}
            }else{
                Toast.makeText(this, "Permission(s) not granted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    //checking if all permissions are granted
    private fun allPermissionsGranted(): Boolean{
        for(permission in REQUIRED_PERMISSION){
            if(ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED){
                return false
            }
        }
        return true
    }

    //starting camera
    private fun startCamera(){
        CameraX.unbindAll()

        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(640,480))
            setLensFacing(CameraX.LensFacing.BACK)
        }.build()
        preview = Preview(previewConfig)

        val videoCaptureConfig = VideoCaptureConfig.Builder().apply{
            setTargetRotation(cameraPreview.display.rotation)
        }.build()

        videoCapture = VideoCapture(videoCaptureConfig)

        preview.setOnPreviewOutputUpdateListener{
            cameraPreview.surfaceTexture = it.surfaceTexture
        }

        CameraX.bindToLifecycle(this as LifecycleOwner, videoCapture, preview)
    }

    //Enum class to see if the record button is recording or not
    enum class recordingState{
        RECORDING, NOTRECORDING
    }

    //Enum class to see if the flash button is on or not
    enum class flashState{
        ON, OFF
    }

    override fun onStart() {
        super.onStart()
        //check if all permission has been give, else request permission
        if(allPermissionsGranted()){
            cameraPreview.post{startCamera()}
        }else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSION, REQUEST_CODE_PERMISSION)
        }
    }
}