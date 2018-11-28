package com.bigmeco.questconstructor.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bigmeco.questconstructor.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }


    private fun scheduleSplashScreen() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }


    }

}