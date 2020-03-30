package com.example.iambeta.authentication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity
import com.example.iambeta.mainPage.popUp.FirstPopUp
=======
import com.example.iambeta.mainPage.FeedActivity
import com.example.iambeta.R
>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signin.*


class SignInActivity : AppCompatActivity() {

<<<<<<< HEAD
    //declaring Firebase variables
    private var mAuth: FirebaseAuth? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
=======
    var mAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

<<<<<<< HEAD
        //instantiates Firebase variables
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

        //checks if User is signed in (main activity opens if true)
        if (mAuth!!.currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    //if sign in information is correct, opens main page
=======
        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {  }

    }

>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b
    fun signIn(view: View) {
        mAuth!!.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
<<<<<<< HEAD
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    startActivity(Intent(applicationContext, FirstPopUp::class.java))
                    finish()
=======
                    val intent = Intent(applicationContext, FeedActivity::class.java)
                    startActivity(intent)
>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }

<<<<<<< HEAD
    //checks the information and adds it to the database
=======
>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b
    fun signUp(view: View) {
        mAuth!!.createUserWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext,"User Created", Toast.LENGTH_LONG).show()
                    //intent
                }
            }.addOnFailureListener { exception ->
<<<<<<< HEAD
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }
}
=======
                    Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
    }
}
>>>>>>> 50709f5c5441a93e2a023eb88f8731e8ae5ed83b
