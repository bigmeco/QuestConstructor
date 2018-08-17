package com.bigmeco.questconstructor

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_creator.*
import kotlinx.android.synthetic.main.item_project.view.*




class CreatorFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val realm = Realm.getDefaultInstance()

//
//        var objectProject2 = ObjectProject()
//        var object1 = ObjectScreen()
//        object1.body ="rfrfrfr"
//        object1.status =true
//
//        var objectScreen = RealmList<ObjectScreen>()
//        objectScreen.add(object1)
//        objectScreen.add(object1)
//        objectScreen.add(ObjectScreen())
//        objectScreen.add(object1)
//        objectProject2.body = "Предки славян — праславяне — издавна жили на территории Центральной и Восточной Европы. По языку они относятся к индоевропейской группе народов, которые населяют Европу и часть Азии вплоть до Индии. Первые упоминания о праславянах относятся к I—II вв. Римские авторы Тацит, Плиний, Птолемей называли предков славян венедами и считали, что они населяли бассейн реки Вислы."
//        objectProject2.time = "1 час"
//        objectProject2.genre = "хоррор"
//        objectProject2.id = 6
//        objectProject2.name = "История tu"
//        objectProject2.status = false
//        objectProject2.screen = objectScreen
//        realm.beginTransaction()
//        realm.insert(objectProject2)
//        realm.commitTransaction()



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
//for (i in 0..test.size){
//    if (test[i].screen!=null) {
//        for (u in 0..test[i].screen!!.size) {
//            var tr = ArrayList<ObjectScreen>(test[i].screen!!)[u]
//            if (!tr.status!!){
//
//                break
//            }
//        }
//    }
//}
        test.add(ObjectProject())
Log.d("rest",test.toString())
       // test(objectProject)
        listProjects.adapter = ProjectAdapter(test) { it: View, list: List<ObjectProject>, i: Int ->
            it.plus.setOnClickListener {
                startActivity(Intent(activity, CreatorActivity::class.java))

            }
            if (i == (list.size - 1)) {
                it.plus.visibility = View.VISIBLE
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
