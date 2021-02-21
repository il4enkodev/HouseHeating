package com.github.il4enkodev.househeating.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.ui.behavior.NavigationSheetBehavior
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView
    private lateinit var bottomBar: BottomAppBar
    private lateinit var behavior: NavigationSheetBehavior<NavigationView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar = findViewById(R.id.bottomBar)
        setSupportActionBar(bottomBar)
        bottomBar.setNavigationOnClickListener {
            behavior.expanded = !behavior.expanded
        }

        navView = findViewById(R.id.navView)
//        navView.setNavigationItemSelectedListener{
//            it.isChecked = true
//            behavior.expanded = false
//            true
//        }

        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        navHostFragment?.navController?.let {
            navView.setupWithNavController(it)
        }

        behavior = NavigationSheetBehavior.from(navView)
    }
}