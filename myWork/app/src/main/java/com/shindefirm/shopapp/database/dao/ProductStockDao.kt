package com.shindefirm.shopapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shindefirm.shopapp.database.modal.NewProduct
import com.shindefirm.shopapp.database.modal.ProductStock

@Dao
interface ProductStockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductStock(productStock: ProductStock):Long

    @Update
    fun updateProductStock(productStock: ProductStock)

    @Query("update ProductStock set quant=quant+ :pQuant,latestPrice=:price where title= :pName ")
    fun updateProductStockByPName(pName: String,pQuant:Int,price:Double)

    @Delete
    fun deleteProductStock(productStock: ProductStock)

    @Query("delete from ProductStock")
    fun deleteAllProductStock()

    @Query("select * from ProductStock order by id desc")
    fun getAllProductStock():List<ProductStock>
}