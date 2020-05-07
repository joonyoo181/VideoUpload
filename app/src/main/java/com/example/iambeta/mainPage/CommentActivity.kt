package com.example.iambeta.mainPage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.iambeta.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_comment.*


class CommentActivity : AppCompatActivity() {

    var addcomment : EditText? = null
    var post_me : TextView? = null
    var postid : String? = null
    var publisherid : String? = null
    var FirebaseUser : FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val mToolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(mToolbar)
        getSupportActionBar()?.setTitle("Comments")
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener( View.OnClickListener {

            fun onClick(v: View) {
                finish()
            }
        })

        addcomment = findViewById(R.id.add_comment)
        post_me = findViewById(R.id.post_me)

        postid = intent.getStringExtra("postId")
        publisherid = intent.getStringExtra("userId")

        post_me?.setOnClickListener( View.OnClickListener {
            if (addcomment?.getText().toString().equals("")){
                Toast.makeText(applicationContext, "You can't send empty comment", Toast.LENGTH_SHORT).show()
            } else {

                addComment()
            }
        })

    }

    fun addComment() {
        val firebaseDatabase = FirebaseDatabase.getInstance()

        val reference = firebaseDatabase!!.reference.child("Comments").child(postid!!)
        //val reference = FirebaseDatabase.getInstance().getReference("Comments")!!.child(postid!!)
        val hashMap = HashMap<String, String>()
        hashMap.put("comment", addcomment?.getText().toString())
        hashMap.put("userId", FirebaseAuth.getInstance().getCurrentUser()!!.getUid().toString())

        reference.push().setValue(hashMap)
        addcomment?.setText("")
    }
}
