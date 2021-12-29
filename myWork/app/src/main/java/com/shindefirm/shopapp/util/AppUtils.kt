package com.shindefirm.shopapp.util

import com.shindefirm.shopapp.listeners.NoticeDialogListener
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.shindefirm.shopapp.web_service.MethodOrdinal
import android.widget.Spinner
import com.shindefirm.shopapp.util.AppUtils
import android.widget.ArrayAdapter
import com.shindefirm.shopapp.R
import android.app.Activity
import android.app.AlertDialog
import androidx.annotation.RequiresApi
import android.os.Build
import android.util.DisplayMetrics
import kotlin.Throws
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.EditText
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.widget.DatePicker
import android.content.DialogInterface
import com.shindefirm.shopapp.util.AppUtils.FileMetaData
import android.provider.OpenableColumns
import android.content.Intent
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager
import android.net.Uri
import android.webkit.MimeTypeMap
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.gms.common.util.IOUtils
import java.io.*
import java.lang.Exception
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object AppUtils {
    var dialogSelectedValue = ""
    var noticeDialogListener: NoticeDialogListener? = null
    @JvmStatic
    fun isInternetAvailable(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            ?: return false
        val netInfo = cm.allNetworkInfo
        var isConnected = false
        for (ni in netInfo) {
            if (ni.typeName.equals("WIFI", ignoreCase = true) && ni.isConnected) {
                isConnected = true
                break
            } else if (ni.typeName.equals("MOBILE", ignoreCase = true) && ni.isConnected) {
                isConnected = true
                break
            }
        }
        return isConnected
    }

    fun errorMessageInResponse(
        responseOrdinal: MethodOrdinal,
        error: String
    ) {
        Log.e(Constants.LOG_TAG, responseOrdinal.name + ": " + error)
    }

    fun setSpinnerAdapter(
        context: Context?,
        spinner: Spinner,
        dataList: List<Any>?,
        firstElementString: String
    ) {
        val arrayList = ArrayList(dataList)
        if (isValidString(firstElementString)) arrayList.add(0, firstElementString)
        spinner.adapter = ArrayAdapter(
            context!!,
            R.layout.row_spinner_dropdown,
            R.id.txtSpinnerDropDown,  //                android.R.layout.simple_spinner_dropdown_item,
            arrayList
        )
    }

    fun setSpinnerAdapterReturn(
        context: Context?,
        spinner: Spinner,
        dataList: List<Any>?,
        firstElementString: String
    ): ArrayAdapter<Any> {
        val arrayList = ArrayList(dataList)
        if (isValidString(firstElementString)) arrayList.add(0, firstElementString)
        val objectArrayAdapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_dropdown_item,
            arrayList
        )
        spinner.adapter = objectArrayAdapter
        return objectArrayAdapter
    }

    @JvmStatic
    fun isValidString(dataString: String?): Boolean {
        return dataString != null && !dataString.isEmpty()
    }

    fun convertStringToDouble(value: String?): Double {
        var returnValue = 0.0
        if (value == null || value.isEmpty() || value == "") {
            return returnValue
        }
        returnValue = try {
            value.toDouble()
        } catch (e: Exception) {
            e.printStackTrace()
            0.0
        }
        return returnValue
    }

    fun hideSoftKeyboard(con: Activity) {
        val imm = con.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        /*   imm.hideSoftInputFromWindow(con.getCurrentFocus().getWindowToken(),
                0);*/imm.hideSoftInputFromWindow(
            con.window.decorView.rootView.windowToken,
            0
        )
    }

    fun hideFragmentSoftKeyboard(con: Activity, v: View) {
        val imm = con.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun setLanguage(context: Context, language: String?) {
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(Locale(language, Constants.COUNTRY)) // API 17+ only.
        res.updateConfiguration(conf, dm)
        context.applicationContext.resources
            .updateConfiguration(
                conf,
                context.resources.displayMetrics
            )
    }

    val currentTimeInMills: Long
        get() {
            val time = System.currentTimeMillis()
            Logger.setLog("Time in Millis-->$time", Logger.LogEnum.INFORMATION)
            return time
        }
    val dateTime: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.ENGLISH)
            val cal1 = Calendar.getInstance()
            return dateFormat.format(cal1.time)
        }
    val dateTime_HH_MM_SS: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
            val cal1 = Calendar.getInstance()
            return dateFormat.format(cal1.time)
        }
    val date: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val cal1 = Calendar.getInstance()
            return dateFormat.format(cal1.time)
        }

    fun get_account_date(): String {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
        val cal1 = Calendar.getInstance()
        return dateFormat.format(cal1.time)
    }

    fun getDateFromString(date: String?): Date? {
        var dt: Date? = null
        if (date != null) {
            try {
                dt = Date(SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH).parse(date).time)
            } catch (pe: ParseException) {
                pe.printStackTrace()
            }
        }
        return dt
    }

    /*
    public static OTPUserModel getUserDetails(Context context) {
        com.stpl.ncp_youth.util.SharedPreferencesUtil spUtil = new com.stpl.ncp_youth.util.SharedPreferencesUtil(context);
        String getUserData = spUtil.fetchSharedPreferenesString(
                OTPUserModel.class.getName(), "");
        Type getOTPType = new TypeToken<ArrayList<OTPUserModel>>() {
        }.getType();

        ArrayList<OTPUserModel> otpUserModels =
                new Gson().fromJson(getUserData, getOTPType);

        return otpUserModels.get(0);
    }
*/
    @Throws(IOException::class)
    fun getBytesImagesFromUri(inputStream: InputStream): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }

    @Throws(FileNotFoundException::class)
    fun convertImageToByte(uri: Uri?, context: Context): ByteArray {
        val cr = context.contentResolver
        val inputStream = cr.openInputStream(uri!!)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val baos = ByteArrayOutputStream()
        val byteArray: ByteArray
        val imageSize = bitmap.byteCount / Constants.SIZE_1_MB
        Logger.setLog("Image Size: $imageSize", Logger.LogEnum.INFORMATION)
        if (imageSize < 2) { // below 2 MB Length
            bitmap.compress(Bitmap.CompressFormat.JPEG, Constants.WITHOUT_COMPRESS_IMAGE, baos)
            byteArray = baos.toByteArray()
            Logger.setLog("Image Size: " + byteArray.size, Logger.LogEnum.INFORMATION)
            return byteArray
        }
        if (imageSize < 5) { // Range of 5 MB to 2 MB
            bitmap.compress(Bitmap.CompressFormat.JPEG, Constants.COMPRESS_80_PERCENT_IMAGE, baos)
            byteArray = baos.toByteArray()
            Logger.setLog("Image Size: " + byteArray.size, Logger.LogEnum.INFORMATION)
            return byteArray
        }
        if (imageSize < 10) {  // Range of 10 MB to 5 MB
            bitmap.compress(Bitmap.CompressFormat.JPEG, Constants.COMPRESS_60_PERCENT_IMAGE, baos)
            byteArray = baos.toByteArray()
            Logger.setLog("Image Size: " + byteArray.size, Logger.LogEnum.INFORMATION)
            return byteArray
        }

        // Above 10 MB
        bitmap.compress(Bitmap.CompressFormat.JPEG, Constants.COMPRESS_30_PERCENT_IMAGE, baos)
        byteArray = baos.toByteArray()
        Logger.setLog("Image Size: " + byteArray.size, Logger.LogEnum.INFORMATION)
        return byteArray
    }

    /**
     * This method is use for show date picker dialog.
     *
     * @param context            it is use for ref. activity or fragment.
     * @param editText           it is use for edit text controller, want to set date.
     * @param minDateTimeMillies if min date want to set then pass it to min time milliseconds.
     * Otherwise pass it to 0L.
     * @param maxDateTimeMillies if max date want to set then pass it to max time milliseconds.
     * *                           Otherwise pass it to 0L.
     */
    fun datePicker(
        context: Context?,
        editText: EditText,
        minDateTimeMillies: Long,
        maxDateTimeMillies: Long
    ) {
        val year: Int
        val month: Int
        val day: Int
        val calendar = Calendar.getInstance()
        year = calendar[Calendar.YEAR]
        month = calendar[Calendar.MONDAY]
        day = calendar[Calendar.DATE]
        val datePickerDialog = DatePickerDialog(
            context!!,
            { view: DatePicker?, year1: Int, month1: Int, dayOfMonth: Int ->
                val calendar1 = Calendar.getInstance()
                calendar1[Calendar.YEAR] = year1
                calendar1[Calendar.MONTH] = month1
                calendar1[Calendar.DAY_OF_MONTH] = dayOfMonth
                val selectedDate = Constants.ddMMyyyy.format(calendar1.timeInMillis)
                editText.setText(selectedDate)
            },
            year, month, day
        )
        if (minDateTimeMillies != 0L) {
            datePickerDialog.datePicker.minDate = minDateTimeMillies
        }
        if (maxDateTimeMillies != 0L) {
            datePickerDialog.datePicker.maxDate = maxDateTimeMillies
        }
        datePickerDialog.show()
    }

    fun showAlertDialog(
        context: Context?,
        title: String?, message: String?,
        isCancelable: Boolean,
        isPositiveButton: Boolean,
        positiveButtonTitle: String?,
        positiveButtonClickListener: DialogInterface.OnClickListener?,
        isNegativeButton: Boolean,
        negativeButtonTitle: String?,
        negativeButtonClickListener: DialogInterface.OnClickListener?
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setTitle(title)
        builder.setCancelable(isCancelable)
        builder.setIcon(R.mipmap.ic_launcher)
        if (isPositiveButton) {
            val positiveButtonClickListenerObject: DialogInterface.OnClickListener
            positiveButtonClickListenerObject = positiveButtonClickListener
                ?: DialogInterface.OnClickListener { dialog: DialogInterface, id: Int -> dialog.dismiss() }
            builder.setPositiveButton(
                positiveButtonTitle ?: "",
                positiveButtonClickListenerObject
            )
        }
        if (isNegativeButton) {
            val negativeButtonClickListenerObject: DialogInterface.OnClickListener
            negativeButtonClickListenerObject = negativeButtonClickListener
                ?: DialogInterface.OnClickListener { dialog: DialogInterface, id: Int -> dialog.dismiss() }
            builder.setNegativeButton(
                negativeButtonTitle ?: "",
                negativeButtonClickListenerObject
            )
        }
        val alert = builder.create()
        alert.show()
    }

    fun setAlertDialog(
        context: Context, title: String?, msg: String?,
        listener: NoticeDialogListener
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton(context.getString(R.string.yes)) { dialog, which -> // Continue with delete operation
                listener.onDialogPositiveClick()
                Logger.setLog("In Yes", Logger.LogEnum.INFORMATION)
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(context.getString(R.string.no), null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

    fun getFileMetaData(context: Context, uri: Uri): FileMetaData? {
        val fileMetaData = FileMetaData()
        return if ("file".equals(uri.scheme, ignoreCase = true)) {
            val file = File(uri.path)
            fileMetaData.displayName = file.name
            fileMetaData.size = file.length()
            fileMetaData.path = file.path
            fileMetaData
        } else {
            val contentResolver = context.contentResolver
            val cursor = contentResolver
                .query(uri, null, null, null, null)
            fileMetaData.mimeType = contentResolver.getType(uri)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                    fileMetaData.displayName =
                        cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    if (!cursor.isNull(sizeIndex)) fileMetaData.size =
                        cursor.getLong(sizeIndex) else fileMetaData.size = -1
                    try {
                        fileMetaData.path = cursor.getString(cursor.getColumnIndexOrThrow("_data"))
                    } catch (e: Exception) {
                        // DO NOTHING, _data does not exist
                        e.printStackTrace()
                    }
                    return fileMetaData
                }
            } catch (e: Exception) {
                Logger.setLog(e.message.toString(), Logger.LogEnum.INFORMATION)
            } finally {
                cursor?.close()
            }
            null
        }
    }

    fun openWhatsAppConversationUsingUri(
        context: Context,
        numberWithCountryCode: String, message: String
    ) {
        val uri =
            Uri.parse("https://api.whatsapp.com/send?phone=$numberWithCountryCode&text=$message")
        val sendIntent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(sendIntent)
    }

    fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission!!
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    fun getMimeType(context: Context, uri: Uri): String? {
        val extension: String?

        //Check uri format to avoid null
        extension = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            //If scheme is a content
            val mime = MimeTypeMap.getSingleton()
            mime.getExtensionFromMimeType(context.contentResolver.getType(uri))
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            MimeTypeMap.getFileExtensionFromUrl(
                Uri.fromFile(File(uri.path)).toString()
            )
        }
        return extension
    }

    fun getFilePathFromURI(context: Context, contentUri: Uri?): String? {
        //copy file and send new file path
        val fileName = getFileName(contentUri)
        if (!TextUtils.isEmpty(fileName)) {
            val copyFile =
                File(context.getExternalFilesDir(null)!!.path + File.separator + fileName)
            copy(context, contentUri, copyFile)
            return copyFile.absolutePath
        }
        return null
    }

    fun getFileName(uri: Uri?): String? {
        if (uri == null) return null
        var fileName: String? = null
        val path = uri.path
        val cut = path!!.lastIndexOf('/')
        if (cut != -1) {
            fileName = path.substring(cut + 1)
        }
        return fileName
    }

    fun copy(context: Context, srcUri: Uri?, dstFile: File?) {
        try {
            val inputStream = context.contentResolver.openInputStream(
                srcUri!!
            ) ?: return
            val outputStream: OutputStream = FileOutputStream(dstFile)
            IOUtils.copyStream(inputStream, outputStream)
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun openFile(context: Context, url: File) {
        // Create URI
        val uri = Uri.fromFile(url)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            //            uri = FileProvider.getUriForFile(context,
//                    Constants.FILE_PROVIDER,
//                    file);
            //  intent.setDataAndType(uriLocal, "application/vnd.android.package-archive");
        }
        //        else {
//            intent.setDataAndType(uri,
//                    "application/vnd.android.package-archive");
//        }
        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        if (url.toString().contains(".doc")
            ||
            url.toString().contains(".docx")
        ) {
            // Word document
            intent.setDataAndType(uri, "application/msword")
        } else if (url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf")
        } else if (url.toString().contains(".ppt")
            ||
            url.toString().contains(".pptx")
        ) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint")
        } else if (url.toString().contains(".xls")
            ||
            url.toString().contains(".xlsx")
        ) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel")
        } else if (url.toString().contains(".zip")
            ||
            url.toString().contains(".rar")
        ) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav")
        } else if (url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf")
        } else if (url.toString().contains(".wav")
            ||
            url.toString().contains(".mp3")
        ) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav")
        } else if (url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif")
        } else if (url.toString().contains(".jpg")
            ||
            url.toString().contains(".jpeg")
            ||
            url.toString().contains(".png")
        ) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg")
        } else if (url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain")
        } else if (url.toString().contains(".3gp")
            ||
            url.toString().contains(".mpg")
            ||
            url.toString().contains(".mpeg")
            ||
            url.toString().contains(".mpe")
            ||
            url.toString().contains(".mp4")
            ||
            url.toString().contains(".avi")
        ) {
            // Video files
            intent.setDataAndType(uri, "video/*")
        } else {
            //if you want you can also define the intent type for any other file

            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "*/*")
        }
        context.startActivity(intent)
    }

    fun openFileUri(context: Context, uri: Uri?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setDataAndType(uri, "application/pdf")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    }

    fun getFileDataFromDrawable(context: Context, uri: Uri?): ByteArray {
        // Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        val byteArrayOutputStream = ByteArrayOutputStream()
        try {
            val iStream = context.contentResolver.openInputStream(uri!!)
            val bufferSize = 2048
            val buffer = ByteArray(bufferSize)

            // we need to know how may bytes were read to write them to the byteBuffer
            var len = 0
            if (iStream != null) {
                while (iStream.read(buffer).also { len = it } != -1) {
                    byteArrayOutputStream.write(buffer, 0, len)
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //  bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray()
    }

    class FileMetaData {
        var displayName: String? = null
        var size: Long = 0
        var mimeType: String? = null
        var path: String? = null
        override fun toString(): String {
            return "name : $displayName ; size : $size ; path : $path ; mime : $mimeType"
        }
    }
}