package com.example.mvvmwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofit.API.QuoteService
import com.example.mvvmwithretrofit.API.RetrofitHelper
import com.example.mvvmwithretrofit.Repository.QuotesRepository
import com.example.mvvmwithretrofit.viewModel.MainViewModel
import com.example.mvvmwithretrofit.viewModel.MainViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var mainviewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository=(application as QuoteApplication).quotesRepository


        mainviewmodel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)



        mainviewmodel.quotes.observe(this, Observer {
            Log.d("CHEEZYCODEDATA", it.results.toString())
        })
    }


}