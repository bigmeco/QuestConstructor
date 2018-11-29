package com.bigmeco.questconstructor.screen.fragments

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.transition.TransitionManager
import android.util.Log
import com.bigmeco.questconstructor.R
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_creator_screen.*
import kotlinx.android.synthetic.main.fragment_creator_screen.*
import android.view.inputmethod.InputMethodManager
import android.util.DisplayMetrics
import android.view.*
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.screen.activity.CreatorScreenActivity
import com.bigmeco.questconstructor.screen.adapter.ButtonsAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_button_add.view.*


class CreatorScreenFragment : Fragment() {


    val realm = Realm.getDefaultInstance()
    private var idProject = 0
    private var idScreen = 0
    private var idButton: Int? = null
    private var objectProject: ObjectProject? = ObjectProject()
    private var objectScreen: ObjectScreen? = ObjectScreen()
    var objectButton: ArrayList<ObjectButton> = ArrayList()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val metrics = DisplayMetrics()
        activity!!.windowManager.defaultDisplay.getMetrics(metrics)
        val bundle = this.arguments
        if (bundle != null) {
            idProject = bundle.getInt("project", 0)
            idScreen = bundle.getInt("screen", 0)
        }
        objectProject = realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()
        objectScreen = objectProject?.screen?.get(idScreen)
        objectButton = ArrayList(objectScreen?.buttons)
        if (objectScreen!!.image != "") {
            Picasso.get().load(objectScreen!!.image).fit().centerCrop().error(R.drawable.plus).into(imageScreen)
        }
        imageScreen.setImageResource(R.drawable.plus)

        editTextBody.setText(objectScreen!!.body)
        imageScreen.setOnClickListener {
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?

            val set = ConstraintSet()
            set.clone(mainLayoutFragment)
            if (cardUrl.height != ConstraintSet.MATCH_CONSTRAINT) {
                set.constrainHeight(cardUrl.id, ConstraintSet.MATCH_CONSTRAINT)
                imm!!.hideSoftInputFromWindow(editTextUrl.getWindowToken(), 0)
                realm.executeTransaction {
                    objectScreen!!.image = editTextUrl.text.toString()
                }
                if (editTextUrl.text.toString() != "") {
                    Picasso.get().load(editTextUrl.text.toString()).fit().centerCrop().error(R.drawable.plus).into(imageScreen)
                } else {
                    imageScreen.setImageResource(R.drawable.plus)
                }

            } else {
                editTextUrl.setText(objectScreen!!.image)
                set.constrainHeight(cardUrl.id, ConstraintSet.WRAP_CONTENT)
                editTextUrl.requestFocus()
                imm!!.showSoftInput(editTextUrl, InputMethodManager.SHOW_IMPLICIT)
                editTextUrl.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                    if (event.getAction() === KeyEvent.ACTION_DOWN) {
                        when (keyCode) {
                            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                                realm.executeTransaction {
                                    objectScreen!!.image = editTextUrl.text.toString()
                                }
                                set.constrainHeight(cardUrl.id, ConstraintSet.MATCH_CONSTRAINT)
                                TransitionManager.beginDelayedTransition(mainLayoutFragment)
                                set.applyTo(mainLayoutFragment)
                                if (editTextUrl.text.toString() != "") {
                                    Picasso.get().load(editTextUrl.text.toString()).fit().centerCrop().error(R.drawable.plus).into(imageScreen)
                                } else {
                                    imageScreen.setImageResource(R.drawable.plus)
                                }
                                return@OnKeyListener true
                            }
                            else -> {
                            }
                        }
                    }
                    false
                })
            }
            TransitionManager.beginDelayedTransition(mainLayoutFragment)
            set.applyTo(mainLayoutFragment)

            Log.d("rrrrr", idScreen.toString())
        }
        listButtons.layoutManager = LinearLayoutManager(activity)
        listButtons.adapter = ButtonsAdapter(objectButton) {
            idButton = it
            val set = ConstraintSet()
            set.clone(activity?.mainLayout)
            set.clear(activity!!.fading_edge_layout.id, ConstraintSet.TOP)
            set.connect(activity!!.fading_edge_layout.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 512)
            TransitionManager.beginDelayedTransition(activity!!.mainLayout)
            set.applyTo(activity!!.mainLayout)

        }
        buttonAdd.setOnClickListener {
            objectButton.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton.size)

        }

        buttonExit.setOnClickListener {
            val i = ObjectButton()
            i.id=9000
            i.status=true
            objectButton.add(i)
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton.size)
            Log.d("scsdcsdcsdcs",objectProject.toString())
            Log.d("scsdcsdcsdcs",objectScreen.toString())
            Log.d("scsdcsdcsdcs",objectButton[0].toString())

        }


        //    listButtonsUpdate(objectButton)


        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)


    }


    override fun onStop() {
        super.onStop()
        val bundle = this.arguments

        Log.d("fffff", bundle?.getInt("IDscreen", 0).toString())
//        var objectButton =RealmList<ObjectButton>()
//        objectButton.addAll(this.objectButton)

        realm.executeTransaction {

            if (this.idButton != null) {
                objectButton[this.idButton!!].id = bundle?.getInt("IDscreen", 0)
            }
            objectScreen!!.body = editTextBody.text.toString()
            objectScreen!!.status = objectScreen!!.thereIsNull()

            objectScreen!!.buttons!!.clear()
            objectScreen!!.buttons!!.addAll(this.objectButton)


            for (i in 0 until objectScreen!!.buttons!!.size) {
                if (objectScreen!!.buttons!![i]!!.thereIsNull()) {
                    objectScreen!!.buttons!![i]!!.status = true
                } else {
                    objectScreen!!.buttons!![i]!!.status = false
                    break
                }
            }
            if (objectButton.size != 0) {
                for (i in 0 until objectScreen!!.buttons!!.size) {
                    if (objectScreen!!.buttons!![i]!!.status!!) {
                        objectScreen!!.status = true
                    } else {
                        objectScreen!!.status = false
                        break
                    }
                }
            } else {
                objectScreen!!.status = false
            }

            Log.d("asasa", objectButton.size.toString())
            if (objectProject!!.thereIsNull()) {
                for (i in 0 until objectProject!!.screen!!.size) {
                    if (objectProject!!.screen!![i]!!.status!!) {
                        objectProject!!.status = true
                    } else {
                        objectProject!!.status = false
                        break
                    }
                }
            } else {
                objectProject!!.status = false
            }
        }

        //Log.d("screan", realm.where(ObjectProject::class.java).equalTo("id", idProject).findFirst()!!.screen!![idScreen]!!.buttons!![0].toString())
        (activity as CreatorScreenActivity).listScreenUpdate(ArrayList(objectProject!!.screen))
        this.idButton = null
    }

    private fun listButtonsUpdate(buttons: ArrayList<ObjectButton>) {
        listButtons.adapter = ButtonsAdapter(buttons) {
            //        val set = ConstraintSet()
//        set.clone(mainLayout)
//        set.clear(fading_edge_layout.id, ConstraintSet.TOP)
//        set.connect(fading_edge_layout.id, ConstraintSet.TOP, cardBody.id, ConstraintSet.BOTTOM, 0)
//        TransitionManager.beginDelayedTransition(mainLayout)
//        set.applyTo(mainLayout)
        }
    }


    private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            viewHolder.itemView.editBody.text = null
            objectButton.removeAt(viewHolder.getAdapterPosition());
            listButtons.adapter!!.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }


}
