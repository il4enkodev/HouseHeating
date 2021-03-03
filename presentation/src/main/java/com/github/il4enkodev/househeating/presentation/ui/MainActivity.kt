package com.github.il4enkodev.househeating.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.github.il4enkodev.househeating.R
import com.github.il4enkodev.househeating.presentation.ui.behavior.NavigationSheetBehavior
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton
    private lateinit var navView: NavigationView
    private lateinit var bottomBar: BottomAppBar
    private lateinit var behavior: NavigationSheetBehavior<NavigationView>

    private val fabViewModel: FabViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController()

        bottomBar = findViewById(R.id.bottomBar)
        setSupportActionBar(bottomBar)
        bottomBar.setNavigationOnClickListener {
            behavior.expanded = !behavior.expanded
        }

        navView = findViewById(R.id.navView)
        navView.setupWithNavController(navController)

        behavior = NavigationSheetBehavior.from(navView)

        fab = findViewById(R.id.fab)
        fab.setOnClickListener { fabViewModel.clicked() }
        fabViewModel.visibility().observe(this) { visible ->
            if (visible) fab.show() else fab.hide()
        }
    }
}