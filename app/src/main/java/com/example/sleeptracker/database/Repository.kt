package com.example.sleeptime.database

class Repository(private val sleepDao: SleepDao) {

    fun inserttablerepository(sleepDetails: SleepDetails){
        return sleepDao.inserttable(sleepDetails)
    }
}