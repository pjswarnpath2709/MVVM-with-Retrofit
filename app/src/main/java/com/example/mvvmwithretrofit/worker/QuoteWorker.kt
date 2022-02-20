package com.example.mvvmwithretrofit.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmwithretrofit.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,params :WorkerParameters ): Worker(context,params ) {
    override fun doWork(): Result {
        val repository=(context as QuoteApplication).quotesRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackGround()
        }
        return Result.success()
    }
}