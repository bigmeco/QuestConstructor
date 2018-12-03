package com.bigmeco.questconstructor.screen.fragments

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintSet
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.presenter.CreatorPresenter
import com.bigmeco.questconstructor.screen.activity.CreatorActivity
import com.bigmeco.questconstructor.screen.activity.CreatorScreenActivity
import com.bigmeco.questconstructor.screen.activity.PublicationActivity
import com.bigmeco.questconstructor.screen.adapter.ProjectAdapter
import com.bigmeco.questconstructor.screen.adapter.ScreenAdapter
import com.bigmeco.questconstructor.screen.touches.EditListener
import com.bigmeco.questconstructor.views.CreatorView
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_creator.*
import kotlinx.android.synthetic.main.item_project.view.*


class CreatorFragment : MvpAppCompatFragment(), CreatorView {
    @InjectPresenter
    lateinit var creatorPresenter: CreatorPresenter


    @ProvidePresenter
    fun provideSplashPresenter(): CreatorPresenter {
        return CreatorPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onResume() {
        super.onResume()
        listProjects.adapter!!.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        creatorPresenter.getProjects()
    }


    private fun listProject(findAll: ArrayList<ObjectProject>) {
        listProjects.isNestedScrollingEnabled = false
        listProjects.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        listProjects.adapter = ProjectAdapter(findAll) { it: View, list: List<ObjectProject>, index: Int ->
            it.plus.setOnClickListener {
                startActivity(Intent(activity, CreatorActivity::class.java))
            }

            it.cardPush.setOnClickListener {
                val intent = Intent(activity, PublicationActivity::class.java)
                val editor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                editor.putInt("idProject", list[index].id!!)
                editor.apply()
                startActivity(intent)
            }
            val gdt = GestureDetector(EditListener(mainFragment, editFragment))
            editFragment.setOnTouchListener { _, event ->
                gdt.onTouchEvent(event)
                true
            }

            it.cardColor.setOnClickListener {
                editingProject(list,index)
            }

            listScreens(it, index, list, findAll)
        }
        LinearSnapHelper().attachToRecyclerView(listProjects)
    }


    private fun editingProject(list: List<ObjectProject>, index: Int) {
        val args = Bundle()
        args.putInt("id_project", list[index].id!!)
        val set = ConstraintSet()
        val editProjectFragment = EditProjectFragment()
        editProjectFragment.arguments = args
        val ft = fragmentManager!!.beginTransaction()
        ft.replace(R.id.editFragment, editProjectFragment)
        ft.commit()
        set.clone(mainFragment)
        val mySwapTransition = ChangeBounds()
        mySwapTransition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {}
            override fun onTransitionEnd(transition: Transition) {
                set.clone(mainFragment)
                set.connect(editFragment.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
                set.clear(editFragment.id, ConstraintSet.BOTTOM)
                set.connect(editFragment.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
                TransitionManager.beginDelayedTransition(mainFragment)
                set.applyTo(mainFragment)
            }
            override fun onTransitionCancel(transition: Transition) {}
            override fun onTransitionPause(transition: Transition) {}
            override fun onTransitionResume(transition: Transition) {}
        })
        TransitionManager.go(Scene(mainFragment), mySwapTransition)
        set.applyTo(mainFragment)
    }


    private fun listScreens(it: View, index: Int, list: List<ObjectProject>, findAll: ArrayList<ObjectProject>) {
        if (index == (list.size - 1)) {
            it.plus.visibility = View.VISIBLE
            it.listScreens.layoutManager = LinearLayoutManager(activity)
            it.listScreens.adapter = ScreenAdapter(ArrayList()) {
            }
        } else {
            it.plus.visibility = View.GONE
            it.listScreens.isNestedScrollingEnabled = false
            it.listScreens.layoutManager = LinearLayoutManager(activity)
            it.listScreens.adapter = findAll[index].screen?.let { it1 ->
                ScreenAdapter(it1) {
                    val intent = Intent(activity, CreatorScreenActivity::class.java)
                    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
                    editor.putInt("idProject", list[index].id!!)
                    editor.putInt("idScreen", it)
                    editor.apply()
                    startActivity(intent)
                }
            }
        }
    }


    override fun getProjects(findAll: ArrayList<ObjectProject>) {
        listProject(findAll)
    }


}
