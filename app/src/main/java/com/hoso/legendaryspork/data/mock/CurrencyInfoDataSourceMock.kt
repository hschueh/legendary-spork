package com.hoso.legendaryspork.data.mock

import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.legendaryspork.util.toDataClassList
import kotlinx.coroutines.coroutineScope

object CurrencyInfoDataSourceMock {
    suspend fun getCurrencyList(): List<CurrencyInfo> = coroutineScope {
        return@coroutineScope """
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
            },
            {
            "id": "LTC",
            "name": "Litecoin",
            "symbol": "LTC"
            },
            {
            "id": "EOS",
            "name": "EOS",
            "symbol": "EOS"
            },
            {
            "id": "BNB",
            "name": "Binance Coin",
            "symbol": "BNB"
            },
            {
            "id": "LINK",
            "name": "Chainlink",
            "symbol": "LINK"
            },
            {
            "id": "NEO",
            "name": "NEO",
            "symbol": "NEO"
            },
            {
            "id": "ETC",
            "name": "Ethereum Classic",
            "symbol": "ETC"
            },
            {
            "id": "ONT",
            "name": "Ontology",
            "symbol": "ONT"
            },
            {
            "id": "CRO",
            "name": "Crypto.com Chain",
            "symbol": "CRO"
            },
            {
            "id": "CUC",
            "name": "Cucumber",
            "symbol": "CUC"
            },
            {
            "id": "USDC",
            "name": "USD Coin",
            "symbol": "USDC"
            }
          ]
        """.trimIndent().toDataClassList<CurrencyInfo>()
    }
}
