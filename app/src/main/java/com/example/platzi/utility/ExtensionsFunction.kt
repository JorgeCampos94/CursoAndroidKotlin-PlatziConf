package com.example.platzi.utility

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun View.setVisibleOrGone(value:Boolean){
    this.visibility = if(value) View.VISIBLE else View.GONE
}

fun ImageView.setGlide(context: Context, value: String, placeHolder: Int){
    Glide.with(context)
        .load(value)
        .centerCrop()
        .apply(RequestOptions.centerCropTransform())
        .placeholder(placeHolder)
        .error(placeHolder)
        .into(this)
}

fun ImageView.setGlide(context: Context, value: Int?, placeHolder: Int){
    Glide.with(context)
        .load(value)
        .centerCrop()
        .placeholder(placeHolder)
        .error(placeHolder)
        .into(this)
}

fun RecyclerView.smoothSnapToPosition(position: Int, snapMode: Int = LinearSmoothScroller.SNAP_TO_END) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun Activity.changeColorStatusBar(color:Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.window?.let {window->
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
        }
    }
}

fun EditText.showKeyBoard(activity: Activity?){
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this,InputMethodManager.SHOW_IMPLICIT)
}

fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
        val spannableString = SpannableString(this.text)
        for (link in links) {
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    Selection.setSelection((view as TextView).text as Spannable, 0)
                    view.invalidate()
                    link.second.onClick(view)
                }
            }
            val startIndexOfLink = this.text.toString().indexOf(link.first)
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        this.movementMethod = LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
        this.setText(spannableString, TextView.BufferType.SPANNABLE)
}