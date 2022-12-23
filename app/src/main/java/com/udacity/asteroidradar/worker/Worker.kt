package com.udacity.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.Repository

class Worker (appContext: Context,parameters: WorkerParameters) :CoroutineWorker(appContext,parameters){
    override suspend fun doWork(): Result {
        val database  = getDatabase(applicationContext)
        val repos = Repository(database)
        return try {
            repos.refresh()
            Result.success()
        }
        catch (e:Exception){
            Result.retry()
        }
    }
    companion object {
        const val WORK_NAME = "RefreshWorker"
    }
}