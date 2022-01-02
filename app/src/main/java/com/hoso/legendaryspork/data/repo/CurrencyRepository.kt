package com.hoso.legendaryspork.data.repo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hoso.legendaryspork.data.mock.CurrencyInfoDataSourceMock
import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.paypaycurrencyex.data.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CurrencyRepository {
    lateinit var db: AppDatabase

    private val callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope(Dispatchers.IO).launch {
                fetchAndUpdateData()
            }
        }
    }
    fun init(applicationContext: Context) {
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "cdc_db",
        ).addCallback(callback).build()
    }

    suspend fun getCurrencyList(): List<CurrencyInfo> {
        return db.currencyDao().getCurrencies()
    }

    suspend fun fetchAndUpdateData() {
        db.currencyDao().insertCurrencies(*CurrencyInfoDataSourceMock.getCurrencyList().toTypedArray())
    }
}
