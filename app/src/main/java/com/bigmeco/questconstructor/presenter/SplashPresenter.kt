package com.bigmeco.questconstructor.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.views.SplashView
import com.google.firebase.auth.FirebaseAuth


@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    fun scheduleSplashScreen() {
        val auth = FirebaseAuth.getInstance()
        viewState.auth(auth.currentUser!=null)
    }

}