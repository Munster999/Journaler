package com.journaler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationListener
import android.os.*
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import com.journaler.R
import com.journaler.R.id.indicator
import com.journaler.database.Crud
import com.journaler.database.Db
import com.journaler.execution.TaskExecutor
import com.journaler.location.LocationProvider
import com.journaler.model.MODE
import com.journaler.model.Note
import com.journaler.service.DatabaseService
import kotlinx.android.synthetic.main.activity_note.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class NoteActivity : ItemActivity() {

    private var note: Note? = null
    override val tag = "Note activity"
    private var handler: Handler? = null
    private var location: Location? = null
    override fun getLayout() = R.layout.activity_note
    private val executor = TaskExecutor.getInstance(1)

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            updateNote()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            /*p0?.let {
                tryAsync(p0.toString())
            }  this can be removed at runtime - used for an example*/
        }
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            p0?.let {
                LocationProvider.unsubscribe(this)
                location = p0
                val title = getNoteTitle()
                val content = getNoteContent()
                note = Note(title, content, p0)

                // Switching to intent service.
                val dbIntent = Intent(this@NoteActivity, DatabaseService::class.java)
                dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
                dbIntent.putExtra(DatabaseService.EXTRA_OPERATION, MODE.CREATE.mode)
                startService(dbIntent)
                sendMessage(true)

            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

        override fun onProviderEnabled(p0: String?) {}

        override fun onProviderDisabled(p0: String?) {}
    }

    private val crudOperationListener = object : BroadcastReceiver() {
        override fun onReceive(ctx: Context?, intent: Intent?) {
            intent?.let {
                val crudResultValue = intent.getIntExtra(MODE.EXTRAS_KEY, 0)
                sendMessage(crudResultValue == 1)
            }
        }
    }

    /*private val threadPoolExecutor = ThreadPoolExecutor(
            3, 3, 1, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>()
    ) this can be removed at runtime - used as an example to show 'concurrency'
    NOTE: This segment is simply used to show how Async works: Params (shown below)
    P1 = corePoolSize = 'minimal' number of threads to keep in the pool
    P2 = maximumPoolSize = 'maximal' number of threads allowed in the pool
    P3 = keepAliveTime = If the number of threads is greater than the core, the non core threads will wait for a new tasks,
    and if they don't get one within the time defined by this param THEY WILL TERMINATE
    P4 = Unit = Represents the time time unit for 'keepAliveTime'
    P5 = WorkQueue = Represents the queue instance that will be used to hold the tasks
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message?) {
                msg?.let {
                    var color = R.color.vermilion
                    if (msg.arg1 > 0) {
                        color = R.color.green
                    }
                    indicator.setBackgroundColor(ContextCompat.getColor(
                            this@NoteActivity,
                            color
                    ))
                }
                super.handleMessage(msg)
            }
        }
        note_title.addTextChangedListener(textWatcher)
        note_content.addTextChangedListener(textWatcher)
        val intentFiler = IntentFilter(Crud.BROADCAST_ACTION)
        registerReceiver(crudOperationListener, intentFiler)
    }

    override fun onDestroy() {
        unregisterReceiver(crudOperationListener)
        super.onDestroy()
    }

    private fun updateNote() {
        if (note == null) {
            if (!TextUtils.isEmpty(getNoteTitle()) && !TextUtils.isEmpty(getNoteContent())) {
                LocationProvider.subscribe(locationListener)
            }
        } else {
            note?.title = getNoteTitle()
            note?.message = getNoteContent()

            // Switching to intent service.
            val dbIntent = Intent(this@NoteActivity, DatabaseService::class.java)
            dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
            dbIntent.putExtra(DatabaseService.EXTRA_OPERATION, MODE.EDIT.mode)
            startService(dbIntent)
            sendMessage(true)
        }
    }

    private fun sendMessage(result: Boolean) {
        Log.v(tag, "Crud operation result [ $result ]")
        val msg = handler?.obtainMessage()
        if (result) {
            msg?.arg1 = 1
        } else {
            msg?.arg1 = 0
        }
        handler?.sendMessage(msg)
    }

    private fun getNoteContent(): String {
        return note_content.text.toString()
    }

    private fun getNoteTitle(): String {
        return note_title.text.toString()
    }

    /*private class TryAsync(val identifier: String): AsyncTask<Unit, Int, Unit>() {
        private val tag = "TryAsync"

        override fun onPreExecute() {
            Log.i(tag, "onPreExecute [ $identifier ]")
            super.onPreExecute()
        }

        override fun doInBackground(vararg p0: Unit?): Unit {
            Log.i(tag, "doInBackground [ $identifier ][ START ]")
            Thread.sleep(5000)
            Log.i(tag, "doInBackground [ $identifier ][ END ]")
            return Unit
        }

        override fun onCancelled(result: Unit?): Unit {
            Log.i(tag, "onCancelled [ $identifier ][ END ]")
            super.onCancelled(result)
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val progress = values.first()
            progress?.let {
                Log.i(tag, "onProgressUpdate [ $identifier ][ $progress ]")
            }
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Unit?) {
            Log.i(tag, "onPostExecute [ $identifier ]")
            super.onPostExecute(result)
        }
    } // this can be removed at runtime - used for an example*/

    /*private fun tryAsync(identifier: String){
        val tryAsync = TryAsync(identifier)
        tryAsync.executeOnExecutor(threadPoolExecutor)
    } // this can be removed at runtime - used for an example*/
}