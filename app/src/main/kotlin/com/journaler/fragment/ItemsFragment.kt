package com.journaler.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.ListView
import com.journaler.R
import com.journaler.activity.NoteActivity
import com.journaler.activity.TodoActivity
import com.journaler.model.MODE
import java.text.SimpleDateFormat
import java.util.*

class ItemsFragment : BaseFragment() {

    private val TODO_REQUEST = 1
    private val NOTE_REQUEST = 0

    override val logTag = "ITEMS FRAGMENT"

    override fun getLayout(): Int {
        return R.layout.fragment_items
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(getLayout(), container, false)
        val btn = view?.findViewById<FloatingActionButton>(R.id.new_item) // Accessed the FAB and assigned a 'ClickListener'
            btn?.setOnClickListener {
                animate(btn)
                val items = arrayOf(
                    getString(R.string.todos),
                    getString(R.string.notes)
                ) // On 'clicking' we create a dialog with TWO options, each will trigger a proper method for activity opening.
                val builder = AlertDialog.Builder(this@ItemsFragment.context)
                        .setTitle(R.string.choose_a_type)
                        .setCancelable(true)
                        .setOnCancelListener { animate(btn, false) }
                        .setItems(items, { _, which -> when (which) {
                                            0 -> { openCreateTodo() } // <--
                                            1 -> { openCreateNote() } // <--
                                            else -> Log.e(logTag, "Unknown Option Selected [$which]")
                                            }
                                         }
                                 )
                builder.show()
            }
        return view
    }

    /*override fun onResume() {
        super.onResume()
        val items = view?.findViewById<ListView>(R.id.items)
        items?.let {
            Handler().postDelayed({ // Used for 'delayed modification'
                if (!activity.isFinishing) {
                    items.setBackgroundColor(R.color.grey_text_middle)
                }
            }, 3000)
        }
    }*/

    override fun onResume() {
        super.onResume()
        val btn = view?.findViewById<FloatingActionButton>(R.id.new_item)
        btn?.let {
            animate(btn, false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TODO_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    Log.i(logTag, "We created new TODO.")
                } else {
                    Log.w(logTag, "We didn't create a new TODO.")
                }
            }
            NOTE_REQUEST -> {
                if (resultCode == Activity.RESULT_OK) {
                    Log.i(logTag, "We created new note.")
                } else {
                    Log.w(logTag, "We didn't create a new note.")
                }
            }
        }
    }

    private fun openCreateNote() { //To start an activity we require its 'context' and the 'class' of the activity we want to start
        val intent = Intent(context, NoteActivity::class.java)
        val data = Bundle()
        data.putInt(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        intent.putExtras(data) // changed from "intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)"
        startActivityForResult(intent, NOTE_REQUEST) // this starts a sub-activity
    }// creates an intent to open the 'Note' sub-activity screen

    private fun openCreateTodo() {
        val date = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH )
        val timeFormat = SimpleDateFormat("MM:HH", Locale.ENGLISH )

        val intent = Intent(context, TodoActivity::class.java)
        val data = Bundle()
        data.putInt(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        data.putString(TodoActivity.EXTRA_DATE, dateFormat.format(date))
        data.putString(TodoActivity.EXTRA_TIME,
        timeFormat.format(date))
        intent.putExtras(data) // changed from "intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)" (values reqd to start an intent)
        startActivityForResult(intent, TODO_REQUEST)
    }// creates an intent to open the 'Todo' sub-activity screen

    /*private fun animate(btn: FloatingActionButton, expand: Boolean = true) {
        btn.animate()
                .setInterpolator(BounceInterpolator())
                .scaleX(if(expand) { 1.5f } else { 1.0f })
                .scaleY(if(expand) { 1.5f } else { 1.0f })
                .setDuration(2000)
                .start()
    }

    This is the first animation from the code
    This method will animate scaling for the button with bounce interpolation.
    If the expand param is 'true' we will scale up otherwise scale down */

    private fun animate(btn: FloatingActionButton, expand: Boolean = true) {
        val animation1 = ObjectAnimator.ofFloat(btn, "scaleX", if (expand) {
            1.5f
        } else {
            1.0f
        })
        animation1.duration = 2000
        animation1.interpolator = BounceInterpolator()

        val animation2 = ObjectAnimator.ofFloat(btn, "scaleY", if (expand) {
            1.5f
        } else {
            1.0f
        })
        animation2.duration = 2000
        animation2.interpolator = BounceInterpolator()

        val animation3 = ObjectAnimator.ofFloat(btn, "alpha", if (expand) {
            0.3f
        } else {
            1.0f
        })
        animation3.duration = 500
        animation3.interpolator = AccelerateInterpolator()

        val set = AnimatorSet()
        set.play(animation1).with(animation2).before(animation3)
        set.start()
    }
}

/*
===================================== COMMENTS ==================================================
NOTE: INTENT - In Android, INTENT represents our 'intention' to do something
- to start the intent you need the 'context' and the 'class' of the activity you would like to start.
    Also, some additional values (MODE.EXTRAS_KEY, MODE.CREATE.mode)
- To get a result of the execution, we override the 'onActivityResult()' method and replace it with 'startActivityForResult()' method
- We checked to see which activity finished and whether it was successful or not
- NOTE: The [ AnimatorSet() ] class gives us the ability to create complex animations.
  In this case, we defined animations for scaling along the x-axis and for scaling along the y-axis
  This TWO animations will be animated at the same time giving us the effect of scaling in both directions.
  After we scale view we will reduce (or increase) views capacity.




*/

