package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AsteroidDao{
    @Query("select* from asteroid_table")
    fun getProperties(): List<DateBaseAsteroid>

    @Query("SELECT* from asteroid_table  WHERE closeApproachDate >= :week ORDER by closeApproachDate ASC")
    fun getWeek(week:String):List<DateBaseAsteroid>

    @Query("SELECT* from asteroid_table  WHERE closeApproachDate >= :day ORDER by closeApproachDate ASC")
    fun getDay(day:String):List<DateBaseAsteroid>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg asteroids: DateBaseAsteroid)

    @Query("DELETE from asteroid_table")
    fun eraseAsteroid()
}
