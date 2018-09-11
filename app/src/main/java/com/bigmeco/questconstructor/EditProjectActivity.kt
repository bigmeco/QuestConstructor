package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log

class EditProjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_project)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        Log.d("kkk", preferences.getInt("idProject", 0).toString())
    }
}
