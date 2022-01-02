package com.hoso.legendaryspork

import android.app.Application
import com.hoso.legendaryspork.data.repo.CurrencyRepository

class MainApplication : Application() {
    companion object {
        lateinit var instance: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        CurrencyRepository.init(applicationContext)
    }
}
