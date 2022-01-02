package com.hoso.legendaryspork.data.model

data class CurrencyInfo(
    val id: String,
    val name: String,
    val symbol: String
) {
    val abbr: String get() = name.substring(0, 1)
}
