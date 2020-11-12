package com.example.platzi.utility

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.platzi.R

fun ImageView.setGlide(context: Context, value: String? , boolean : Boolean){
    if (boolean){
        Glide.with(context)
            .load(value)
            .centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .into(this)
    }else{
        Glide.with(context)
            .load(value)
            .centerCrop()
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .into(this)
    }
}

fun ImageView.setGlide(context: Context, value: Int?){
    Glide.with(context)
        .load(value)
        .centerCrop()
        .placeholder(R.drawable.img_place_holder)
        .error(R.drawable.img_place_holder)
        .into(this)
}