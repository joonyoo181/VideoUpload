package com.example.iambeta.mainPage;

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.iambeta.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view.view.*

class PostClass (private val useremail: ArrayList<String>,
                        private val userImage: ArrayList<String>,
                        private val userComment: ArrayList<String>,
                        private val context: Activity) :
                            ArrayAdapter<String>(context, R.layout.custom_view, useremail)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_view, null, true)

        if (useremail.size != 0){
            customView.customUserName.text = useremail[position]
        }

        if (userComment.size != 0){
            customView.customUserName.text = userComment[position]
        }

        if (userImage.size != 0){
            Picasso.get().load(userImage[position]).into(customView.customImageView)
        }

        return customView
    }
}
