package pl.ab.currencyapp.repository

import android.annotation.SuppressLint
import android.util.Log
import pl.ab.currencyapp.currlist.ApiCallback
import pl.ab.currencyapp.currlist.FixerApiInterface
import pl.ab.currencyapp.currlist.item.FixerItem
import pl.ab.currencyapp.currlist.item.QueryFixer
import pl.ab.currencyapp.singleton.Config
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

class FixerRepository {

    val TAG = "FixerRepository"

    fun downloadAPI(data: String, callback: ApiCallback) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.SERVER_API_URL)
            .build()
            .create(FixerApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData(data, Config.DEFAULT_SYMBOLS, Config.DEFAULT_CURRENCY, Config.SERVER_KEY)

        retrofitData.enqueue(object: Callback<FixerItem> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(call: Call<FixerItem>, response: Response<FixerItem>) {
                val fixerData = response.body() ?: FixerItem()
                    callback.onActionSuccess(fixerData)
            }

            override fun onFailure(call: Call<FixerItem>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
                callback.onActionFailure(t)
            }
        })
    }

    fun getDefaultCurrencies(): QueryFixer {
        val query = QueryFixer(listOf("PLN", "USD", "GBP", "CHF", "SEK")) // temporary
        return query
    }

    fun dateToString(data: Calendar): String {
        val date = StringBuilder()
        val year = data.get(Calendar.YEAR)
        date.append(year)
        date.append("-")

        val month = data.get(Calendar.MONTH)
        if (month < 10)
            date.append("0" + month)
        else
            date.append(month)
        date.append("-")

        val day = data.get(Calendar.DAY_OF_MONTH)
        if (day < 10)
            date.append("0" + day)
        else
            date.append(day)

        return date.toString()
    }

    fun ratesValue(currency: String, fixerItem: FixerItem): Double? {
        when(currency) {
            "AED" -> return fixerItem.rates?.AED
            "AFN" -> return fixerItem.rates?.AFN
            "ALL" -> return fixerItem.rates?.ALL
            "AMD" -> return fixerItem.rates?.AMD
            "ANG" -> return fixerItem.rates?.ANG
            "AOA" -> return fixerItem.rates?.AOA
            "ARS" -> return fixerItem.rates?.ARS
            "AUD" -> return fixerItem.rates?.AUD
            "AWG" -> return fixerItem.rates?.AWG
            "AZN" -> return fixerItem.rates?.AZN
            "BAM" -> return fixerItem.rates?.BAM
            "BBD" -> return fixerItem.rates?.BBD
            "BDT" -> return fixerItem.rates?.BDT
            "BGN" -> return fixerItem.rates?.BGN
            "BHD" -> return fixerItem.rates?.BHD
            "BIF" -> return fixerItem.rates?.BIF
            "BMD" -> return fixerItem.rates?.BMD
            "BND" -> return fixerItem.rates?.BND
            "BOB" -> return fixerItem.rates?.BOB
            "BRL" -> return fixerItem.rates?.BRL
            "BSD" -> return fixerItem.rates?.BSD
            "BTC" -> return fixerItem.rates?.BTC
            "BTN" -> return fixerItem.rates?.BTN
            "BWP" -> return fixerItem.rates?.BWP
            "BYN" -> return fixerItem.rates?.BYN
            "BYR" -> return fixerItem.rates?.BYR
            "BZD" -> return fixerItem.rates?.BZD
            "CAD" -> return fixerItem.rates?.CAD
            "CDF" -> return fixerItem.rates?.CDF
            "CHF" -> return fixerItem.rates?.CHF
            "CLF" -> return fixerItem.rates?.CLF
            "CLP" -> return fixerItem.rates?.CLP
            "CNY" -> return fixerItem.rates?.CNY
            "COP" -> return fixerItem.rates?.COP
            "CRC" -> return fixerItem.rates?.CRC
            "CUC" -> return fixerItem.rates?.CUC
            "CUP" -> return fixerItem.rates?.CUP
            "CVE" -> return fixerItem.rates?.CVE
            "CZK" -> return fixerItem.rates?.CZK
            "DJF" -> return fixerItem.rates?.DJF
            "DKK" -> return fixerItem.rates?.DKK
            "DOP" -> return fixerItem.rates?.DOP
            "DZD" -> return fixerItem.rates?.DZD
            "EGP" -> return fixerItem.rates?.EGP
            "ERN" -> return fixerItem.rates?.ERN
            "ETB" -> return fixerItem.rates?.ETB
            "EUR" -> return fixerItem.rates?.EUR?.toDouble()
            "FJD" -> return fixerItem.rates?.FJD
            "FKP" -> return fixerItem.rates?.FKP
            "GBP" -> return fixerItem.rates?.GBP
            "GEL" -> return fixerItem.rates?.GEL
            "GGP" -> return fixerItem.rates?.GGP
            "GHS" -> return fixerItem.rates?.GHS
            "GIP" -> return fixerItem.rates?.GIP
            "GMD" -> return fixerItem.rates?.GMD
            "GNF" -> return fixerItem.rates?.GNF
            "GTQ" -> return fixerItem.rates?.GTQ
            "GYD" -> return fixerItem.rates?.GYD
            "HKD" -> return fixerItem.rates?.HKD
            "HNL" -> return fixerItem.rates?.HNL
            "HRK" -> return fixerItem.rates?.HRK
            "HTG" -> return fixerItem.rates?.HTG
            "HUF" -> return fixerItem.rates?.HUF
            "IDR" -> return fixerItem.rates?.IDR
            "ILS" -> return fixerItem.rates?.ILS
            "IMP" -> return fixerItem.rates?.IMP
            "INR" -> return fixerItem.rates?.INR
            "IQD" -> return fixerItem.rates?.IQD
            "IRR" -> return fixerItem.rates?.IRR
            "ISK" -> return fixerItem.rates?.ISK
            "JEP" -> return fixerItem.rates?.JEP
            "JMD" -> return fixerItem.rates?.JMD
            "JOD" -> return fixerItem.rates?.JOD
            "JPY" -> return fixerItem.rates?.JPY
            "KES" -> return fixerItem.rates?.KES
            "KGS" -> return fixerItem.rates?.KGS
            "KHR" -> return fixerItem.rates?.KHR
            "KMF" -> return fixerItem.rates?.KMF
            "KPW" -> return fixerItem.rates?.KPW
            "KRW" -> return fixerItem.rates?.KRW
            "KWD" -> return fixerItem.rates?.KWD
            "KYD" -> return fixerItem.rates?.KYD
            "KZT" -> return fixerItem.rates?.KZT
            "LAK" -> return fixerItem.rates?.LAK
            "LBP" -> return fixerItem.rates?.LBP
            "LKR" -> return fixerItem.rates?.LKR
            "LRD" -> return fixerItem.rates?.LRD
            "LSL" -> return fixerItem.rates?.LSL
            "LTL" -> return fixerItem.rates?.LTL
            "LVL" -> return fixerItem.rates?.LVL
            "LYD" -> return fixerItem.rates?.LYD
            "MAD" -> return fixerItem.rates?.MAD
            "MDL" -> return fixerItem.rates?.MDL
            "MGA" -> return fixerItem.rates?.MGA
            "MKD" -> return fixerItem.rates?.MKD
            "MMK" -> return fixerItem.rates?.MMK
            "MNT" -> return fixerItem.rates?.MNT
            "MOP" -> return fixerItem.rates?.MOP
            "MRO" -> return fixerItem.rates?.MRO
            "MUR" -> return fixerItem.rates?.MUR
            "MVR" -> return fixerItem.rates?.MVR
            "MWK" -> return fixerItem.rates?.MWK
            "MXN" -> return fixerItem.rates?.MXN
            "MYR" -> return fixerItem.rates?.MYR
            "MZN" -> return fixerItem.rates?.MZN
            "NAD" -> return fixerItem.rates?.NAD
            "NGN" -> return fixerItem.rates?.NGN
            "NIO" -> return fixerItem.rates?.NIO
            "NOK" -> return fixerItem.rates?.NOK
            "NPR" -> return fixerItem.rates?.NPR
            "NZD" -> return fixerItem.rates?.NZD
            "OMR" -> return fixerItem.rates?.OMR
            "PAB" -> return fixerItem.rates?.PAB
            "PEN" -> return fixerItem.rates?.PEN
            "PGK" -> return fixerItem.rates?.PGK
            "PHP" -> return fixerItem.rates?.PHP
            "PKR" -> return fixerItem.rates?.PKR
            "PLN" -> return fixerItem.rates?.PLN
            "PYG" -> return fixerItem.rates?.PYG
            "QAR" -> return fixerItem.rates?.QAR
            "RON" -> return fixerItem.rates?.RON
            "RSD" -> return fixerItem.rates?.RSD
            "RUB" -> return fixerItem.rates?.RUB
            "RWF" -> return fixerItem.rates?.RWF
            "SAR" -> return fixerItem.rates?.SAR
            "SBD" -> return fixerItem.rates?.SBD
            "SCR" -> return fixerItem.rates?.SCR
            "SDG" -> return fixerItem.rates?.SDG
            "SEK" -> return fixerItem.rates?.SEK
            "SGD" -> return fixerItem.rates?.SGD
            "SHP" -> return fixerItem.rates?.SHP
            "SLL" -> return fixerItem.rates?.SLL
            "SOS" -> return fixerItem.rates?.SOS
            "SRD" -> return fixerItem.rates?.SRD
            "STD" -> return fixerItem.rates?.STD
            "SVC" -> return fixerItem.rates?.SVC
            "SYP" -> return fixerItem.rates?.SYP
            "SZL" -> return fixerItem.rates?.SZL
            "THB" -> return fixerItem.rates?.THB
            "TJS" -> return fixerItem.rates?.TJS
            "TMT" -> return fixerItem.rates?.TMT
            "TND" -> return fixerItem.rates?.TND
            "TOP" -> return fixerItem.rates?.TOP
            "TRY" -> return fixerItem.rates?.TRY
            "TTD" -> return fixerItem.rates?.TTD
            "TWD" -> return fixerItem.rates?.TWD
            "TZS" -> return fixerItem.rates?.TZS
            "UAH" -> return fixerItem.rates?.UAH
            "UGX" -> return fixerItem.rates?.UGX
            "USD" -> return fixerItem.rates?.USD
            "UYU" -> return fixerItem.rates?.UYU
            "UZS" -> return fixerItem.rates?.UZS
            "VEF" -> return fixerItem.rates?.VEF
            "VND" -> return fixerItem.rates?.VND
            "VUV" -> return fixerItem.rates?.VUV
            "WST" -> return fixerItem.rates?.WST
            "XAF" -> return fixerItem.rates?.XAF
            "XAG" -> return fixerItem.rates?.XAG
            "XAU" -> return fixerItem.rates?.XAU
            "XCD" -> return fixerItem.rates?.XCD
            "XDR" -> return fixerItem.rates?.XDR
            "XOF" -> return fixerItem.rates?.XOF
            "XPF" -> return fixerItem.rates?.XPF
            "YER" -> return fixerItem.rates?.YER
            "ZAR" -> return fixerItem.rates?.ZAR
            "ZMK" -> return fixerItem.rates?.ZMK
            "ZMW" -> return fixerItem.rates?.ZMW
            "ZWL" -> return fixerItem.rates?.ZWL
        }
        return 0.0
    }
}