package pl.ab.currencyapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import pl.ab.currencyapp.currlist.ApiCallback
import pl.ab.currencyapp.currlist.item.QueryFixer
import pl.ab.currencyapp.repository.FixerRepository

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    val repository: FixerRepository = FixerRepository()
    val data = "2022-12-12"


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("pl.ab.currencyapp", appContext.packageName)
    }
}