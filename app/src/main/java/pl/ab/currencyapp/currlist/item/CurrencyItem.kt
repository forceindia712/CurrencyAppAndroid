package pl.ab.currencyapp.currlist.item

data class CurrencyItem(
    val date: String? = null,
    val isDate: Boolean = false,
    val currencyName: String? = null,
    val currencyValue: Double? = null,
)
