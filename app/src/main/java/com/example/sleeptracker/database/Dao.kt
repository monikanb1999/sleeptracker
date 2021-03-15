package com.example.sleeptime.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SleepDao {

    @Insert
    fun inserttable(sleepDetails: SleepDetails)


    @Query("select * from sleep_details")
    fun gettable(): LiveData<List<SleepDetails>>

}