package com.example.iambeta.mainPage;

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.VideoView
import com.example.iambeta.mainPage.CommentActivity
import com.example.iambeta.R
import com.example.iambeta.profilePage.ProfilePage
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view.view.*


class PostClass (private val useremail: ArrayList<String>,
                 private val userImage: ArrayList<Map<String, String>>,
                 private val userComment: ArrayList<String>,
                 private val context: Activity,
                 private val userpost: ArrayList<String>,
                 private val userlist: ArrayList<String>) :
                            ArrayAdapter<String>(context, R.layout.custom_view, useremail)  {

    fun getComments(postid : String, comments: TextView) {
        var firebaseDatabase : FirebaseDatabase? = null
        var myRef : DatabaseReference? = null

        firebaseDatabase = FirebaseDatabase.getInstance()
        myRef = firebaseDatabase!!.reference.child("Comments").child(postid)
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(datasnapshot: DatabaseError) {

            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                comments.setText("View All " + datasnapshot.childrenCount + " Comments")
            }
        })
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_view, null, true)

        if(userpost.size != 0) {
            getComments(userpost[position]!!, customView.comments)
        }

        if (useremail.size != 0){
            customView.customUserName.text = useremail[position]
        }

        if (userComment.size != 0){
            customView.customCommentText.text = userComment[position]
        }

        if (userImage.size != 0 && userImage.getOrNull(position) != null && userImage[position].containsKey("image")){
            Picasso.get().load(userImage[position].get("image")).into(customView.customImageView)
        } else {
            customView.customImageView.setImageBitmap(null)
        }

        if (userImage.size != 0 && userImage.getOrNull(position) != null && userImage[position].containsKey("video")) {
            val uri: Uri = Uri.parse(userImage[position].get("video"))
            customView.videoView.setVideoURI(uri)
            //TODO THIS STARTS PLAYING VIDEOS STOP IT
            customView.videoView.start()
        } else {
            customView.videoView.setVideoURI(null)
        }

        customView.comment.setOnClickListener {
            if (userlist.size != 0 && userpost.size != 0){
                var intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("userId", userlist[position])
                intent.putExtra("postId", userpost[position])
                context.startActivity(intent)
            }
        }

        customView.comments.setOnClickListener {
            if (userlist.size != 0 && userpost.size != 0){
                var intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("userId", userlist[position])
                intent.putExtra("postId", userpost[position])
                context.startActivity(intent)
            }
        }

        return customView
    }
}
