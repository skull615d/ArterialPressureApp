package com.github.kadehar.arterialpressureapp.base.nav

import androidx.appcompat.app.AppCompatActivity
import com.github.kadehar.arterialpressureapp.R
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivityNavigator(activity: AppCompatActivity) : AppNavigator(
    activity = activity,
    containerId = R.layout.activity_main
)