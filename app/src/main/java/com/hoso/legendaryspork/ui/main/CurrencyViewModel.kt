package com.hoso.legendaryspork.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoso.legendaryspork.data.CurrencyRepository
import com.hoso.legendaryspork.data.model.CurrencyInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val _currencyList = MutableLiveData<List<CurrencyInfo>>()
    val currencyList: LiveData<List<CurrencyInfo>> = _currencyList
    fun fetchCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            _currencyList.postValue(CurrencyRepository.getCurrencyList())
        }
    }
}
