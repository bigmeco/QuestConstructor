package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.presenter.RegistrationPresenter
import com.bigmeco.questconstructor.views.RegistrationView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*


class RegistrationActivity : MvpAppCompatActivity(), RegistrationView {
    private lateinit var googleSignInClient: GoogleSignInClient

    @InjectPresenter
    lateinit var registrationPresenter: RegistrationPresenter

    @ProvidePresenter
    fun provideSplashPresenter(): RegistrationPresenter {
        return RegistrationPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        singButton.setOnClickListener {
            googleSignInClient = GoogleSignIn.getClient(this,
                    GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build())
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            data?.let { registrationPresenter.signedInAccount(it) }
        }
    }


    override fun signedInAccount(mAuth: FirebaseAuth, credential: AuthCredential) {
        Log.d("sdsdsdds","sdfsdfsdf")
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, StartActivity::class.java))
                        finish()
                    } else {
                    }
                }


    }

    companion object {
        private const val RC_SIGN_IN = 9001
    }

}
