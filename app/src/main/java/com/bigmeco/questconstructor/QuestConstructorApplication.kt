package com.bigmeco.questconstructor

import android.app.Application
import android.content.Context
import io.realm.Realm

class QuestConstructorApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        QuestConstructorApplication.appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}