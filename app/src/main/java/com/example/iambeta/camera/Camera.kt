package com.example.iambeta.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.VideoCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.iambeta.R
import com.example.iambeta.storage.UploadActivity
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

//variables for requesting permission
private const val REQUEST_CODE_PERMISSION = 101
var CAMERA_PERMISSION = Manifest.permission.CAMERA
var RECORD_AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO
private val REQUIRED_PERMISSION = arrayOf(CAMERA_PERMISSION, RECORD_AUDIO_PERMISSION)
val TAG = Camera::class.java.simpleName

class Camera : AppCompatActivity() {

    //declaring variables for stopwatch
    private lateinit var meter: Chronometer

    //declaring variables for camera
    private var recordingStatus: recordingState? = null
    private var flashStatus: flashState? = null
    private var switchStatus: switchState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        //initializing variables for stopwatch
        meter = findViewById(R.id.Chronometer_cameraStopWatchTimer)
        meter.visibility = View.GONE

        //initializing variables for camera
        recordingStatus = recordingState.NOTRECORDING
        flashStatus = flashState.OFF
        switchStatus = switchState.BACK

        //variable for storing the video/recording
        val recordFiles = ContextCompat.getExternalFilesDirs(this, Environment.DIRECTORY_MOVIES)
        val storageDirectory = recordFiles[0]
        val fileStorage = "${System.currentTimeMillis()}.mp4"

        //if record button is clicked
        Button_cameraRecord.setOnClickListener{
            if(recordingStatus == recordingState.NOTRECORDING) {
                recordingStatus = recordingState.RECORDING
                Button_cameraRecord.setImageResource(R.drawable.camera_stop_record_vector)
                Button_cameraToMainPage.visibility = View.GONE
                Button_cameraFlash.visibility = View.GONE
                Button_cameraUpload.visibility = View.GONE
                Button_cameraSwitch.visibility = View.GONE
                meter.visibility = View.VISIBLE
                meter.base = SystemClock.elapsedRealtime()
                meter.start()
                CameraView_cameraPreview.startRecording(File(externalMediaDirs.first(), fileStorage), ContextCompat.getMainExecutor(this), object: VideoCapture.OnVideoSavedCallback {
                    override fun onVideoSaved(file: File) {
                        Log.d(TAG, "onVideoSaved $fileStorage")
                    }

                    override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                        Toast.makeText(applicationContext, "Recording Failed", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "onError $videoCaptureError $message")
                    }
                })
            }else if(recordingStatus == recordingState.RECORDING){
                recordingStatus = recordingState.NOTRECORDING
                Button_cameraRecord.setImageResource(R.drawable.camera_record_vector)
                CameraView_cameraPreview.stopRecording()
                Button_cameraToMainPage.visibility = View.VISIBLE
                Button_cameraFlash.visibility = View.VISIBLE
                Button_cameraUpload.visibility = View.VISIBLE
                Button_cameraSwitch.visibility = View.VISIBLE
                meter.visibility = View.GONE
                meter.stop()
                Toast.makeText(this, "Saved to /internalstorage/Android/media/com.example.iambeta", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Video File Stopped")
            }
        }

        //if flash button is clicked
        Button_cameraFlash.setOnClickListener{
            if(this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                if (flashStatus == flashState.OFF) {
                    Toast.makeText(this, "Flash Turned On", Toast.LENGTH_SHORT).show()
                    flashStatus = flashState.ON
                    Button_cameraFlash.setImageResource(R.drawable.camera_flash_off_vector)
                    CameraView_cameraPreview.enableTorch(true)
                } else if (flashStatus == flashState.ON) {
                    Toast.makeText(this, "Flash Turned Off", Toast.LENGTH_SHORT).show()
                    flashStatus = flashState.OFF
                    Button_cameraFlash.setImageResource(R.drawable.camera_flash_on_vector)
                    CameraView_cameraPreview.enableTorch(false)
                }
            }
        }

        //if switch camera button is clicked
        Button_cameraSwitch.setOnClickListener{
            if(CameraView_cameraPreview.hasCameraWithLensFacing(CameraSelector.LENS_FACING_BACK) ||
                    CameraView_cameraPreview.hasCameraWithLensFacing(CameraSelector.LENS_FACING_FRONT)) {
                if(switchStatus == switchState.BACK){
                    switchStatus = switchState.FRONT
                    Button_cameraFlash.visibility = View.GONE
                    CameraView_cameraPreview.cameraLensFacing = CameraSelector.LENS_FACING_FRONT
                }else{
                    switchStatus = switchState.BACK
                    Button_cameraFlash.visibility = View.VISIBLE
                    CameraView_cameraPreview.cameraLensFacing = CameraSelector.LENS_FACING_BACK
                }
            }else{
                Toast.makeText(applicationContext, "Unable to switch camera", Toast.LENGTH_SHORT).show()
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
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_CODE_PERMISSION -> {
                var allPermissionsGranted = false
                for (result in grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        allPermissionsGranted = false
                        break
                    } else {
                        allPermissionsGranted = true
                    }
                }
                if (allPermissionsGranted) openCamera() else permissionsNotGranted()
            }
        }
    }

    //checking if all permissions are granted
    private fun allPermissionsGranted(): Boolean{
        return ((ActivityCompat.checkSelfPermission(this, CAMERA_PERMISSION)) == PackageManager.PERMISSION_GRANTED
                && (ActivityCompat.checkSelfPermission(this, RECORD_AUDIO_PERMISSION)) == PackageManager.PERMISSION_GRANTED)
    }

    //Inserts a dialog if permission is not granted
    private fun permissionsNotGranted() {
        AlertDialog.Builder(this).setTitle("Permissions required")
            .setMessage("These permissions are required to use this app. Please allow Camera and Audio permissions first")
            .setCancelable(false)
            .setPositiveButton("Grant"){
                    dialog, which -> ActivityCompat.requestPermissions(this, REQUIRED_PERMISSION, REQUEST_CODE_PERMISSION)
            }
            .show()
    }

    //starting camera
    private fun openCamera(){
        CameraView_cameraPreview.bindToLifecycle(this)
    }

    //Enum class to see if the record button is recording or not
    enum class recordingState{
        RECORDING, NOTRECORDING
    }

    //Enum class to see if the flash button is on or not
    enum class flashState{
        ON, OFF
    }

    //Enum class to see if the camera view is front or back
    enum class switchState{
        FRONT, BACK
    }

    override fun onStart() {
        super.onStart()
        //check if all permission has been give, else request permission
        if(allPermissionsGranted()){
            openCamera()
        }else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSION, REQUEST_CODE_PERMISSION)
        }
    }
}