// MainActivity.kt
package com.journaler.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.util.Log
import android.view.MenuItem
import com.journaler.R
import com.journaler.fragment.ItemsFragment
import com.journaler.navigation.NavigationDrawerAdapter
import com.journaler.navigation.NavigationDrawerItem
import kotlinx.android.synthetic.main.activity_header.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override val tag = "MAIN ACTIVITY"
    override fun getLayout() = R.layout.activity_main
    override fun getActivityTitle() = R.string.app_name // Getting the activity title value (i.e. Int)

    private val keyPagePosition = "keyPagePosition"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pager.adapter = ViewPagerAdapter(supportFragmentManager)

        val menuItems = mutableListOf<NavigationDrawerItem>()
        // Instantiating 'NavigationDrawerItem' instances below
        val today = NavigationDrawerItem(
                getString(R.string.today), // assigning a title to the button
                Runnable { pager.setCurrentItem(0, true) }
                /*'Runnable' action that will execute.
                Each runnable will jump to a specific page of our view pager*/
        )

        val next7Days = NavigationDrawerItem(
                getString(R.string.next_seven_days),
                Runnable { pager.setCurrentItem(1, true) }
        )

        val todos = NavigationDrawerItem(
                getString(R.string.todos),
                Runnable { pager.setCurrentItem(2, true) }
        )

        val notes = NavigationDrawerItem(
                getString(R.string.notes),
                Runnable { pager.setCurrentItem(3, true) }
        )
        // assigned a title to the buttons
        menuItems.add(today)
        menuItems.add(next7Days)
        menuItems.add(todos)
        menuItems.add(notes)

        val navigationDrawerAdapter = NavigationDrawerAdapter(this, menuItems)
        left_drawer.adapter = navigationDrawerAdapter
    }

    /*
    - defining adapter class for the pager
    - we must extend 'FragmentStatePagerAdapter' class
    - The constructor accepts the 'fragment manager' as a param, that will deal with fragment transactions
    - NOTE: override the getItem() method that returns an instance of the fragment
    - getCount() - Returns the total number of expected fragments*/
    private class ViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {
        override fun getItem(position: Int): Fragment {
            return ItemsFragment()
        }

        override fun getCount(): Int {
            return 5
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.drawing_menu -> {drawer_layout.openDrawer(GravityCompat.START) // changed from **Log.v(tag, "Main menu.")**
                return true
            }
            R.id.options_menu -> {Log.v(tag, "Options menu")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}

/*
============================================== COMMENTS ====================================================

// FrameLayout will be our fragment container. Below, shows the new fragment in 'fragment_containerFrameLayout'
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val fragment = ItemsFragment()
    supportFragmentManager // NOTE: Android support library (instead of 'FragmentManager')
            .beginTransaction() // Starts a fragment transaction, will return an instance of a transaction
            .add(R.id.fragment_container, fragment) // .add() method will add a new fragment instance, that will be associated to 'fragment_containerFrameLayout'
            .commit() // This method executes the transaction
    filter_menu.setText("H")
    filter_menu.setOnClickListener {
        val userManualFrg = ManualFragment()
    supportFragmentManager // NOTE: Android support library (instead of 'FragmentManager')
            .beginTransaction() // Starts a fragment transaction, will return an instance of a transaction
            .replace(R.id.fragment_container, userManualFrg) // replaces the original fragment
            .addToBackStack("User Manual")
            .commit()
    }
}

*/
