package com.example.iambeta.mainPage

import android.widget.VideoView

class Content{

    private var title: String? = null
    private var video: VideoView? = null
    private var user: String? = null
    private var description: String? = null
    private var comments: List<String>? = null

    fun Content( titleP: String?, videoP: VideoView?, userP: String?, descriptionP: String?, commentsP: List<String>?){
        title = titleP
        video = videoP
        user = userP
        description = descriptionP
        comments = commentsP
    }
}