package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.Network
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.PropDatabase
import com.udacity.asteroidradar.database.toDomain
import com.udacity.asteroidradar.toDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Repository(private val database: PropDatabase) {
    private val API_KEY = "tEVQa86GFvfYip9EdY3ITxVbZQjcUWb8d7HInp3W"
    private lateinit var start_data:String
    private lateinit var end_data:String
    private lateinit var calendar: List<String>
    init {
        update()
    }
    suspend fun refresh(){
        update()
        withContext(Dispatchers.IO){
            try {
                database.asteroidDao.eraseAsteroid()
                val asteroidList = parseAsteroidsJsonResult(
                    JSONObject( Network.service.getProperties(start_data,end_data,API_KEY)))
                    .toDataBase()
                database.asteroidDao.insert(*asteroidList)

                Log.i("lol", "Asteroids Connection and insertion success")
            }
            catch (e:Exception){
                Log.i("lol Asteroid connection",e.toString())
            }
        }
    }

    suspend fun getALLAsteroids():List<Asteroid> =
        withContext(Dispatchers.IO) {database.asteroidDao.getProperties().toDomain()}

    suspend fun getTodayAsteroids():List<Asteroid> =
        withContext(Dispatchers.IO) { database.asteroidDao.getDay(start_data).toDomain()}


    suspend fun getWeekAsteroids():List<Asteroid> =
        withContext(Dispatchers.IO) {database.asteroidDao.getWeek(end_data).toDomain()}

    suspend fun getPicOfDay():PictureOfDay {
        var image :PictureOfDay = PictureOfDay(
            "https://pic.onlinewebfonts.com/svg/img_216129.png",
            "noInternet",
            "Something went wrong")
        try {
            image = Network.service.getPic(API_KEY);
            Log.i("lol", "Picture Connection and insertion success")
        } catch (e: Exception) {
            Log.i("lol Picture connection", e.toString())
        }
        return image
    }

    private fun update(){
        calendar = weekCalender()
        start_data = calendar.first()
        end_data = calendar.last()
    }

    private fun weekCalender():List<String>{
        val formattedDateList = ArrayList<String>()

        val calendar = Calendar.getInstance()
        for (i in 0..Constants.DEFAULT_END_DATE_DAYS) {
            val currentTime = calendar.time
            val dateFormat = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            formattedDateList.add(dateFormat.format(currentTime))
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
        return formattedDateList
    }
}