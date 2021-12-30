package com.shindefirm.shopapp

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindefirm.shopapp.adapter.ProductAdapter
import com.shindefirm.shopapp.database.AppDatabase
import com.shindefirm.shopapp.database.modal.NewProduct
import com.shindefirm.shopapp.database.modal.ProductStock
import com.shindefirm.shopapp.util.AppUtils
import com.shindefirm.shopapp.util.Logger
import com.shindefirm.shopapp.util.TextReaderAnalyzer
import kotlinx.android.synthetic.main.activity_store.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


class InsertProductActivity : AppCompatActivity(),View.OnClickListener {

    var db:AppDatabase?=null
    var btnAdd:Button?=null

    private var startTextRecog:Button?=null
    private var endTextRecog:Button?=null
    var etPName:EditText?=null
    var etPPrice:EditText?=null
    var etMfgDate:EditText?=null
    var etExpDate:EditText?=null
    var etBestBeforeDate:EditText?=null
    var spPUnit:Spinner?=null
    var rvProductDetail:RecyclerView?=null
    var productAdapter:ProductAdapter?=null
    var newProductList=ArrayList<NewProduct>();
    var toolbar:androidx.appcompat.widget.Toolbar?=null

    private val TAG: String?="InsertProductActivity"
    var isCameraStart:Boolean=true

    private val cameraExecutor: ExecutorService by lazy { Executors.newSingleThreadExecutor() }
    private val imageAnalyzer by lazy {
        ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_16_9)
            .build()
            .also {
                it.setAnalyzer(
                    cameraExecutor,
                    TextReaderAnalyzer(::onTextFound)
                )
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_product)

        initControls()
        fillSpinner()
        fillRecycleDataData()
        startCamera()

    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            Runnable {
                val preview = Preview.Builder()
                    .build()
                    .also { it.setSurfaceProvider(cameraPreviewView.surfaceProvider) }
                cameraProviderFuture.get().bind(preview, imageAnalyzer)
            },
            ContextCompat.getMainExecutor(this)
        )
    }

    private fun ProcessCameraProvider.bind(
        preview: Preview,
        imageAnalyzer: ImageAnalysis
    ) = try {
        unbindAll()
        bindToLifecycle(
            this@InsertProductActivity,
            CameraSelector.DEFAULT_BACK_CAMERA,
            preview,
            imageAnalyzer
        )
    } catch (ise: IllegalStateException) {
        // Thrown if binding is not done from the main thread
        Log.e(TAG, "Binding failed", ise)
    }

    private fun onTextFound(foundText: String)  {
        if(isCameraStart) {
            Log.d(TAG, " We got new text: $foundText")
//            Logger.showToast(this, foundText, true)
            etPName?.setText(foundText)
            isCameraStart=false
        }
    }

    private fun fillRecycleDataData() {

        newProductList= db?.newProduct()?.getAllNewProducts() as ArrayList<NewProduct>
        productAdapter = ProductAdapter(newProductList, "")
        rvProductDetail?.adapter = productAdapter
        productAdapter?.notifyDataSetChanged()
    }

    private fun fillSpinner() {
        val contacts: ArrayList<String> = ArrayList()

            contacts.add("Unit")
            contacts.add("Kilo")
            contacts.add("Liter")
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            contacts
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spPUnit?.setAdapter(adapter)
    }

    private fun initControls() {

        db= AppDatabase.getInstance(this)
        toolbar=findViewById(R.id.apptoolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        toolbar?.findViewById<TextView>(R.id.tvTitle)?.text=resources.getString(R.string.strAddProduct)
        toolbar?.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.vtr_back))
        toolbar?.setNavigationOnClickListener(View.OnClickListener { view: View? -> onBackPressed() })

        etPName=findViewById(R.id.etPName)
        etPPrice=findViewById(R.id.etPPrice)
        etMfgDate=findViewById(R.id.etMfgDate)
        etExpDate=findViewById(R.id.etExpDate)
        etBestBeforeDate=findViewById(R.id.etBestBeforeDate)
        spPUnit=findViewById(R.id.spPUnit)
        btnAdd=findViewById(R.id.btnAdd)
        startTextRecog=findViewById(R.id.startTextRecog)
        endTextRecog=findViewById(R.id.endTextRecog)
        rvProductDetail=findViewById(R.id.rvProductDetail)

        btnAdd?.setOnClickListener(this)

        productAdapter = ProductAdapter(newProductList, "")
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rvProductDetail?.layoutManager = layoutManager
        rvProductDetail?.itemAnimator = DefaultItemAnimator()


        etMfgDate?.setOnClickListener {

            AppUtils.hideSoftKeyboard(this@InsertProductActivity)
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                val month=monthOfYear+1
                etMfgDate?.setText("$dayOfMonth/$month/$year")

            }, year, month, day)

            dpd.show()
        }

        etExpDate?.setOnClickListener {

            AppUtils.hideSoftKeyboard(this@InsertProductActivity)
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                val month=monthOfYear+1
                etExpDate?.setText("$dayOfMonth/$month/$year")

            }, year, month, day)

            dpd.show()
        }

        startTextRecog?.setOnClickListener {
            isCameraStart=true
        }

        endTextRecog?.setOnClickListener {
            isCameraStart=false
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {

        when(v?.id)
        {
          R.id.btnAdd->{
              if(validateForm())
              {
                 var pName:String=if(etPName?.text.toString().isEmpty()) "" else etPName?.text.toString()
                 var pPrice:Double=if(etPPrice?.text.toString().isEmpty()) 0.0 else etPPrice?.text.toString().toDouble()
                 var pUnit:String=if(spPUnit?.selectedItem.toString().isEmpty()) "" else spPUnit?.selectedItem.toString()
                 var pMfgDate:String=if(etMfgDate?.text.toString().isEmpty()) "" else etMfgDate?.text.toString()
                 var pExpDate:String=if(etExpDate?.text.toString().isEmpty()) "" else etExpDate?.text.toString()

                  var count: Int? =db?.newProduct()?.getAllNewProductsCount(pName)
                if(count!! <=0) {

                    db?.newProduct()
                        ?.insertNewProduct(NewProduct(pName, pPrice, pUnit, pMfgDate, pExpDate))

                    db?.productStock()?.insertProductStock(
                        ProductStock(
                            pName, 0, 0, 0, 0, 0,0.0,
                            SimpleDateFormat("dd/MM/yyyy").format(Date())
                        )
                    )
                }
                  else{
                      Logger.showToast(this,resources.getString(R.string.strProductPresent),true)
                }

                  fillRecycleDataData()
                  clearFields()
                  etPName?.focusable
              }
          }

        }
    }

    private fun clearFields() {
        etPName?.setText("")
        etPPrice?.setText("")
        etMfgDate?.setText("")
        etExpDate?.setText("")

    }


    private fun validateForm(): Boolean {

        var isValid=false
        if(etPName?.text.toString().isEmpty())
        {
           Logger.showToast(this,resources.getString(R.string.strPleaseAddProductName),true)
        }
        else if(etPPrice?.text.toString().isEmpty())
        {
            Logger.showToast(this,resources.getString(R.string.strPleaseAddProductPrice),true)
        }
        else
        {
           isValid=true
        }

        return isValid
    }
}