package com.bigmeco.questconstructor.presenter

import android.content.Intent
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.bigmeco.questconstructor.views.RegistrationView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@InjectViewState
class RegistrationPresenter : MvpPresenter<RegistrationView>() {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun signedInAccount( data: Intent) {
        Log.d("sdsdsdds","signedInAccount")
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                viewState.signedInAccount(mAuth, credential)
            }
        } catch (e: ApiException) {

        }


    }
}
