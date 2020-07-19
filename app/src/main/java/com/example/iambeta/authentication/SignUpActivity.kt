package com.example.iambeta.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Toast
import com.example.iambeta.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*

class SignUpActivity : AppCompatActivity() {

    private var passwordVisibilityStatus: PasswordVisibility? = null

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        passwordVisibilityStatus = PasswordVisibility.INVISIBLE

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

    }

    fun signUp(view: View) {
        val email = emailText.text.toString().trim()
        val password = passwordText.text.toString().trim()

        when {
            TextUtils.isEmpty(email) -> {
                Toast.makeText(applicationContext, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(applicationContext, "Please enter your password", Toast.LENGTH_SHORT).show()
            }
            else -> {
                mAuth!!.createUserWithEmailAndPassword(
                    emailText.text.toString(),
                    passwordText.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "User Created", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(applicationContext, SignInActivity::class.java))
                            finish()
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(
                            applicationContext,
                            exception.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }
    }

    fun signUpGoogle(view: View) {}

    fun signUpFaceBook(view: View) {}

    fun setPasswordVisibility(view: View){
        if(passwordVisibilityStatus == PasswordVisibility.INVISIBLE){
            Button_passwordVisible.setBackgroundResource(R.drawable.signin_password_invisible_vector)
            passwordText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordVisibilityStatus = PasswordVisibility.VISIBLE
        }else if(passwordVisibilityStatus == PasswordVisibility.VISIBLE){
            Button_passwordVisible.setBackgroundResource(R.drawable.signin_password_visible_vector)
            passwordText.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordVisibilityStatus = PasswordVisibility.INVISIBLE
        }
    }


    enum class PasswordVisibility{
        VISIBLE, INVISIBLE
    }
}