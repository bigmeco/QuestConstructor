package com.bigmeco.questconstructor.screen.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_publication.*
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.presenter.PublicationPresenter
import com.bigmeco.questconstructor.screen.adapter.StylesPagerAdapter
import com.bigmeco.questconstructor.views.PublicationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.realm.Realm


class PublicationActivity : MvpAppCompatActivity(), PublicationView {


    @InjectPresenter
    lateinit var publicationPresenter: PublicationPresenter

    @ProvidePresenter
    fun providePublicationPresenter(): PublicationPresenter {
        return PublicationPresenter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
        tabDots.setupWithViewPager(pager, true)
        if (pager != null) {
            pager.adapter = StylesPagerAdapter(this)
        }
        publicationPresenter.getCopyProject(intent.getIntExtra("idProject", 0))
    }

    override fun releaseOK() {
        Log.d("wwwwww", pager.currentItem.toString())

    }

    override fun releaseError() {
        Log.d("wwwwww", pager.currentItem.toString())

    }


    override fun getCopyProject(objectProject: ObjectProject) {
        Log.e("dfghdfgdfgdfgdf",objectProject.releaseKey+"  dxcgbxcvg")
        textOk.setOnClickListener {
            publicationPresenter.releaseProject(pager.currentItem, objectProject)
            finish()


        }
        textBeac.setOnClickListener {
           finish()
        }
    }
}
