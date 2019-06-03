package com.example.hbox

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.hbox.control.ControlFragment
import com.example.hbox.history.HistoryFragment
import com.example.hbox.home.HomeFragment
import com.example.hbox.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var homeFragment : HomeFragment
    private lateinit var controlFragment : ControlFragment
    private lateinit var historyFragment : HistoryFragment
    private lateinit var settingsFragment : SettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("d", "onCreate von MainActivity")
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        homeFragment = HomeFragment()
        controlFragment = ControlFragment()
        historyFragment = HistoryFragment()
        settingsFragment = SettingsFragment()

        replaceFragment(HomeFragment())
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_control-> {
                replaceFragment(controlFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                replaceFragment(historyFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                replaceFragment(settingsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.FragmentContainer, fragment)
        fragmentTransition.commit()
    }
}
