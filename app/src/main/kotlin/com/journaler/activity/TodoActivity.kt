// TodoActivity.kt
package com.journaler.activity

import com.journaler.R

class TodoActivity: ItemActivity() {

    override val tag = "TODO ACTIVITY"
    override fun getLayout() = R.layout.activity_todo
}