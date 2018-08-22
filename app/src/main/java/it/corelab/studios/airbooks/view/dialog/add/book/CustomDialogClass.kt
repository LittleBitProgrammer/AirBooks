package it.corelab.studios.airbooks.view.dialog.add.book

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter


class CustomDialogClass(private val activity: Activity, private val layout: Int, private val firstColor: String?) : Dialog(activity, R.style.CustomDialog), android.view.View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(layout)

        val header = findViewById<LinearLayout>(R.id.header_custom_dialog_home)
        val cancel = findViewById<Button>(R.id.cancel_button_home)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_custom_dialog)

        header.setBackgroundColor(Color.parseColor("#$firstColor"))

        recyclerView.setItemViewCacheSize(10)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecorator)

        val addBookAdapter = AddBookAdapter(activity)
        recyclerView.adapter = addBookAdapter


        cancel.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.cancel_button_home -> dismiss()
        }
    }
}