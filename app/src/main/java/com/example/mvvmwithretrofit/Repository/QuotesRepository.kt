package com.example.mvvmwithretrofit.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofit.API.QuoteService
import com.example.mvvmwithretrofit.database.QuoteDataBase
import com.example.mvvmwithretrofit.models.QuoteList
import com.example.mvvmwithretrofit.utils.NetworkUtils

class QuotesRepository(
    private val quoteService: QuoteService,
    private val quoteDataBase: QuoteDataBase,
    private val applicationContext: Context
) {
    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
        get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDataBase.quoteDao().addQuotes(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        } else {
            val quotes=quoteDataBase.quoteDao().getQuotes()
            val quoteList=QuoteList(1,1,1,quotes,1,1 )
            quotesLiveData.postValue(quoteList)
        }

    }
    suspend fun getQuotesBackGround(){
        val randomNumber=(Math.random()*10).toInt()
        val result=quoteService.getQuotes(randomNumber)
        if(result?.body()!=null){
            quoteDataBase.quoteDao().addQuotes(result.body()!!.results)
        }
    }
}