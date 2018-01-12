package com.journaler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.journaler.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistableBundle: PersistableBundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
