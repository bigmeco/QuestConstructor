package com.bigmeco.questconstructor

import android.app.Application
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.models.CreatorModel
import io.realm.Realm
import org.junit.Assert
import org.junit.Test


class CreatorTest {
    @Test
    fun getList() {
        val creatorModel = CreatorModel()
        Assert.assertEquals(ArrayList<ObjectProject>(), creatorModel.test())
    }
}
