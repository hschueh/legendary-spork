package com.hoso.legendaryspork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoso.legendaryspork.ui.main.CurrencyListFragment

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CurrencyListFragment.newInstance())
                .commitNow()
        }
    }
}
