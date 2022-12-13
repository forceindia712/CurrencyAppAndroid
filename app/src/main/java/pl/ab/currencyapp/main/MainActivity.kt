package pl.ab.currencyapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.ab.currencyapp.currlist.CurrencyListActivity
import pl.ab.currencyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        binding.button.setOnClickListener {
            val intent = Intent(this, CurrencyListActivity::class.java)
            startActivity(intent)
        }
    }
}