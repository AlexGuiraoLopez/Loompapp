package com.aresudev.loompapp.core.utils.filemanagement

import android.content.Context
import android.widget.ImageView
import com.aresudev.loompapp.R
import com.squareup.picasso.Picasso

class FileManager {
    companion object{
        @kotlin.jvm.Throws(NullPointerException::class)
        fun importImageFromUrl(context: Context, imagePath: String, imageView: ImageView){
            Picasso.with(context)
                .load(imagePath)
                .error(R.drawable.ic_error)
                .into(imageView)
        }
    }
}