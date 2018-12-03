package com.bigmeco.questconstructor

import android.app.Application
import android.content.Context
import io.realm.Realm

class QuestConstructorApplication() : Application() {
    private var mInstance: QuestConstructorApplication = this

    @Synchronized
    fun getInstance(): QuestConstructorApplication {
        return mInstance
    }


    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }


}