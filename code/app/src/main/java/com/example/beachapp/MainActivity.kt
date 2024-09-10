package com.example.beachapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beachapp.dashboardNavigationFragments.BookmarkFragment
import com.example.beachapp.dashboardNavigationFragments.ChatbotFragment
import com.example.beachapp.dashboardNavigationFragments.HomeFragment
import com.example.beachapp.dashboardNavigationFragments.MapFragment
import com.example.beachapp.dashboardNavigationFragments.SettingsFragment
import com.example.beachapp.databinding.ActivityMainBinding
import com.example.beachapp.utility.BeachForecastData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.bottomNavigation.selectedItemId = R.id.navigation_home
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        // Load the initial fragment only if there is no saved instance state
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val selectedFragment: Fragment = when (item.itemId) {
            R.id.navigation_home -> HomeFragment()
            R.id.navigation_settings -> SettingsFragment()
            R.id.navigation_map -> MapFragment()
            R.id.navigation_bookmark -> BookmarkFragment()
            R.id.navigation_chatbot -> ChatbotFragment()
            else -> HomeFragment()
        }


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, selectedFragment)
            .commit()

        true
    }
}
