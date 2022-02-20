package com.example.mvvmwithretrofit.database

import android.content.Context
import com.example.mvvmwithretrofit.models.Result
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1)
abstract class QuoteDataBase : RoomDatabase() {

    abstract fun quoteDao(): QuotesDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDataBase? = null
        fun getDataBase(context: Context): QuoteDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        QuoteDataBase::class.java,
                        "quoteDB"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}