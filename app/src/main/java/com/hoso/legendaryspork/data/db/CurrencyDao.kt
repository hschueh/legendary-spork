package com.hoso.paypaycurrencyex.data.db

import androidx.room.*
import com.hoso.legendaryspork.data.model.CurrencyInfo

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM CurrencyInfo")
    suspend fun getCurrencies(): List<CurrencyInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCurrencies(vararg currencyRates: CurrencyInfo)
}
