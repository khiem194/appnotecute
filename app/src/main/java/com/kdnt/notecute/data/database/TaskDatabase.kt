package com.kdnt.notecute.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kdnt.notecute.Constants
import com.kdnt.notecute.data.entity.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    companion object{
        @Volatile
        private var instance : TaskDatabase? = null

        fun getInstance(context: Context) : TaskDatabase =
            instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(context, TaskDatabase::class.java, Constants.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
    }

    abstract fun taskDao() : TaskDao

}