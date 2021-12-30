package com.shindefirm.shopapp.database.modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["title"], unique = true)])
data class ProductStock(@ColumnInfo(defaultValue = "NA")val title:String,
                        @ColumnInfo(defaultValue = "0")val quant:Int,
                        @ColumnInfo(defaultValue = "0")val sale:Int,
                        @ColumnInfo(defaultValue = "0") val expire:Int,
                        @ColumnInfo(defaultValue = "0")val damage:Int,
                        @ColumnInfo(defaultValue = "0") val waste:Int,
                        @ColumnInfo(defaultValue = "0") val latestPrice:Double,
                        val lastDat:String,
                        @PrimaryKey(autoGenerate = true) val id : Int?=null)