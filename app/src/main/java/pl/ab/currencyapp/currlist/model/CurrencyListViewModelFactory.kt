package pl.ab.currencyapp.currlist.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.ab.currencyapp.repository.FixerRepository

class CurrencyListViewModelFactory(private val fixerRepository: FixerRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyListViewModel(fixerRepository) as T
    }
}