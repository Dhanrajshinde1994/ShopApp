package com.shindefirm.shopapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity(),View.OnClickListener {

    var llAddProduct:LinearLayout?=null
    var llStore:LinearLayout?=null
    val permissions = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initControls()

        if (hasNoPermissions()) {
            requestPermission()
        }

    }



    private fun initControls() {

        llAddProduct=findViewById(R.id.llAddProduct)
        llStore=findViewById(R.id.llStore)


        llAddProduct?.setOnClickListener(this)
        llStore?.setOnClickListener(this)



    }



    private fun hasNoPermissions(): Boolean{
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this, permissions,0)
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.llAddProduct->{
                startActivity(Intent(this,InsertProductActivity::class.java))

            }


            R.id.llStore->{
                startActivity(Intent(this,StoreActivity::class.java))

            }


        }

    }
}