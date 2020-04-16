package com.example.iambeta.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity
import com.example.iambeta.mainPage.popUp.FirstPopUp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*


class SignInActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        if(mAuth!!.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

    }

    fun signIn(view: View) {
        val email = emailText.text.toString().trim()
        val password = passwordText.text.toString().trim()

        when {
            TextUtils.isEmpty(email) -> {
                Toast.makeText(applicationContext, "Please enter your email", Toast.LENGTH_SHORT)
                    .show()
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(applicationContext, "Please enter your password", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                mAuth!!.signInWithEmailAndPassword(
                    emailText.text.toString(),
                    passwordText.text.toString()
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            startActivity(Intent(applicationContext, FirstPopUp::class.java))
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
                            //intent
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

    fun forgotPassword(view: View){
        startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
    }
}