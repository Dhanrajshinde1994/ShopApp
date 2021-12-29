package com.shindefirm.shopapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shindefirm.shopapp.database.modal.NewProduct

@Dao
interface NewProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewProduct(newProduct: NewProduct)

    @Update
    fun updateNewProduct(newProduct: NewProduct)

    @Delete
    fun deleteNewProduct(newProduct: NewProduct)

    @Query("delete from NewProduct")
    fun deleteAllProducts()

    @Query("select * from NewProduct order by id desc")
    fun getAllNewProducts():List<NewProduct>

    @Query("select Count(*) from NewProduct where title like UPPER( :pName||'%')")
    fun getAllNewProductsCount(pName:String):Int

    @Query("select price from NewProduct where title like UPPER( :pName||'%')")
    fun getNewProductsPrice(pName:String):Int
}