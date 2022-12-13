package pl.ab.currencyapp.currlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.ab.currencyapp.currlist.item.CurrencyItem
import pl.ab.currencyapp.databinding.ItemFixerDataBinding
import pl.ab.currencyapp.databinding.ItemFixerWalutaBinding


class CurrencyListAdapter(private var list: ArrayList<CurrencyItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TAG = "CurrencyListAdapter"

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_DATA = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val bindingWaluta = ItemFixerWalutaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ItemViewHolder(bindingWaluta)
        } else {
            val bindingData = ItemFixerDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            DataViewHolder(bindingData)
        }
    }

    interface ClickListener {
        fun onItemClick(item: CurrencyItem, holder: RecyclerView.ViewHolder, position: Int)
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    private var clickListener: ClickListener? = null

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is DataViewHolder -> dateItemRows(viewHolder, position)
            is ItemViewHolder -> populateItemRows(viewHolder, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position].isDate) VIEW_TYPE_DATA else VIEW_TYPE_ITEM
    }

    private inner class DataViewHolder(val binding: ItemFixerDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val containerView: View
            get() = binding.root
    }

    private inner class ItemViewHolder(val binding: ItemFixerWalutaBinding) : RecyclerView.ViewHolder(binding.root) {
        val containerView: View
            get() = binding.root
    }

    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {
        val item = list[position]
        viewHolder.binding.CURRENCY.text = item.currencyName
        viewHolder.binding.wartosc.text = item.currencyValue.toString()
        viewHolder.containerView.setOnClickListener {
            clickListener?.onItemClick(item, viewHolder, position)
        }
    }

    private fun dateItemRows(viewHolder: DataViewHolder, position: Int) {
        val item = list[position]
        viewHolder.binding.data.text = item.date
    }
}