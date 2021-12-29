package com.shindefirm.shopapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.shindefirm.shopapp.adapter.ProductAdapter
import com.shindefirm.shopapp.adapter.StoreAdapter
import com.shindefirm.shopapp.database.AppDatabase
import com.shindefirm.shopapp.database.modal.NewProduct
import com.shindefirm.shopapp.database.modal.StoreProductList
import com.shindefirm.shopapp.util.AppUtils
import com.shindefirm.shopapp.util.Logger
import tech.hibk.searchablespinnerlibrary.SearchableItem
import tech.hibk.searchablespinnerlibrary.SearchableSpinner
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import kotlin.collections.ArrayList

class StoreActivity : AppCompatActivity() {

    var db: AppDatabase?=null
    var toolbar:androidx.appcompat.widget.Toolbar?=null

    private lateinit var codeScanner: CodeScanner
    private var etMfgDate:EditText?=null
    private var etExpDate:EditText?=null
    private var etPQuant:EditText?=null
//    private var atvPName:AutoCompleteTextView?=null
    private var sspProduct:SearchableSpinner?=null
    private var tvPBarcode:TextView?=null
    private var etProductPrice:EditText?=null
    private var btnAddStore:Button?=null
    private var rvStoreDetail:RecyclerView?=null
    var storeAdapter:StoreAdapter?=null
    var storeList=ArrayList<StoreProductList>();
    var newProductList=ArrayList<NewProduct>();



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        initControls()
        fillRecycleDataData()
        btnAddStore?.setOnClickListener(){

            if(validateForm())
            {
                var pName:String=if(sspProduct?.selectedItem?.title.toString().isEmpty()) "" else sspProduct?.selectedItem?.title.toString()
                var pPrice:Double=if(etProductPrice?.text.toString().isEmpty()) 0.0 else etProductPrice?.text.toString().toDouble()
                var pQuant:Int=if(etPQuant?.text.toString().isEmpty()) 0 else etPQuant?.text.toString().toInt()
                var pMfgDate:String=if(etMfgDate?.text.toString().isEmpty()) "" else etMfgDate?.text.toString()
                var pExpDate:String=if(etExpDate?.text.toString().isEmpty()) "" else etExpDate?.text.toString()
                var pBarcode:String=if(tvPBarcode?.text.toString().isEmpty()) "" else tvPBarcode?.text.toString()

                db?.storeProductList()?.insertStoreProductList(StoreProductList(pName,pBarcode,pQuant,pPrice,pMfgDate,pExpDate,SimpleDateFormat("dd-MM-yyyy").format(Date())))
                db?.productStock()?.updateProductStockByPName(pName,pQuant)

                fillRecycleDataData()
                clearFields()
//                atvPName?.focusable
            }


        }
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
        toolbar?.findViewById<TextView>(R.id.tvTitle)?.text=resources.getString(R.string.strStore)
        toolbar?.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.vtr_back))
        toolbar?.setNavigationOnClickListener(View.OnClickListener { view: View? -> onBackPressed() })





        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        etMfgDate=findViewById(R.id.etMfgDate)
        etExpDate=findViewById(R.id.etExpDate)
        etPQuant=findViewById(R.id.etPQuant)
//        atvPName=findViewById(R.id.atvPName)
        sspProduct=findViewById(R.id.sspProduct)
        tvPBarcode=findViewById(R.id.tvPBarcode)
        etProductPrice=findViewById(R.id.etProductPrice)
        btnAddStore=findViewById(R.id.btnAddStore)
        rvStoreDetail=findViewById(R.id.rvStoreDetail)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
//                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
          tvPBarcode?.text=it.text
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Logger.showToast(this,"Camera initialization error: ${it.message}",true)
            }
        }

        codeScanner.startPreview()

        scannerView.setOnClickListener {
            codeScanner.startPreview()
            tvPBarcode?.text=""
        }

        etMfgDate?.setOnClickListener {

            AppUtils.hideSoftKeyboard(this@StoreActivity)
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

            AppUtils.hideSoftKeyboard(this@StoreActivity)
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

        storeAdapter = StoreAdapter(storeList, "")
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rvStoreDetail?.layoutManager = layoutManager
        rvStoreDetail?.itemAnimator = DefaultItemAnimator()


        newProductList= db?.newProduct()?.getAllNewProducts() as ArrayList<NewProduct>

        var strList=ArrayList<SearchableItem>()
        var i=0;
        for (s in newProductList)
        {
            strList.add(SearchableItem(i.toLong(),s.title))
            i++
        }
        strList.add(0,SearchableItem(0,resources.getString(R.string.str_product_name)))
        sspProduct?.items=strList

        sspProduct?.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                var st =sspProduct?.selectedItem?.title

                var pr=db?.newProduct()?.getNewProductsPrice(st.toString())

                etProductPrice?.setText(pr.toString())

            }
        }

    }


    @SuppressLint("NotifyDataSetChanged")
    private fun fillRecycleDataData() {

        storeList= db?.storeProductList()?.getAllStoreProductList() as ArrayList<StoreProductList>
        storeAdapter = StoreAdapter(storeList, "")
        rvStoreDetail?.adapter = storeAdapter
        storeAdapter?.notifyDataSetChanged()
    }

    private fun clearFields() {
//        atvPName?.setText("")
        sspProduct?.setSelection(0)
        tvPBarcode?.setText("")
        etProductPrice?.setText("")
        etMfgDate?.setText("")
        etExpDate?.setText("")
        etPQuant?.setText("")
        codeScanner.startPreview()
    }

    private fun validateForm(): Boolean {

        var isValid=false
        if(sspProduct?.selectedItem?.title.toString().equals(resources.getString(R.string.str_product_name)))
        {
            Logger.showToast(this,resources.getString(R.string.strPleaseAddProductName),true)
        }
        else if(etPQuant?.text.toString().isEmpty())
        {
            Logger.showToast(this,resources.getString(R.string.strPleaseAddProductQuant),true)
        }
        else
        {
            isValid=true
        }

        return isValid
    }



    override fun onResume() {
        super.onResume()
//        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}