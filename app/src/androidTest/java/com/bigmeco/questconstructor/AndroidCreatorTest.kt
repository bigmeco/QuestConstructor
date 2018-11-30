package com.bigmeco.questconstructor

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.models.CreatorModel
import io.realm.Realm
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AndroidCreatorTest {

    @Test
    fun getListTest() {
        val test = ArrayList<ObjectProject>()
        val appContext = InstrumentationRegistry.getTargetContext()
        Realm.init(appContext)
        val realm: Realm = Realm.getDefaultInstance()
        val creatorModel = CreatorModel()
        test.addAll(realm.where(ObjectProject::class.java).findAll())
        test.add(ObjectProject())
        assertEquals(test[0], creatorModel.getProjects()[0])
    }
}
