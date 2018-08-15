package com.bigmeco.questconstructor

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_creator.*
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_start.*
import android.support.v7.widget.SnapHelper
import android.support.v7.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.item_project.view.*


class CreatorFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                // тестовый обект
        val test = ArrayList<ObjectProject>()
        val test2 = ArrayList<ObjectScreen>()
        val test3 = ArrayList<ObjectButton>()

        test.add(ObjectProject("История один","хоррор","Одинокий, потерянный человек...","1 час",1,test2))
        test.add(ObjectProject("История два очень длинная","хоррор","Предки славян — праславяне — издавна жили на территории Центральной и Восточной Европы. По языку они относятся к индоевропейской группе народов, которые населяют Европу и часть Азии вплоть до Индии. Первые упоминания о праславянах относятся к I—II вв. Римские авторы Тацит, Плиний, Птолемей называли предков славян венедами и считали, что они населяли бассейн реки Вислы. Более поздние авторы — Прокопий Кесарийский и Иордан (VI век) разделяют славян на три группы: склавины, жившие между Вислой и Днестром, венеды, населявшие бассейн Вислы, и анты, расселившиеся между Днестром и Днепром. Именно анты считаются предками восточных славян.",
                "6 часов",1,test2))
        test.add(ObjectProject("","","","",1,ArrayList()))
        test2.add(ObjectScreen("blablablablabla blablabla blablabla","",1,test3))
        test2.add(ObjectScreen("blablablablabla2 blablabla2 blablabla2","",1,test3))

        // тестовый обект/>

        listProjects.isNestedScrollingEnabled = false
        listProjects.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        listProjects.adapter = ProjectAdapter(test) { it: View, list: List<ObjectProject>, i: Int ->
            it.plus.setOnClickListener {

            }
            if (i == (list.size - 1)) {
                it.plus.visibility = View.VISIBLE
            } else {
                it.plus.visibility = View.GONE

                it.listScreens.isNestedScrollingEnabled = false


                it.listScreens.layoutManager = LinearLayoutManager(activity)
                it.listScreens.adapter = ScreenAdapter(test2) {

                }
            }
        }
        LinearSnapHelper().attachToRecyclerView(listProjects)
    }

}
