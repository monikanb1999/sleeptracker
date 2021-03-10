package com.example.sleeptime.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [SleepDetails::class],version = 1,exportSchema = false)
abstract class SleepRoomDatabase: RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: SleepRoomDatabase? = null

        fun getInstance(context: Context): SleepRoomDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepRoomDatabase::class.java,
                        "SleepRoomDatabase"
                    )

                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    abstract fun SleepDao(): SleepDao
}