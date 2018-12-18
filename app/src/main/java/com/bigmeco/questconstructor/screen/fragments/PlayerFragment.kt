package com.bigmeco.questconstructor.screen.fragments

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_player.*
import android.view.animation.AnimationUtils
import android.view.animation.Animation
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.widget.NumberPicker
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.data.InfoProject
import com.bigmeco.questconstructor.presenter.PlayerListPresenter
import com.bigmeco.questconstructor.screen.activity.InfoQuestActivity
import com.bigmeco.questconstructor.screen.adapter.GameAdapter
import com.bigmeco.questconstructor.views.PlayerListView


class PlayerFragment : MvpAppCompatFragment(), PlayerListView {


    @InjectPresenter
    lateinit var playerListPresenter: PlayerListPresenter


    @ProvidePresenter
    fun providePlayerListPresenter(): PlayerListPresenter {
        return PlayerListPresenter()
    }

    var genre: String? = null
    var timer: String? = null
    var rating: Int? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayGenre = resources.getStringArray(R.array.greetings)
        genrePicker.minValue = 0
        genrePicker.maxValue = arrayGenre.size - 1
        genrePicker.displayedValues = arrayGenre

        val arrayTime = resources.getStringArray(R.array.duration)
        timerPicker.minValue = 0
        timerPicker.maxValue = arrayTime.size - 1
        timerPicker.displayedValues = arrayTime

        val arrayRating = resources.getStringArray(R.array.rating)
        ratingPicker.minValue = 0
        ratingPicker.maxValue = arrayRating.size - 1
        ratingPicker.displayedValues = arrayRating


        setDividerColor(genrePicker, resources.getColor(R.color.pleyColor2))
        setDividerColor(timerPicker, resources.getColor(R.color.pleyColor2))
        setDividerColor(ratingPicker, resources.getColor(R.color.pleyColor2))

        listGame.layoutManager = LinearLayoutManager(activity)

        playerListPresenter.setList(updateList)


        val animFlterOpen = AnimationUtils.loadAnimation(activity, R.anim.filter_anim)
        animFlterOpen.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                imageFilter.visibility = View.GONE
                filterLayout.setBackgroundColor(resources.getColor(R.color.pleyColor))
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
                menuLayout.setBackgroundColor(resources.getColor(R.color.pleyColor))
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
            playerListPresenter.setList(updateList)
            filterLayout.visibility = View.INVISIBLE
            filterLayout.startAnimation(animFlterClose)
        }
        imageFilter.setOnClickListener {
            filterLayout.visibility = View.VISIBLE
            filterLayout.startAnimation(animFlterOpen)
        }

        imageOkF.setOnClickListener {
            playerListPresenter.setListFilter(updateList,arrayTime[timerPicker.value], ratingPicker.value)
            filterLayout.visibility = View.INVISIBLE
            filterLayout.startAnimation(animFlterClose)
        }

        imageMenu.setOnClickListener {
            menuLayout.visibility = View.VISIBLE
            menuLayout.startAnimation(animScale)

        }
        imageBeack.setOnClickListener {
            //setList()
            menuLayout.visibility = View.INVISIBLE
            menuLayout.startAnimation(animScaleBeack)
        }

        imageOk.setOnClickListener {
            playerListPresenter.setListGenre(updateList,arrayGenre[genrePicker.value])
            menuLayout.visibility = View.INVISIBLE
            menuLayout.startAnimation(animScaleBeack)
        }


    }
    override fun getList() {
//To change body of created functions use File | Settings | File Templates.
    }

    override fun getListGenre(s: String) {
 //To change body of created functions use File | Settings | File Templates.
    }

    override fun getListFilter(s: String, i: Int) {
 //To change body of created functions use File | Settings | File Templates.
    }



   val updateList = fun(lecturesPojos: ArrayList<InfoProject>) {
        listGame.adapter = GameAdapter(lecturesPojos) { infoProject: InfoProject, i: Int ->
            val intent = Intent(activity, InfoQuestActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val bundle = Bundle()
            bundle.putSerializable("value", infoProject)
            intent.putExtras(bundle)
            startActivity(intent)
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
