package pl.ab.currencyapp.currlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import pl.ab.currencyapp.currlist.item.CurrencyItem
import pl.ab.currencyapp.currlist.model.CurrencyListViewModel
import pl.ab.currencyapp.databinding.CurrencyItemFragmentBinding
import pl.ab.currencyapp.singleton.Config


class CurrencyItemFragment : Fragment() {

    val TAG = "CurrencyItemFragment"

    private lateinit var binding: CurrencyItemFragmentBinding
    private val viewModel: CurrencyListViewModel by activityViewModels()
    private var currencyItem = CurrencyItem()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CurrencyItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyItem = viewModel.getCurrencyItem()
        initUI()
    }

    private fun initUI() {
        binding.textView2.text = "Podstawowa waluta " + Config.DEFAULT_CURRENCY
        binding.textView3.text = currencyItem.date
        binding.textView4.text = currencyItem.currencyName + " - " + currencyItem.currencyValue
    }
}