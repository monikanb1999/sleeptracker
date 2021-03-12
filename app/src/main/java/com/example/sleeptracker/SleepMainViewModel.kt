package com.example.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sleeptime.database.Repository
import com.example.sleeptime.database.SleepDetails
import com.example.sleeptime.database.SleepRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SleepMainViewModel(application: Application): AndroidViewModel(application) {
    private val tableRespository: Repository
    init {
        val dao1 = SleepRoomDatabase.getInstance(getApplication()).SleepDao()
        tableRespository= Repository(dao1)
    }
    fun inserttable(sleepDetails: SleepDetails)= viewModelScope.launch(Dispatchers.IO) {
        tableRespository.inserttablerepository(sleepDetails)
    }
}