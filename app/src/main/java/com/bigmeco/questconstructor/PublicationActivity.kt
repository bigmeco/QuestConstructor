package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_publication.*
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.util.Log


class PublicationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)
            tabDots.setupWithViewPager(pager, true)
        if (pager != null) {
            pager.adapter = StylesPagerAdapter(this)
        }
        pager.addOnPageChangeListener(object : OnPageChangeListener {


            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                Log.d("wwwwww",position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }
}
