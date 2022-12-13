package pl.ab.currencyapp.currlist

import pl.ab.currencyapp.currlist.item.FixerItem

interface ApiCallback {
    fun onActionSuccess(successItem: FixerItem?)
    fun onActionFailure(throwableError: Throwable?)
}