package com.example.sleeptime.database

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class SleepDetails(
        @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
        val id:Int,
        @ColumnInfo(name = "StartTime")
        val StartDate: Long,
        @ColumnInfo(name="EndTime")
        val EndDate: Long,
        @ColumnInfo(name = "TotalDuration")
        val TotalDuration: Int,
): BaseObservable()