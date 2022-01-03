package com.hoso.legendaryspork

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.legendaryspork.ui.main.CurrencyViewModel
import com.hoso.legendaryspork.util.getOrAwaitValue
import com.hoso.legendaryspork.util.toDataClass
import com.hoso.legendaryspork.util.toDataClassList
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun currencyListMediatorLiveData_isCorrect() {
        val orderLiveData = MutableLiveData<Boolean>()
        val currencyListLiveData = MutableLiveData<List<CurrencyInfo>>()
        val currencyListMediatorLiveData = CurrencyViewModel.CurrencyListMediatorLiveData(
            currencyListLiveData,
            orderLiveData
        )
        assertEquals(currencyListMediatorLiveData.value, null)
        currencyListLiveData.postValue(
            listOf(
                CurrencyInfo("BTC", "Bitcoin", "BTC"),
                CurrencyInfo("ETH", "Ethereum", "ETH"),
                CurrencyInfo("XRP", "XRP", "XRP"),
                CurrencyInfo("BCH", "Bitcoin Cash", "BCH"),
            )
        )

        var newCurrencyListValue = currencyListMediatorLiveData.getOrAwaitValue<List<CurrencyInfo>>()
        assertEquals(newCurrencyListValue[0], CurrencyInfo("BTC", "Bitcoin", "BTC"))
        assertEquals(newCurrencyListValue[1], CurrencyInfo("ETH", "Ethereum", "ETH"))
        assertEquals(newCurrencyListValue[2], CurrencyInfo("XRP", "XRP", "XRP"))
        assertEquals(newCurrencyListValue[3], CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))

        orderLiveData.postValue(true)
        newCurrencyListValue = currencyListMediatorLiveData.getOrAwaitValue()
        assertEquals(newCurrencyListValue[0], CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
        assertEquals(newCurrencyListValue[1], CurrencyInfo("BTC", "Bitcoin", "BTC"))
        assertEquals(newCurrencyListValue[2], CurrencyInfo("ETH", "Ethereum", "ETH"))
        assertEquals(newCurrencyListValue[3], CurrencyInfo("XRP", "XRP", "XRP"))

        orderLiveData.postValue(false)
        newCurrencyListValue = currencyListMediatorLiveData.getOrAwaitValue()
        assertEquals(newCurrencyListValue[0], CurrencyInfo("XRP", "XRP", "XRP"))
        assertEquals(newCurrencyListValue[1], CurrencyInfo("ETH", "Ethereum", "ETH"))
        assertEquals(newCurrencyListValue[2], CurrencyInfo("BTC", "Bitcoin", "BTC"))
        assertEquals(newCurrencyListValue[3], CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
    }

    @Test
    fun dataConversion_isCorrect() {
        val btcCurrencyInfo =
            """
            {
            "id": "BTC",
            "name": "Bitcoin",
            "symbol": "BTC"
            }
            """.trimIndent().toDataClass<CurrencyInfo>()
        assertEquals(btcCurrencyInfo, CurrencyInfo("BTC", "Bitcoin", "BTC"))
        assertEquals(btcCurrencyInfo.abbr, "B")

        val currencyInfoList =
            """
          [
            {
            "id": "BTC",
            "name": "Bitcoin",
            "symbol": "BTC"
            },
            {
            "id": "ETH",
            "name": "Ethereum",
            "symbol": "ETH"
            },
            {
            "id": "XRP",
            "name": "XRP",
            "symbol": "XRP"
            },
            {
            "id": "BCH",
            "name": "Bitcoin Cash",
            "symbol": "BCH"
            }
          ]
            """.trimIndent().toDataClassList<CurrencyInfo>()
        assertEquals(currencyInfoList[0], CurrencyInfo("BTC", "Bitcoin", "BTC"))
        assertEquals(currencyInfoList[1], CurrencyInfo("ETH", "Ethereum", "ETH"))
        assertEquals(currencyInfoList[2], CurrencyInfo("XRP", "XRP", "XRP"))
        assertEquals(currencyInfoList[3], CurrencyInfo("BCH", "Bitcoin Cash", "BCH"))
    }
}
