package com.example.sleeptracker

import android.view.View

interface Handler {


    fun onEndDateClicked(view: View)
    fun onStartDateClicked(view: View)
    fun onStartTimeClicked(view: View)
    fun onEndTimeClicked(view: View)

}