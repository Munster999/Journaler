package com.journaler.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                val items = arrayOf(
                    getString(R.string.todos),
                    getString(R.string.notes)
                ) // On 'clicking' we create a dialog with TWO options, each will trigger a proper method for activity opening.
                val builder = AlertDialog.Builder(this@ItemsFragment.context)
                        .setTitle(R.string.choose_a_type)
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
}

/*
===================================== COMMENTS ==================================================
NOTE: INTENT - In Android, INTENT represents our 'intention' to do something
- to start the intent you need the 'context' and the 'class' of the activity you would like to start.
    Also, some additional values (MODE.EXTRAS_KEY, MODE.CREATE.mode)
- To get a result of the execution, we override the 'onActivityResult()' method and replace it with 'startActivityForResult()' method
- We checked to see which activity finished and whether it was successful or not




*/

