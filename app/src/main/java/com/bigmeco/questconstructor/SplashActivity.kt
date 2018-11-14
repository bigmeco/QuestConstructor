 package com.bigmeco.questconstructor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

 class SplashActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }


    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        val auth =FirebaseAuth.getInstance()


        Handler().postDelayed(
                {
                    FirebaseAuth.AuthStateListener {
                        if(it.currentUser!=null){
                            startActivity(Intent(this, StartActivity::class.java))
                            finish()
                        } else {
                            startActivity(Intent(this, RegistrationActivity::class.java))
                            finish()
                        }
                    }
                            if(auth.currentUser!=null){
                                startActivity(Intent(this, StartActivity::class.java))
                                finish()
                            } else {
                                startActivity(Intent(this, RegistrationActivity::class.java))
                                finish()
                            }
                   // getRouteToAppropriatePage()

                },
                splashScreenDuration
        )
    }

    private fun getSplashScreenDuration() = 1000L

    private fun getRouteToAppropriatePage() {
//        launch(UI) {
//            when (mSplashActivityVM.isUserLoggedIn()) {
//                true -> {
//                    startActivity(Intent(application, MenuActivity::class.java))
//                    finish()
//                }
//                false -> {
//                    startActivity(Intent(application, LoginActivity::class.java))
//                    finish()
//                }
//            }
//        }
    }



    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }
}