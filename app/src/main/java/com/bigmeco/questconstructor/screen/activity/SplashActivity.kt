package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.presenter.SplashPresenter
import com.bigmeco.questconstructor.views.SplashView
import io.realm.Realm

class SplashActivity : MvpAppCompatActivity(), SplashView {

    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    @ProvidePresenter
    fun provideSplashPresenter(): SplashPresenter {
        return SplashPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashPresenter.scheduleSplashScreen()
        Realm.init(applicationContext)
    }


    override fun auth(auth: Boolean) {
        if (auth) {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }

}
