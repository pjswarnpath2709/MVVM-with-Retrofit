package com.example.mvvmwithretrofit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmwithretrofit.models.Result

@Dao
interface QuotesDao {
    @Insert
    suspend fun addQuotes(quotes: List<Result>)

    @Query("SELECT * FROM quote ")
    suspend fun getQuotes(): List<Result>

}