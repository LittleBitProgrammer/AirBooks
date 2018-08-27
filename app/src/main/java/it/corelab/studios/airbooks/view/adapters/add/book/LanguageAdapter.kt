package it.corelab.studios.airbooks.view.adapters.add.book

import android.app.Dialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.add.book.AddBookAdapter.Companion._languageText
import java.util.LinkedHashSet

class LanguageAdapter(values: LinkedHashSet<String>,  private val dialog: Dialog) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: LinkedHashSet<String> = values

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.country_list_item, parent, false)

        return LanguageAdapter.ItemViewHolder(view,dialog)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = items.elementAt(position)

        (holder as? ItemViewHolder)?.text?.text = item
    }

    internal class ItemViewHolder (view: View, dialog: Dialog) : RecyclerView.ViewHolder(view) {

        var text: TextView = view.findViewById(R.id.txtViewCountryName)

        init {
            ButterKnife.bind(this,view)
            this.itemView.setOnClickListener {
                _languageText?.text = text.text
                dialog.dismiss()
            }
        }

    }

}