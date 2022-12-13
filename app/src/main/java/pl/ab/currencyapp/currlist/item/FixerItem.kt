package pl.ab.currencyapp.currlist.item

data class FixerItem(
    val base: String? = null,
    val date: String? = null,
    val historical: Boolean? = null,
    val rates: Rates? = null,
    val success: Boolean? = null,
    val timestamp: Int? = null,
)