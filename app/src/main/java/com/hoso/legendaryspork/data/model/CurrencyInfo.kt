package com.hoso.legendaryspork.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyInfo(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String
) {
    val abbr: String get() = name.substring(0, 1)
}
