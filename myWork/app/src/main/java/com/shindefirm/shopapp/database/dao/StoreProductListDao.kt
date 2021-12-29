package com.shindefirm.shopapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shindefirm.shopapp.database.modal.NewProduct
import com.shindefirm.shopapp.database.modal.StoreProductList

@Dao
interface StoreProductListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStoreProductList(storeProductList: StoreProductList)

    @Update
    fun updateStoreProductList(storeProductList: StoreProductList)

    @Delete
    fun deleteStoreProductList(storeProductList: StoreProductList)

    @Query("delete from StoreProductList")
    fun deleteAllStoreProductList()

    @Query("select * from StoreProductList order by id desc")
    fun getAllStoreProductList():List<StoreProductList>
}