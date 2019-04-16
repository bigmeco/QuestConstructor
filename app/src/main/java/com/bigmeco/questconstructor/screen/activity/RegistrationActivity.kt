package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
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
    fun provideRegistrationPresenter(): RegistrationPresenter {
        return RegistrationPresenter()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        googleSignInClient = GoogleSignIn.getClient(this,
                GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build())
        singButton.setOnClickListener {
            singButton.isEnabled = false
            progressBar.visibility= View.VISIBLE
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        textPrivacy.setOnClickListener {
            val url = "https://github.com/bigmeco/QuestConstructor/edit/master/privacy_policy.md"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("sdsdsdds","RC_SIGN_IN")

        if (requestCode == RC_SIGN_IN) {
            data?.let { registrationPresenter.signedInAccount(it) }
        }
        singButton.isEnabled = true
        progressBar.visibility= View.INVISIBLE
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
