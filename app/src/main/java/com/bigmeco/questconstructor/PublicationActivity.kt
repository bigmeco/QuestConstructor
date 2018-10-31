package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_publication.*
import android.support.v4.view.ViewPager
import android.view.View
import android.support.design.widget.TabLayout


class PublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
            tabDots.setupWithViewPager(pager, true)
    }
}
