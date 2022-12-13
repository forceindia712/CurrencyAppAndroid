package pl.ab.currencyapp.currlist

import pl.ab.currencyapp.currlist.item.FixerItem
import retrofit2.Call
import retrofit2.http.*

interface FixerApiInterface {
    @GET("{date}")
    fun getData(
        @Path("date") data: String,
        @Query("symbols") symbols: String,
        @Query("base") currency: String,
        @Header("apikey") apikey: String,
    ): Call<FixerItem>
}