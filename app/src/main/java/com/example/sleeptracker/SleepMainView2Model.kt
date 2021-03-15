package com.example.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sleeptime.database.Repository
import com.example.sleeptime.database.SleepDetails
import com.example.sleeptime.database.SleepRoomDatabase

class SleepMainView2Model(application: Application): AndroidViewModel(application) {
    private val tableRespository: Repository
    //observer
    lateinit var sleepdetaillist: LiveData<List<SleepDetails>>
    init {
        val dao= SleepRoomDatabase.getInstance(getApplication()).SleepDao()
        tableRespository= Repository(dao)
        sleepdetaillist=tableRespository.getrepositorytable()
    }


}