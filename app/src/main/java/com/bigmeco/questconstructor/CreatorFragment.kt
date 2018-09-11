package com.bigmeco.questconstructor

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            it.cardColor.setOnClickListener{
                val intent = Intent(activity, EditProjectActivity::class.java)
                val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
                editor.putInt("idProject", list[i].id!!)
                editor.apply()
                startActivity(intent)
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

                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(listProjects)
    }

}
