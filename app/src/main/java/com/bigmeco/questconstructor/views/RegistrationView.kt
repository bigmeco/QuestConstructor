package com.bigmeco.questconstructor.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth

@StateStrategyType(AddToEndStrategy::class)
interface RegistrationView : MvpView {
    fun signedInAccount(data: FirebaseAuth, account: AuthCredential)

}