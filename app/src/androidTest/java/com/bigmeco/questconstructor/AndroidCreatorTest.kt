package com.bigmeco.questconstructor

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.models.CreatorModel
import com.bigmeco.questconstructor.models.ProjectCreationModel
import io.realm.Realm
import io.realm.RealmList
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AndroidCreatorTest {




    @Test
    fun getListTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Realm.init(appContext)
         val realm: Realm = Realm.getDefaultInstance()

        val test = ArrayList<ObjectProject>()
        val creatorModel = CreatorModel()
        test.addAll(realm.where(ObjectProject::class.java).findAll())
        test.add(ObjectProject())
        Assert.assertEquals(test[0], creatorModel.getProjects()[0])
    }
    @Test
    fun setProjectTest() {
        val appContext = InstrumentationRegistry.getTargetContext()
        Realm.init(appContext)
        val projectCreationModel= ProjectCreationModel()
         val objectProject = ObjectProject()
        Assert.assertNotNull(projectCreationModel.getStandardProject(objectProject))
    }
}
