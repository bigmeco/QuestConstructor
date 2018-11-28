package com.bigmeco.questconstructor.view.fragments

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.R
import com.bigmeco.questconstructor.view.activity.CreatorActivity
import com.bigmeco.questconstructor.view.activity.CreatorScreenActivity
import com.bigmeco.questconstructor.view.activity.PublicationActivity
import com.bigmeco.questconstructor.view.adapter.ProjectAdapter
import com.bigmeco.questconstructor.view.adapter.ScreenAdapter
import com.bigmeco.questconstructor.view.touches.EditListener
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_creator.*
import kotlinx.android.synthetic.main.item_project.view.*




class CreatorFragment : Fragment() {


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
        val realm = Realm.getDefaultInstance()

        // тестовый обект/>
        var objectProject = ObjectProject()
        objectProject.body = ""
        objectProject.time = ""
        objectProject.genre = ""
        objectProject.id = 0
        objectProject.name = "0"
        listProjects.isNestedScrollingEnabled = false
        listProjects.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
       var test= ArrayList<ObjectProject>(realm.where(ObjectProject::class.java).findAll())

        test.add(ObjectProject())
Log.d("rest",test.toString())
       // test(objectProject)
        listProjects.adapter = ProjectAdapter(test) { it: View, list: List<ObjectProject>, i: Int ->
            it.plus.setOnClickListener {
                startActivity(Intent(activity, CreatorActivity::class.java))

            }
            it.cardPush.setOnClickListener {
                val intent = Intent(activity, PublicationActivity::class.java)
                val editor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
                editor.putInt("idProject", list[i].id!!)
                editor.apply()
                Log.d("ddd", objectProject.id.toString())

                startActivity(intent)
            }

            val gdt = GestureDetector(EditListener(mainFragment, editFragment))
            editFragment.setOnTouchListener { _, event ->
                gdt.onTouchEvent(event)
                true
            }
//            editFragment.setOnClickListener{
//                var set=ConstraintSet()
//                set.clone(mainFragment)
//                set.clear(editFragment.id,ConstraintSet.TOP)
//                set.clear(editFragment.id,ConstraintSet.BOTTOM)
//                set.connect(editFragment.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
//
//                TransitionManager.beginDelayedTransition(mainFragment)
//                set.applyTo(mainFragment)
//            }
            it.cardColor.setOnClickListener {
                val args = Bundle()
                args.putInt("id_project", list[i].id!!)
                var set = ConstraintSet()
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


            if (i == (list.size - 1)) {
                it.plus.visibility = View.VISIBLE
                it.listScreens.layoutManager = LinearLayoutManager(activity)
                it.listScreens.adapter = ScreenAdapter(ArrayList()) {
                }
            } else {
                it.plus.visibility = View.GONE

                it.listScreens.isNestedScrollingEnabled = false


                it.listScreens.layoutManager = LinearLayoutManager(activity)
                it.listScreens.adapter = ScreenAdapter(test[i].screen!!) {
                    val intent = Intent(activity, CreatorScreenActivity::class.java)
                    val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
                    editor.putInt("idProject", list[i].id!!)
                    editor.putInt("idScreen", it)
                    editor.apply()
                    startActivity(intent)

                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(listProjects)
    }


}
