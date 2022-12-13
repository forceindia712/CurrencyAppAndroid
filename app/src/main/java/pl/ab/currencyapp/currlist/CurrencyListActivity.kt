package pl.ab.currencyapp.currlist


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import pl.ab.currencyapp.currlist.model.CurrencyListViewModel
import pl.ab.currencyapp.currlist.model.CurrencyListViewModelFactory
import pl.ab.currencyapp.databinding.ActivityCurrencyListBinding
import pl.ab.currencyapp.repository.FixerRepository


class CurrencyListActivity : AppCompatActivity() {

    val TAG = "CurrencyListActivity"

    private lateinit var binding: ActivityCurrencyListBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var viewModel: CurrencyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCurrencyListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, CurrencyListViewModelFactory(FixerRepository()))[CurrencyListViewModel::class.java]
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}