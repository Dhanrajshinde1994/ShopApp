package com.shindefirm.shopapp.database.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StoreProductList(val title:String,
                            @ColumnInfo(defaultValue = "0")val barcode:String,
                            @ColumnInfo(defaultValue = "0")val quant:Int,
                            @ColumnInfo(defaultValue = "0.0")val price:Double,
                            val mfg_date:String,
                            val exp_date:String,val add_date:String,
                            @PrimaryKey(autoGenerate = true) val id : Int?=null)