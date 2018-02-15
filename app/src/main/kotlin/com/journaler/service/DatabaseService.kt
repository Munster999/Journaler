package com.journaler.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.journaler.database.Db
import com.journaler.model.MODE
import com.journaler.model.Note

class DatabaseService : IntentService("DatabaseService") {

    companion object {
        val EXTRA_ENTRY = "entry"
        val EXTRA_OPERATION = "operation"
    }

    private val tag = "Database service"

    override fun onCreate() {
        super.onCreate()
        Log.v(tag, "[ ON CREATE ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    override fun onHandleIntent(p0: Intent?) {
        p0?.let {
            val note = p0.getParcelableExtra<Note>(EXTRA_ENTRY)
            note?.let {
                val operation = p0.getIntExtra(EXTRA_OPERATION, -1)
                when (operation) {
                    MODE.CREATE.mode -> {
                        val result = Db.insert(note)
                        if (result) {
                            Log.i(tag, "Note inserted.")
                        } else {
                            Log.e(tag, "Note not inserted.")
                        }
                    }
                    MODE.EDIT.mode -> {
                        val result = Db.update(note)
                        if (result) {
                            Log.i(tag, "Note updated.")
                        } else {
                            Log.e(tag, "Note not updated.")
                        }
                    }
                    else -> {
                        Log.w(tag, "Unknown mode [ $operation ]")
                    }
                }
            }
        }
    }

}
/*
- This service will receive intents, obtain the operation and note the instance from it.
- Depending on the operation, the proper CRUD operation will be triggered.
- To pass a Note instance to Intent, we must implement 'Parcelable' so that the data is passed efficiently.
('Parcelable' is much faster than 'serializable')
-

*/
