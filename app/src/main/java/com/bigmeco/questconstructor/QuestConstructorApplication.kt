package com.bigmeco.questconstructor

import android.app.Application
import android.content.Context
import io.realm.Realm

class QuestConstructorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {

        var context: Context? = null
            private set
    }
}