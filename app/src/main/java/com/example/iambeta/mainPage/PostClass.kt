package com.example.iambeta.mainPage;

import android.app.Activity
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.iambeta.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view.view.*


class PostClass (private val useremail: ArrayList<String>,
                 private val userImage: ArrayList<Map<String, String>>,
                 private val userDescription: ArrayList<String>,
                 private val context: Activity) :
                            ArrayAdapter<String>(context, R.layout.custom_view, useremail)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_view, null, true)

        if (useremail.size != 0){
            customView.customUserName.text = useremail[position]
        }

        if (userDescription.size != 0){
            customView.customCommentText.text = userDescription[position]
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

        return customView
    }
}
