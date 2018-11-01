package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_publication.*


class PublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
            tabDots.setupWithViewPager(pager, true)
        if (pager != null) {
            pager.adapter = StylesPagerAdapter(this)
        }
    }
}
