package com.example.beachapp.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.beachapp.R
import com.example.beachapp.databinding.ActivitySplashScreenBinding
import com.example.beachapp.loginAndSignup.LoginActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@SplashScreen, R.layout.activity_splash_screen)
        val animation = AnimationUtils.loadAnimation(this@SplashScreen, R.anim.splash_screen_animation)
        binding.imageView.startAnimation(animation)

        Handler().postDelayed(
            Runnable {
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
                finish()
            }, 800
        )

    }
}