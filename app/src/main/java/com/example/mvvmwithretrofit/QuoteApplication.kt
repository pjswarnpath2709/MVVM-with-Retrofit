package com.example.mvvmwithretrofit

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvmwithretrofit.API.QuoteService
import com.example.mvvmwithretrofit.API.RetrofitHelper
import com.example.mvvmwithretrofit.Repository.QuotesRepository
import com.example.mvvmwithretrofit.database.QuoteDataBase
import com.example.mvvmwithretrofit.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {
    lateinit var quotesRepository: QuotesRepository
    override fun onCreate() {
        super.onCreate()
        initialise()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialise() {
        val quoteservice = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val dataBase = QuoteDataBase.getDataBase(applicationContext)
        quotesRepository = QuotesRepository(quoteservice, dataBase, applicationContext)
    }

}