package com.example.iambeta.authentication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity
import com.example.iambeta.mainPage.popUp.FirstPopUp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_signin.*


class SignInActivity : AppCompatActivity() {

    private var passwordVisibilityStatus: PasswordVisibility? = null

    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    //Google Sign In Variables
    lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1
    lateinit var gso:GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        createGoogleRequest()

        setGooglePlusButtonText(Button_signinGoogle, "Sign In Using Google")

        passwordVisibilityStatus = PasswordVisibility.INVISIBLE

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        //Setting the text of Google Sign In Button
        try {
            (Button_signinGoogle.getChildAt(0) as TextView).text = "SIGN IN USING GOOGLE"
        } catch (e: ClassCastException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        //If google sign in button is clicked
        Button_signinGoogle.setOnClickListener{
            signInGoogle()
        }
    }

    //sign in using email
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
                            finishAffinity()
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

    //sign in using google
    fun signInGoogle(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun signInFaceBook(view: View){}

    fun forgotPassword(view: View){
        startActivity(Intent(applicationContext, ForgotPasswordActivity::class.java))
    }

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

    //request user's google details or information
    private fun createGoogleRequest(){
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    //authenticate google acount
    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Failed to authenticate google acc", Toast.LENGTH_SHORT).show()
                }
            }
    }

    //send google information to firebase
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "Failed to send google information to firebase", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    //set Google Sign In Button Text
    protected fun setGooglePlusButtonText(signInButton: SignInButton, buttonText: String){
        for(x in 0 until signInButton.childCount) run {
            val v: View = signInButton.getChildAt(x)

            if (v is TextView) {
                v.text = buttonText
                return
            }
        }
    }

    enum class PasswordVisibility{
        VISIBLE, INVISIBLE
    }
}