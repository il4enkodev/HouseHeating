package com.github.il4enkodev.househeating.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun AppCompatActivity.findNavController(): NavController {
    return supportFragmentManager.fragments
            .find { it is NavHostFragment }
            .run { this as? NavHostFragment }
            .let { it?.navController
                    ?: throw IllegalStateException("Can not find NavHostFragment") }
}