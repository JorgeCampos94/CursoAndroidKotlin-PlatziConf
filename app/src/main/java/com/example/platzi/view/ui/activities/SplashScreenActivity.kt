package com.example.platzi.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.example.platzi.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        loadAnimationScreen()
    }

    private fun loadAnimationScreen(){
        val intent = Intent(this, MainActivity::class.java)
        val loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.animation_splash_screen)
        iv_splash_screen_logo_platzi_conf.startAnimation(loadAnimation)
        loadAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }
            override fun onAnimationEnd(animation: Animation?) {
                startActivity(intent)
                finish()
            }
            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
    }
}