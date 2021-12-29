package com.shindefirm.shopapp.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.shindefirm.shopapp.adapter.ProductAdapter.ProductViewHolder
import com.shindefirm.shopapp.database.modal.NewProduct
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.shindefirm.shopapp.R
import android.widget.TextView
import android.widget.LinearLayout
import java.util.ArrayList

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder> {
    private var newProductArrayList: ArrayList<NewProduct>
    private var UserId: String? = null

    constructor(newProductArrayList: ArrayList<NewProduct>, UserId: String?) {
        this.newProductArrayList = newProductArrayList
        this.UserId = UserId
    }

    constructor() {
        newProductArrayList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_products, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        if (newProductArrayList.size > 0) holder.bindData(newProductArrayList[position], position)
        holder.llMainProduct.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return newProductArrayList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvSrno: TextView
        var tvProductName: TextView
        var tvProductPrice: TextView
        var tvProductExpdate: TextView
        private val context: Context
        var llMainProduct: LinearLayout
        fun bindData(newProduct: NewProduct, position: Int) {
//            tvSrno.setText(position + 1)
            tvSrno.text= (position + 1).toString()
            tvProductName.text = newProduct.title
            tvProductPrice.text = newProduct.price.toString() + ""
            tvProductExpdate.text = newProduct.exp_date
            //
        }

        init {
            context = itemView.context
            tvSrno = itemView.findViewById(R.id.tvSrno)
            tvProductName = itemView.findViewById(R.id.tvProductName)
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice)
            tvProductExpdate = itemView.findViewById(R.id.tvProductExpdate)
            llMainProduct = itemView.findViewById(R.id.llMainProduct)
        }
    }
}