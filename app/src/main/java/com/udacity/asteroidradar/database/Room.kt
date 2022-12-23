package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.*


@Database (entities = [DateBaseAsteroid::class], version = 4, exportSchema = false)
abstract class PropDatabase:RoomDatabase(){
    abstract val asteroidDao:AsteroidDao
}


lateinit var INSTANCE : PropDatabase

fun getDatabase(context: Context):PropDatabase{
    synchronized(PropDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                PropDatabase::class.java,
                "Asteroid_Database"
            ).fallbackToDestructiveMigration().build()
        }
    }
    return INSTANCE
}