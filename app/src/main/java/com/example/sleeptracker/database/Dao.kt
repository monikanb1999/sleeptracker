package com.example.sleeptime.database

import androidx.room.Dao
import androidx.room.Insert
@Dao
interface SleepDao {
    @Insert
    fun inserttable(sleepDetails: SleepDetails)
}