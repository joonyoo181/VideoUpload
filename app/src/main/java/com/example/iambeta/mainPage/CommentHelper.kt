package com.example.iambeta.mainPage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iambeta.R
import com.example.iambeta.mainPage.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class CommentHelper : RecyclerView.Adapter<CommentHelper.ViewHolder> {

    var mContext : Context? = null
    var mComment : List<Comment>? = null

    var firebaseuser : FirebaseUser? = null

    constructor(mContext: Context, mComment: List<Comment>?) {
        this.mContext = mContext
        this.mComment = mComment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHelper.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mComment!!.size
    }

    override fun onBindViewHolder(holder: CommentHelper.ViewHolder, position: Int) {
        firebaseuser = FirebaseAuth.getInstance().currentUser
        val comment: Comment = mComment!!.get(position)
        // getUserInfo

        holder.comment!!.setText(comment.comment)

        holder.comment!!.setOnClickListener( {

            fun onClick(view: View) {
                var intent = Intent(mContext, MainActivity::class.java)
                intent.putExtra("userId", comment.user)
                mContext!!.startActivity(intent)
            }
        })


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image_profile : ImageView? = null
        var comment : TextView? = null
        var username : TextView? = null

        init {
            //image_profile = view.findViewById(R.id.image_profile)
            username = view.findViewById(R.id.username)
            comment = view.findViewById(R.id.comment)
        }
    }

    // Getting user info
    /*
    fun getUserInfor(imageView: ImageView, username: TextView, userId: String) {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val myRef = firebaseDatabase.reference.child("Child").child(userId)
        myRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(datasnapshot: DatabaseError) {

            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                user = datasnapshot.getValue(User.class)
                        Glide.with(mContext)load
            }
        })
    }
    */

}