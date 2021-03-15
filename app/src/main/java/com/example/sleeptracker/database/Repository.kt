package com.example.sleeptime.database

import androidx.lifecycle.LiveData

class Repository(private val sleepDao: SleepDao) {
    fun getrepositorytable(): LiveData<List<SleepDetails>>
    {
        return sleepDao.gettable()
    }

    fun inserttablerepository(sleepDetails: SleepDetails){
        return sleepDao.inserttable(sleepDetails)
    }
}