package com.shindefirm.shopapp.database.modal

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["title"], unique = true)])
data class NewProduct(val title:String,
                        val price:Double,
                      val unit:String,
                        val mfg_date:String,
                        val exp_date:String,
                        @PrimaryKey(autoGenerate = true) val id : Int?=null)