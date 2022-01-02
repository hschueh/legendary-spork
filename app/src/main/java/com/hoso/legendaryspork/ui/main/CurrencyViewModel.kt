package com.hoso.legendaryspork.ui.main

import androidx.lifecycle.*
import com.hoso.legendaryspork.data.model.CurrencyInfo
import com.hoso.legendaryspork.data.repo.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val _currencyList = MutableLiveData<List<CurrencyInfo>>()
    fun fetchCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            _currencyList.postValue(CurrencyRepository.getCurrencyList())
        }
    }

    private val _order = MutableLiveData<Boolean>()
    fun toggleCurrencyListOrder() {
        _order.postValue(!(_order.value ?: false))
    }

    val currencyList: LiveData<List<CurrencyInfo>> = MediatorLiveData<List<CurrencyInfo>>().apply {
        addSource(
            _order
        ) { order ->
            this.postValue(
                if (order) {
                    _currencyList.value?.sortedBy { it.id }
                } else {
                    _currencyList.value?.sortedByDescending { it.id }
                }
            )
        }
        addSource(
            _currencyList
        ) { list ->
            val order = _order.value
            this.postValue(
                if (order == null) {
                    list
                } else if (order) {
                    list.sortedBy { it.id }
                } else {
                    list.sortedByDescending { it.id }
                }
            )
        }
    }
}
