package com.udacity.asteroidradar

import android.os.Parcelable
import com.udacity.asteroidradar.database.DateBaseAsteroid
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid (
    val id: Long,
    val codename: String, val closeApproachDate: String,
                    val absoluteMagnitude: Double, val estimatedDiameter: Double,
                    val relativeVelocity: Double, val distanceFromEarth: Double,
                    val isPotentiallyHazardous: Boolean) : Parcelable
//just for debugging matters

fun List<Asteroid>.toDataBase() :Array<DateBaseAsteroid>{
    return map {DateBaseAsteroid(
        id = it.id,
        codename = it.codename
        ,closeApproachDate = it.closeApproachDate
        ,absoluteMagnitude = it.absoluteMagnitude
        ,estimatedDiameter = it.estimatedDiameter
        ,relativeVelocity = it.relativeVelocity
        ,distanceFromEarth = it.distanceFromEarth
        ,isPotentiallyHazardous = it.isPotentiallyHazardous
    )}.toTypedArray()
}