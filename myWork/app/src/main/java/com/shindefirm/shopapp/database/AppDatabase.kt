package com.shindefirm.shopapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.shindefirm.shopapp.database.dao.NewProductDao
import com.shindefirm.shopapp.database.dao.ProductStockDao
import com.shindefirm.shopapp.database.dao.StoreProductListDao
import com.shindefirm.shopapp.database.modal.NewProduct
import com.shindefirm.shopapp.database.modal.ProductStock
import com.shindefirm.shopapp.database.modal.StoreProductList

@Database(entities = [NewProduct::class,ProductStock::class,StoreProductList::class],version = 1,exportSchema = true)
abstract class AppDatabase : RoomDatabase(){

    abstract fun newProduct(): NewProductDao
    abstract fun productStock(): ProductStockDao
    abstract fun storeProductList(): StoreProductListDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "store_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }
    }

}