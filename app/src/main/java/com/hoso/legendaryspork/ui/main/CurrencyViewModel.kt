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

    val currencyList: LiveData<List<CurrencyInfo>> = CurrencyListMediatorLiveData(_currencyList, _order)

    inner class CurrencyListMediatorLiveData(
        private val currencyLiveData: MutableLiveData<List<CurrencyInfo>>,
        private val orderLiveData: MutableLiveData<Boolean>
    ) : MediatorLiveData<List<CurrencyInfo>>() {
        init {
            addSource(
                orderLiveData
            ) { order ->
                this.postValue(
                    if (order) {
                        currencyLiveData.value?.sortedBy { it.id }
                    } else {
                        currencyLiveData.value?.sortedByDescending { it.id }
                    }
                )
            }
            addSource(
                currencyLiveData
            ) { list ->
                val order = orderLiveData.value
                this.postValue(
                    when {
                        order == null -> {
                            list
                        }
                        order -> {
                            list.sortedBy { it.id }
                        }
                        else -> {
                            list.sortedByDescending { it.id }
                        }
                    }
                )
            }
        }
    }
}
