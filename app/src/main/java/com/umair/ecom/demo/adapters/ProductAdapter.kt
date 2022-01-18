package com.umair.ecom.demo.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.umair.ecom.demo.R
import com.umair.ecom.demo.adapters.base.BaseRecyclerViewAdapter
import com.umair.ecom.demo.data.models.ProductItemResponse
import com.umair.ecom.demo.databinding.ItemProductLayoutBinding
import java.math.BigDecimal
import java.math.RoundingMode

class ProductAdapter : BaseRecyclerViewAdapter<ProductAdapter.ViewHolder, ProductItemResponse>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val itemBinding: ItemProductLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: ProductItemResponse) {
            itemBinding.apply {
                tvTitle.text = item.title
                cpItemProductCategory.text = item.category
                tvPrice.apply {
                    text = context.getString(R.string.label_product_price, item.price.toString())
                }

                val newPrice = item.price.times(1.5)
                val rounded = BigDecimal(newPrice).setScale(2, RoundingMode.HALF_UP).toDouble()
                tvOriginalPrice.apply {
                    text = context.getString(R.string.label_product_price, rounded.toString())
                    paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }



                ivCover.load(item.image) {
                    crossfade(true)
                    placeholder(R.color.loading_gray)
                }
            }
        }
    }
}
