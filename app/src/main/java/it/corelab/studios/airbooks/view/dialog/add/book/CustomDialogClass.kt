package it.corelab.studios.airbooks.view.dialog.add.book

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.API.remote.APIService
import it.corelab.studios.airbooks.model.API.remote.ApiUtils
import it.corelab.studios.airbooks.model.data.ADD.BOOK.PostAddBook
import it.corelab.studios.airbooks.model.data.ADD.BOOK.PostAddBookResponse
import it.corelab.studios.airbooks.view.activity.main.MainActivity
import it.corelab.studios.airbooks.view.activity.main.MainActivity.Companion.myFile
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter.Companion._descriptionText
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter.Companion._extension
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter.Companion._languageText
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter.Companion._titleText
import it.corelab.studios.airbooks.view.adapters.add.book.CategoriesDialogAdapter.Companion._genreId
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories.Categories.Companion.tagArrayList
import java.io.*
import java.util.*
import kotlin.concurrent.thread


class CustomDialogClass(private val activity: Activity, private val layout: Int, private val firstColor: String?)
    : Dialog(activity, R.style.CustomDialog),
        android.view.View.OnClickListener,
        CustomDialogController{

    private var mAPIService: APIService? = null
    lateinit var recyclerView: RecyclerView
    lateinit var token: String
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(layout)

        ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                2)

        mAPIService = ApiUtils.getAPIService()

        pDialog = SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE)

        val header = findViewById<LinearLayout>(R.id.header_custom_dialog_home)
        val cancel = findViewById<Button>(R.id.cancel_button_home)
        val publish = findViewById<Button>(R.id.publish_button_home)
        recyclerView = findViewById(R.id.recycler_custom_dialog)

        header.setBackgroundColor(Color.parseColor("#$firstColor"))

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)

        val addBookAdapter = AddBookAdapter(activity)
        recyclerView.adapter = addBookAdapter

        val sharedPreferences = activity.getSharedPreferences(activity.packageName, android.content.Context.MODE_PRIVATE)
        token = sharedPreferences.getString("token", "")!!

        cancel.setOnClickListener(this)
        publish.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.cancel_button_home -> dismiss()
            R.id.publish_button_home -> {

                thread {

                    val addBook = PostAddBook(getTitle(), getDescription(), getID(), tagArrayList, getLanguage(),
                            encodeTobase64(MainActivity._bitmapSelectedImage!!),
                            encoder(myFile!!),
                            getExtensions())

                    kotlin.run {
                        setPositiveButtonAction(addBook, "http://airbooks.altervista.org/API/v2/books/", Locale.getDefault().language, "android", token)
                    }
                }

                activity.runOnUiThread { showProgressDialog(pDialog)}

                /*Log.i("UPLOAD", "DESCRIPTION = ${_descriptionText?.text?.toString()}")
                Log.i("UPLOAD", "TITLE = ${_titleText?.text?.toString()} ID = $_genreId")
                Log.i("UPLOAD",  "TAG = $tagArrayList ")
                Log.i("UPLOAD", "GENRE ID = $_genreId")
                Log.i("UPLOAD", "LANGUAGE = ${AddBookAdapter._languageText?.text?.substring(_languageText?.text?.indexOf("(")!! + 1,_languageText?.text?.indexOf(")")!!)}")
                Log.i("UPLOAD", "FORMAT = ${_extension?.text} ")
                Log.i("UPLOAD", "=================================================================== ")
                Log.i("UPLOAD", "COVER IMAGE = ${encodeTobase64(MainActivity._bitmapSelectedImage!!)}")
                Log.i("UPLOAD", "=================================================================== ")
                Log.i("UPLOAD", "BOOK_DATA = ${encoder(myFile!!)}")*/

            }
        }
    }

    override fun setPositiveButtonAction(postAddBook: PostAddBook?, url: String?, lang: String?, os: String?, token: String?) {

        mAPIService!!.addBook(postAddBook, url, lang, os,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PostAddBookResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(postSignInResponse: PostAddBookResponse) {

            }

            override fun onError(e: Throwable) {
                activity.runOnUiThread {
                    pDialog.dismiss()
                    showErrorDialog()
                }
            }

            override fun onComplete() {
                activity.runOnUiThread {
                    pDialog.dismiss()
                    showSuccessDialog()
                    dismiss()
                }
            }

        })
    }

    private fun showProgressDialog(dialog: SweetAlertDialog) {

        dialog.progressHelper.barColor = Color.parseColor("#4990e2")
        dialog.titleText = "Loading Book"
        dialog.setCancelable(false)
        dialog.show()
    }

    fun showSuccessDialog() {
        SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                .show()
    }

    fun showErrorDialog() {
        SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .show()
    }

    private fun encodeTobase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()

        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun encoder(file: File): String{
        val inputStream = FileInputStream(file)//You can get an inputStream using any IO API
        val bytes: ByteArray
        val buffer = ByteArray(8192)
        var bytesRead: Int
        val output = ByteArrayOutputStream()
        try {
            while (inputStream.read(buffer) != -1) {
                bytesRead = inputStream.read(buffer)
                output.write(buffer, 0, bytesRead)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        bytes = output.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun fileToByte(file: File): ByteArray{
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(file)
        baos.close()
        oos.close()
        return baos.toByteArray()
    }

    private fun getTitle(): String?{
        return _titleText?.text?.toString()
    }

    private fun getDescription(): String?{
        return  _descriptionText?.text?.toString()
    }

    private fun getID(): Int{
        return _genreId!!
    }

    private fun getLanguage(): String?{
        return AddBookAdapter._languageText?.text?.substring(_languageText?.text?.indexOf("(")!! + 1,_languageText?.text?.indexOf(")")!!)
    }
    private fun getExtensions(): String{
        return _extension?.text.toString()
    }
}