//ItemActivity.kt
package com.journaler.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.journaler.R
import com.journaler.model.MODE


abstract class ItemActivity : BaseActivity() {

    protected var mode = MODE.VIEW // This tell us if we're viewing, creating or editing a NOTE or a TODO
    protected var success = Activity.RESULT_CANCELED

    override fun getActivityTitle() = R.string.app_name
// NOTE: when you click on a button and open an activity we will pass some 'values' to it
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let{
            val modeToSet = data.getInt(MODE.EXTRAS_KEY, MODE.VIEW.mode)
            mode = MODE.getByValue(modeToSet)
        }
        Log.v(tag, "Mode [ $mode ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        setResult(success)
    }//Sets the result of the work that will be available for parent activity
}

/*
========================================== COMMENTS =======================================
- When we click on the button and open the activity, we will pass some values to it.
- This code snippet retrieves the values we passed


        val data = intent.extras // override 'onCreate()' to access the 'intent' instance
        data?.let{

    override fun onDestroy() {
        super.onDestroy()
        setResult(success)
    }

        val modeToSet = intent.getIntExtra(MODE.EXTRAS_KEY, MODE.VIEW.mode) // <-- The 'values' are retrieved here (method and params)
        // NOTE: If there is no value 'MODE.VIEW.mode' is returned
        mode = MODE.getByValue(modeToSet) // Mode = Value we obtained by getting the MODE instance from the integer value.
        Log.v(tag, "Mode [ $mode ]")



*/