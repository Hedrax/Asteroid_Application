package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database  = getDatabase(application)
    private val repos = Repository(database)
    private val _asteroidList = MutableLiveData<List<Asteroid>>()
    private val _AllAsteroid = MutableLiveData<List<Asteroid>>()
    private val _asteroidToday =MutableLiveData<List<Asteroid>>()
    private val _asteroidWeek =MutableLiveData<List<Asteroid>>()
    private val _image = MutableLiveData<PictureOfDay>()
    val image : LiveData<PictureOfDay>
    get() = _image
    val asteroidList :LiveData<List<Asteroid>>
        get() = _asteroidList
    private val _navigate = MutableLiveData<Asteroid>()
    val navigate :LiveData<Asteroid>
    get() = _navigate
    init {
    check()
    }
    fun navigateProp(item :Asteroid){
        _navigate.value = item
    }

    fun refreshData(){
        viewModelScope.launch {
            try {
                repos.refresh()
                Log.i("lol","Connection success")
            }
            catch(E:java.lang.Exception)
            {
                Log.i("lol",E.toString())
            }
            update()
        }

    }
    fun getWeek(){ _asteroidList.value = _asteroidWeek.value}
    fun getDay(){ _asteroidList.value = _asteroidToday.value}
    fun getSaved(){ _asteroidList.value = _AllAsteroid.value}
    private fun check(){
        viewModelScope.launch {
            update()
        }
    }
    private suspend fun update(){
        if (repos.getALLAsteroids().isEmpty()){refreshData()}
        _asteroidList.value = repos.getALLAsteroids()
        _AllAsteroid.value = repos.getALLAsteroids()
        _asteroidWeek.value = repos.getWeekAsteroids()
        _asteroidToday.value = repos.getTodayAsteroids()

        _image.value = repos.getPicOfDay()
    }
}