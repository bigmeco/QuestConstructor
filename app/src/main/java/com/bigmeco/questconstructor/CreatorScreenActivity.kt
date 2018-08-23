package com.bigmeco.questconstructor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_creator_screen.*
import android.content.Intent
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_BACK
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.util.Log
import io.realm.Realm
import io.realm.RealmList
import com.bigmeco.questconstructor.R.id.imageView




class CreatorScreenActivity : AppCompatActivity() {

    val objectScreens = ArrayList<ObjectButton>()
    val realm = Realm.getDefaultInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creator_screen)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var id = preferences.getInt("idProject", 0)

        objectScreens.add(ObjectButton())

        listButtons.layoutManager = LinearLayoutManager(this)!!
        listButtons.adapter = ButtonsAdapter(objectScreens){
            val drawable = imageView4.drawable

            if (drawable is Animatable) {
                (drawable as Animatable).start()
            }
//            var anim = imageView4.drawable as Animatable
//            anim.start()
            var obP= realm.where(ObjectProject::class.java).equalTo("id",id).findFirst()
            if (obP != null) {
                for (i in 0..obP.screen!!.size)
                         obP.screen
            }
Log.d("ddd",   realm.where(ObjectProject::class.java).equalTo("id",id).findFirst().toString())
        }
        buttonAdd.setOnClickListener{
            objectScreens.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectScreens.size)
        }

        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)
        val itemRightHelper = ItemTouchHelper(simpleRightCallback)
        itemRightHelper.attachToRecyclerView(listButtons)
    }

    private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            objectScreens.removeAt(position)
            listButtons.adapter!!.notifyDataSetChanged()
        }
    }
    private var simpleRightCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            listButtons.adapter!!.notifyDataSetChanged()

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.repeatCount === 0) {
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onBackPressed() {
        startActivity(Intent(this, StartActivity::class.java))

    }
}
