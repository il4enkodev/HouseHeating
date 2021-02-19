package com.github.il4enkodev.househeating.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.il4enkodev.househeating.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}