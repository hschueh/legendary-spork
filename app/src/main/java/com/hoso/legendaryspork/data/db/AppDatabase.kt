package com.hoso.paypaycurrencyex.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hoso.legendaryspork.data.model.CurrencyInfo

@Database(entities = [CurrencyInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}
