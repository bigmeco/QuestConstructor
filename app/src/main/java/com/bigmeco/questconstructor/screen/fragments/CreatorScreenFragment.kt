package com.bigmeco.questconstructor.screen.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.support.constraint.ConstraintSet
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
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bigmeco.questconstructor.data.ObjectButton
import com.bigmeco.questconstructor.data.ObjectProject
import com.bigmeco.questconstructor.data.ObjectScreen
import com.bigmeco.questconstructor.presenter.CreatorScreenFrPresenter
import com.bigmeco.questconstructor.screen.activity.CreatorScreenActivity
import com.bigmeco.questconstructor.screen.adapter.ButtonsAdapter
import com.bigmeco.questconstructor.views.CreatorScreenFrView
import kotlinx.android.synthetic.main.item_button_add.view.*
import android.graphics.drawable.BitmapDrawable
import io.realm.RealmList


class CreatorScreenFragment : MvpAppCompatFragment(), CreatorScreenFrView {



    private var idProject = 0
    private var idScreen = 0
    private var idButton: Int? = null
    private var objectProject: ObjectProject? = ObjectProject()
    private var objectScreen: ObjectScreen? = ObjectScreen()
    var objectButton: ArrayList<ObjectButton> = ArrayList()
    @InjectPresenter
    lateinit var creatorScreenFrPresenter: CreatorScreenFrPresenter

    @ProvidePresenter
    fun creatorScreenFrPresenter(): CreatorScreenFrPresenter {
        return CreatorScreenFrPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_creator_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.windowManager.defaultDisplay.getMetrics(DisplayMetrics())
        val bundle = this.arguments
        if (bundle != null) {
            idProject = bundle.getInt("project", 0)
            idScreen = bundle.getInt("screen", 0)
        }

        creatorScreenFrPresenter.getProject(idProject)

        imageScreen.setOnClickListener {
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            val set = ConstraintSet()
            set.clone(mainLayoutFragment)
            if (cardUrl.height != ConstraintSet.MATCH_CONSTRAINT) {
                set.constrainHeight(cardUrl.id, ConstraintSet.MATCH_CONSTRAINT)
                imm!!.hideSoftInputFromWindow(editTextUrl.windowToken, 0)
                creatorScreenFrPresenter.setImageResponse(objectScreen, editTextUrl.text.toString())
            } else {
                editTextUrl.setText(objectScreen!!.image)
                set.constrainHeight(cardUrl.id, ConstraintSet.WRAP_CONTENT)
                editTextUrl.requestFocus()
                imm!!.showSoftInput(editTextUrl, InputMethodManager.SHOW_IMPLICIT)
                editTextUrl.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                    if (event.getAction() === KeyEvent.ACTION_DOWN) {
                        when (keyCode) {
                            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                                creatorScreenFrPresenter().setImageResponse(objectScreen, editTextUrl.text.toString())
                                set.constrainHeight(cardUrl.id, ConstraintSet.MATCH_CONSTRAINT)
                                TransitionManager.beginDelayedTransition(mainLayoutFragment)
                                set.applyTo(mainLayoutFragment)
                                getImage()
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
        }


        buttonAdd.setOnClickListener {
            objectButton.add(ObjectButton())
            (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButton.size)
        }

        buttonExit.setOnClickListener {
            creatorScreenFrPresenter.addButtonExit(objectButton)

        }


        val itemLeftHelper = ItemTouchHelper(simpleLeftCallback)
        itemLeftHelper.attachToRecyclerView(listButtons)
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val set = ConstraintSet()
            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - r.bottom
            set.clone(mainLayoutFragment)

            if (keypadHeight > screenHeight * 0.15) {
                set.setMargin(scrollView2.id, ConstraintSet.BOTTOM, resources.getDimension(R.dimen.keyBord).toInt())
                set.setMargin(imageView7.id, ConstraintSet.TOP, resources.getDimension(R.dimen.imageKeyBordON).toInt())
            } else {
                set.setMargin(scrollView2.id, ConstraintSet.BOTTOM, 0)
                set.setMargin(imageView7.id, ConstraintSet.TOP, resources.getDimension(R.dimen.imageKeyBord).toInt())
            }

            set.applyTo(mainLayoutFragment)
        }

    }

    override fun onStop() {
        super.onStop()
        val bundle = this.arguments
        objectProject?.let {
            creatorScreenFrPresenter.saveScreen(it,
                    objectScreen!!,
                    objectButton,
                    editTextBody.text.toString(),
                    bundle?.getInt("IDscreen", 0)!!,
                    this.idButton)
        }?.let { (activity as CreatorScreenActivity).listScreenUpdate(it) }
        this.idButton = null
    }


    private var simpleLeftCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            viewHolder.itemView.editBody.text = null
            objectButton.removeAt(viewHolder.adapterPosition)
            listButtons.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)
        }
    }

    override fun getProject(objectProject: ObjectProject) {
        this.objectProject = objectProject
        objectScreen = objectProject.screen?.get(idScreen)
        objectButton = ArrayList(objectScreen?.buttons)
        editTextUrl.setText(objectScreen!!.image)
        editTextBody.setText(objectScreen!!.body)
        getImage()
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
    }

    override fun resultCreationButton(objectButtons: ArrayList<ObjectButton>) {
        (listButtons.adapter as ButtonsAdapter).notifyItemInserted(objectButtons.size)
    }

    fun getImage() {
        if (editTextUrl.text.toString() != "") {
            Log.d("eeeeeeeeeee", "tru")
            creatorScreenFrPresenter.getImageResponse(editTextUrl.text.toString())
        } else {
            Log.d("eeeeeeeeeee", "fols")
            imageScreen.setImageResource(R.drawable.plus)
        }
    }

    override fun readImageResponse() {
        getImage()
    }

    override fun getImageResponse(image: Bitmap) {
        imageScreen.setImageBitmap(image)
        Log.d("eeeeeeeeeee", "override")

    }

    override fun getImageError() {
        imageScreen.setImageResource(R.drawable.cancel_image)
    }

}
