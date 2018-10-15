package com.bigmeco.questconstructor

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_creator.*
import kotlinx.android.synthetic.main.fragment_edit_project.*


class EditProjectFragment : Fragment() {
    private var objectProject: ObjectProject? = ObjectProject()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_project, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val realm = Realm.getDefaultInstance()
        val idProject = arguments!!.getInt("id_project")
        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        //Log.d("arguments",s.toString())
        editName.setText(objectProject?.name)
        editBody.setText(objectProject?.body)
        spinnerGenres.setSelection(genreID(objectProject!!.genre!!)!!)
        spinnerTime.setSelection(timeID(objectProject!!.time!!)!!)

        cancel.setOnClickListener {
            updateEdit()
        }

        done.setOnClickListener {
            realm.executeTransaction {
                objectProject!!.name = editName.text.toString()
                objectProject!!.body = editBody.text.toString()
                objectProject!!.genre = spinnerGenres.selectedItem.toString()
                objectProject!!.time = spinnerTime.selectedItem.toString()
                objectProject!!.status = editName.text.toString() != "" && editBody.text.toString() != ""
            }
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(editName.getWindowToken(), 0)
            imm.hideSoftInputFromWindow(editBody.getWindowToken(), 0)

            activity!!.listProjects.adapter!!.notifyDataSetChanged();
            updateEdit()
        }
    }

    private fun genreID(str: String): Int? {
        val names = resources.getStringArray(R.array.greetings)
        var t: Int? = null
        for (i in names.indices) {
            if (str == names[i]) {
                t = i
                break
            } else {
                t = 0
            }
        }
        return t
    }

    private fun timeID(str: String): Int? {
        val names = resources.getStringArray(R.array.duration)
        var t: Int? = null
        for (i in names.indices) {
            if (str == names[i]) {
                t = i
                break
            } else {
                t = 0
            }
        }
        return t
    }

    fun updateEdit() {
        var set = ConstraintSet()
        set.clone(activity!!.mainFragment)
        set.clear(activity!!.editFragment.id, ConstraintSet.TOP)
        set.clear(activity!!.editFragment.id, ConstraintSet.TOP)
        set.clear(activity!!.editFragment.id, ConstraintSet.BOTTOM)
        set.connect(activity!!.editFragment.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
        TransitionManager.beginDelayedTransition(activity!!.mainFragment)
        set.applyTo(activity!!.mainFragment)
    }
}