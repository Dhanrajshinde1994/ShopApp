package com.shindefirm.shopapp.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.shindefirm.shopapp.adapter.StoreAdapter.StoreViewHolder
import com.shindefirm.shopapp.database.modal.NewProduct
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.shindefirm.shopapp.R
import android.widget.TextView
import android.widget.LinearLayout
import com.shindefirm.shopapp.database.modal.StoreProductList
import java.util.ArrayList

class StoreAdapter : RecyclerView.Adapter<StoreViewHolder> {
    private var storeProductList: ArrayList<StoreProductList>
    private var UserId: String? = null

    constructor(storeProductList: ArrayList<StoreProductList>, UserId: String?) {
        this.storeProductList = storeProductList
        this.UserId = UserId
    }

    constructor() {
        storeProductList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        return StoreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        if (storeProductList.size > 0) holder.bindData(storeProductList[position], position)
        holder.llMainProduct.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return storeProductList.size
    }

    class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSrno: TextView
        var tvProductName: TextView
        var tvProductQuant: TextView
        var tvProductBarcode: TextView
        private val context: Context
        var llMainProduct: LinearLayout
        fun bindData(storeProductList: StoreProductList, position: Int) {
//            tvSrno.setText(position + 1)
            tvSrno.text= (position + 1).toString()
            tvProductName.text = storeProductList.title
            tvProductQuant.text = storeProductList.quant.toString() + ""
            tvProductBarcode.text = storeProductList.barcode
            //
        }

        init {
            context = itemView.context
            tvSrno = itemView.findViewById(R.id.tvSrno)
            tvProductName = itemView.findViewById(R.id.tvProductName)
            tvProductQuant = itemView.findViewById(R.id.tvProductQuant)
            tvProductBarcode = itemView.findViewById(R.id.tvProductBarcode)
            llMainProduct = itemView.findViewById(R.id.llMainProduct)
        }
    }
}