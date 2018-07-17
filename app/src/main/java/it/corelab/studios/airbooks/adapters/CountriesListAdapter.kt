package it.corelab.studios.airbooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import it.corelab.studios.airbooks.CountryDialog

import java.util.Locale
import java.util.Objects

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.login.fragment.SignUpFragment


class CountriesListAdapter(contexts: Context, private val values: Array<String>) : ArrayAdapter<String>(contexts, R.layout.country_list_item, values) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val rowView = inflater.inflate(R.layout.country_list_item, parent, false)
        val textView = rowView.findViewById<TextView>(R.id.txtViewCountryName)

        val g = values[position].split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        textView.text = getCountryZipCode(g[1]).trim { it <= ' ' }

        rowView.setOnClickListener {
            SignUpFragment.nation.setText(textView.text.toString())
            Objects.requireNonNull<CountryDialog>(SignUpFragment.countryDialog).dismiss()
        }

        return rowView
    }

    private fun getCountryZipCode(ssid: String): String {
        val loc = Locale("", ssid)

        return loc.displayCountry.trim { it <= ' ' }
    }
}