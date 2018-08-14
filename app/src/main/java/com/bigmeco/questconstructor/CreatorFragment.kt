package com.bigmeco.questconstructor

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_creator.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_start.*
import android.support.v7.widget.SnapHelper
import android.support.v7.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.item_project.view.*


class CreatorFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val test = ArrayList<ObjectProject>()

        test.add(ObjectProject())
        test.add(ObjectProject())
        test.add(ObjectProject())
        test.add(ObjectProject())
        listProjects.isNestedScrollingEnabled = false;
        listProjects.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        listProjects.adapter = ProjectAdapter(test) { it: View, list: List<ObjectProject>, i: Int ->
            it.plus.setOnClickListener {
                Log.d("ff","fdfdfdg")
            }
            if (i == (list.size - 1)) {
                it.plus.visibility = View.VISIBLE
            } else {
                val test = ArrayList<ObjectScreen>()
                it.listScreens.isNestedScrollingEnabled = false

                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                test.add(ObjectScreen())
                it.listScreens.layoutManager = LinearLayoutManager(activity)
                it.listScreens.adapter = ScreenAdapter(test) {

                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(listProjects)
    }

}
