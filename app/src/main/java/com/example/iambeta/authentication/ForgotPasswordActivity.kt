package com.example.iambeta.authentication

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        mAuth = FirebaseAuth.getInstance()
    }

    //Sending password request email
    fun forgotPasswordButton(view: View){
        val email = EditText_forgotPasswordEmailInput.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter your email", Toast.LENGTH_SHORT).show()
        } else {
            mAuth!!.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Check the email to reset your password!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }.addOnFailureListener{ exception ->
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
    }

    //Opening SignIn Activity from this activity
    fun forgotPasswordToSignIn(view: View){
        finish()
    }
}
