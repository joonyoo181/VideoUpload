package com.example.iambeta.mainPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.iambeta.R
import com.example.iambeta.camera.Camera
import com.example.iambeta.profilePage.ProfilePage
import com.example.iambeta.storage.UploadActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Declaring ListView objects
    var useremailFromFB : ArrayList<String> = ArrayList()
    var userImageFromFB : ArrayList<Map<String,String>> = ArrayList()
    var userCommentFromFB : ArrayList<String> = ArrayList()
    var userLikesFromFB: ArrayList<Map<String, Boolean>> = ArrayList()
    var firebaseDatabase: FirebaseDatabase? = null
    var myRef : DatabaseReference? = null
    var adapter : PostClass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtaining data from firebase for ListView
        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.getReference()

        adapter = PostClass(useremailFromFB, userImageFromFB, userCommentFromFB, userLikesFromFB, this)

        listView.adapter = adapter

        getDataFromFirebase()
    }

    //ListView inflating menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_post, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //ListView
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add_post){
            val intent = Intent(applicationContext, UploadActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    //Opening Camera from Main Page
    fun mainToCamera(view: View){
        startActivity(Intent(applicationContext, Camera::class.java))
    }

    //Opening Profile Page from Main Page
    fun mainToProfilePage(view: View){
        startActivity(Intent(applicationContext, ProfilePage::class.java))
    }

    //Obtaining data from firebase
    private fun getDataFromFirebase() {
        val newReference = firebaseDatabase!!.getReference("Posts")
        newReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                val beep = p0.children.toString()
                Log.d("BLAH", beep)


                adapter!!.clear()
                userImageFromFB.clear()
                userCommentFromFB.clear()
                useremailFromFB.clear()
                for (user in p0.children) {
                    for (snapshot in user.children) {

                        val hashMap = snapshot.value as HashMap<String, String>

                        if (hashMap.size > 0) {

                            val email = hashMap["useremail"]
                            val comment = hashMap["comment"]
                            val image = hashMap["downloadUrl"]

                            if (email != null) {
                                useremailFromFB.add(email)
                            }

                            if (comment != null) {
                                userCommentFromFB.add(comment)
                            }

                            if (image != null) {
                                val map = mapOf("image" to image)
                                userImageFromFB.add(map)
                            }

                            adapter!!.notifyDataSetChanged()

                        }
                    }
                }
            }
        })
    }
}