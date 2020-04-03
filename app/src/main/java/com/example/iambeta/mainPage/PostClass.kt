package com.example.iambeta.MainPage;

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.iambeta.R
import kotlinx.android.synthetic.main.custom_view.view.*

class PostClass (private val useremail: ArrayList<String>,
                        private val userImage: ArrayList<String>,
                        private val userComment: ArrayList<String>,
                        private val context: Activity) :
                            ArrayAdapter<String>(context, R.layout.custom_view, useremail)  {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_view, null, true)

        //TODO FIX
        //customView.customUserName.text = useremail[position]
        //customView.customCommentText.text = userComment[position]

        return customView
    }
}
