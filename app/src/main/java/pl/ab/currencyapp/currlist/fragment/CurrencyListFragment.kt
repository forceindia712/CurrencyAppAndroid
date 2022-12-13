package pl.ab.currencyapp.currlist.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.ab.currencyapp.R
import pl.ab.currencyapp.currlist.adapter.CurrencyListAdapter
import pl.ab.currencyapp.currlist.item.CurrencyItem
import pl.ab.currencyapp.currlist.model.CurrencyListViewModel
import pl.ab.currencyapp.databinding.CurrencyListFragmentBinding
import pl.ab.currencyapp.singleton.Config

class CurrencyListFragment : Fragment(), CurrencyListAdapter.ClickListener {

    val TAG = "CurrencyListFragment"

    private lateinit var binding: CurrencyListFragmentBinding
    private lateinit var rvAdpter: CurrencyListAdapter
    private val viewModel: CurrencyListViewModel by activityViewModels()

    private var currencyList: ArrayList<CurrencyItem> = arrayListOf()
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CurrencyListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.GONE
        observeUI()
        initUI()
    }

    fun initUI() {
        currencyList = viewModel.getCurrencyList()
        binding.textView2.text = "Podstawowa waluta " + Config.DEFAULT_CURRENCY
        rvAdpter = CurrencyListAdapter(currencyList)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = rvAdpter
        }
        rvAdpter.setClickListener(this)
        binding.recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading && linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == currencyList.size - 1) {
                    isLoading = true
                    changeList()
                }
            }
        })
    }

    private fun changeList() {
        binding.progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.updateCurrencyList()
            isLoading = false
            binding.progressBar.visibility = View.GONE
        }, 2000)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeUI() {
        viewModel.currencyItem.observe(viewLifecycleOwner) {
            currencyList = it
            rvAdpter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(item: CurrencyItem, holder: RecyclerView.ViewHolder, position: Int) {
        viewModel.setCurrencyItem(item)
        findNavController().navigate(R.id.action_CurrencyListFragment_to_CurrencyItemFragment)
    }
}