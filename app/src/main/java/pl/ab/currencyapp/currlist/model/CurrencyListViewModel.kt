package pl.ab.currencyapp.currlist.model

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.ab.currencyapp.currlist.ApiCallback
import pl.ab.currencyapp.currlist.item.CurrencyItem
import pl.ab.currencyapp.currlist.item.FixerItem
import pl.ab.currencyapp.currlist.item.QueryFixer
import pl.ab.currencyapp.repository.FixerRepository
import java.util.*
import kotlin.collections.ArrayList

class CurrencyListViewModel(private val fixerRepository: FixerRepository) : ViewModel() {

    val TAG = "CurrencyListViewModel"

    var queryFixer: MutableLiveData<QueryFixer> = MutableLiveData()
    var currencyItem: MutableLiveData<ArrayList<CurrencyItem>> = MutableLiveData()
    val fixerDataItem: MutableLiveData<FixerItem?> = MutableLiveData()
    val currencyDataItem: MutableLiveData<CurrencyItem> = MutableLiveData()
    var data = MutableLiveData(Calendar.getInstance())

    init {
        updateQueryFixer()
        currencyItem.value = arrayListOf()
        updateCurrencyList()
        updateCurrencyList()
        updateCurrencyList()
    }

    fun updateCurrencyList() {
        fixerRepository.downloadAPI(dateToString(), object : ApiCallback {
            override fun onActionSuccess(successItem: FixerItem?) {
                fixerDataItem.value = successItem
                changeCurrencyItem()
                updateDate()
            }

            override fun onActionFailure(throwableError: Throwable?) {
                Log.i(TAG, "Throwable error: " + throwableError.toString())
            }
        })
    }

    fun setCurrencyItem(currItem: CurrencyItem) {
        currencyDataItem.value = currItem
    }

    fun getCurrencyItem(): CurrencyItem {
        return currencyDataItem.value!!
    }

    fun getCurrencyList(): ArrayList<CurrencyItem> {
        return currencyItem.value!!
    }

    fun updateQueryFixer() {
        queryFixer.value = fixerRepository.getDefaultCurrencies()
    }

    fun updateDate() {
        val calendar = data.value
        calendar?.add(Calendar.DAY_OF_MONTH, -1)
        data.postValue(calendar)
    }

    fun dateToString(): String {
        var date = StringBuilder()
        val year = data.value?.get(Calendar.YEAR)
        date.append(year)
        date.append("-")

        val month = data.value?.get(Calendar.MONTH)
        if (month!! < 10)
            date.append("0" + month)
        else
            date.append(month)
        date.append("-")

        val day = data.value?.get(Calendar.DAY_OF_MONTH)
        if (day!! < 10)
            date.append("0" + day)
        else
            date.append(day)

        return date.toString()
    }

    fun querySize(): Int {
        return queryFixer.value?.listCurrencyDisplayed?.size ?: 0
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun changeCurrencyItem() {
        val tempList = currencyItem.value
        for (i in 0..querySize()) {
            if (i == 0)
                tempList?.add(
                    CurrencyItem(
                        date = dateToString(),
                        isDate = true
                    )
                )
            else {
                val currencyName = queryFixer.value?.listCurrencyDisplayed?.get(i - 1)
                val fixerItem = fixerDataItem.value
                val currencyValue = fixerRepository.ratesValue(currencyName!!, fixerItem!!)

                tempList?.add(
                    CurrencyItem(
                        date = dateToString(),
                        isDate = false,
                        currencyName = currencyName,
                        currencyValue = currencyValue
                    )
                )
            }
        }
        currencyItem.postValue(tempList)
    }
}