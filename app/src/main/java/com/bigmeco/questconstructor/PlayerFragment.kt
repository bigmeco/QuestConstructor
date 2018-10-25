package com.bigmeco.questconstructor

import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_player.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.widget.NumberPicker




class PlayerFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        genrePicker.maxValue = 9
        genrePicker.minValue = 0
        setDividerColor(genrePicker, resources.getColor(R.color.pleyColor2))
        setDividerColor(timerPicker, resources.getColor(R.color.pleyColor2))
        setDividerColor(ratingPicker, resources.getColor(R.color.pleyColor2))

        listGame.layoutManager = LinearLayoutManager(activity)
        var t = arrayListOf<ObjectScreen>()
        t.add(ObjectScreen())
        listGame.adapter = GameAdapter(t) { objectScreen: ObjectScreen, i: Int ->

        }



        val animFlterOpen = AnimationUtils.loadAnimation(activity, R.anim.filter_anim)
        animFlterOpen.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                imageFilter.visibility = View.GONE
                filterLayout.setBackgroundColor( resources.getColor(R.color.pleyColor))
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {
            }
        })
        val animFlterClose = AnimationUtils.loadAnimation(activity, R.anim.filter_anim_beack)
        animFlterClose.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {

            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {
                imageFilter.visibility = View.VISIBLE
                filterLayout.setBackgroundResource(R.drawable.full_filter_item)
            }
        })
        val animScale = AnimationUtils.loadAnimation(activity, R.anim.menu_anim)
        animScale.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                imageMenu.visibility = View.GONE
                menuLayout.setBackgroundColor( resources.getColor(R.color.pleyColor))
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {
            }
        })
        val animScaleBeack = AnimationUtils.loadAnimation(activity, R.anim.menu_anim_beack)
        animScaleBeack.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {
                imageMenu.visibility = View.VISIBLE
                menuLayout.setBackgroundResource(R.drawable.full_menu_item)

            }
        })

        imageBaeckF.setOnClickListener {
            filterLayout.visibility = View.INVISIBLE
            filterLayout.startAnimation(animFlterClose)


        }
        imageFilter.setOnClickListener {
            filterLayout.visibility = View.VISIBLE
            filterLayout.startAnimation(animFlterOpen)
        }

        imageOkF.setOnClickListener {
            filterLayout.visibility = View.INVISIBLE
            filterLayout.startAnimation(animFlterClose)
        }

        imageMenu.setOnClickListener {
            menuLayout.visibility = View.VISIBLE
            menuLayout.startAnimation(animScale)
        }
        imageBeack.setOnClickListener {
            menuLayout.visibility = View.INVISIBLE
            menuLayout.startAnimation(animScaleBeack)
        }

        imageOk.setOnClickListener {
            menuLayout.visibility = View.INVISIBLE
            menuLayout.startAnimation(animScaleBeack)
        }


    }

    private fun setDividerColor(picker: NumberPicker, color: Int) {

        val pickerFields = NumberPicker::class.java.declaredFields
        for (pf in pickerFields) {
            if (pf.name == "mSelectionDivider") {
                pf.isAccessible = true
                try {
                    val colorDrawable = ColorDrawable(color)
                    pf.set(picker, colorDrawable)
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                } catch (e: Resources.NotFoundException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

                break
            }
        }
    }
}
