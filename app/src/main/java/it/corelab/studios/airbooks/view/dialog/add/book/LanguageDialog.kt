package it.corelab.studios.airbooks.view.dialog.add.book

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Window
import android.widget.LinearLayout
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.add.book.LanguageAdapter
import java.util.*

class LanguageDialog(private val activity: Activity,private val firstColor: String?) : Dialog(activity,R.style.CustomDialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.setContentView(R.layout.language_dialog)

        val header = findViewById<LinearLayout>(R.id.header_custom_dialog_home)
        header.setBackgroundColor(Color.parseColor("#$firstColor"))

        val hashSet = LinkedHashSet<String>()
        val locales = Locale.getAvailableLocales()
        for( l:Locale in locales)
        {
            hashSet.add(l.displayLanguage)
        }

        val recyclerView: RecyclerView = findViewById(R.id.list_dynamics)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val countriesListAdapter = LanguageAdapter(hashSet,this)
        recyclerView.adapter = countriesListAdapter
    }
}