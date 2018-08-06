package it.corelab.studios.airbooks.view.adapters.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.HOME.Genre

/**
 * Created by Roberto_Vecchio on 19/02/18.
 */

class CategoriesAdapter(itemList: List<Genre>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: List<Genre> = itemList


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        val colors = intArrayOf(Color.parseColor("#" + item.firstColor), Color.parseColor("#" + item.secondColor))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors)
        gd.cornerRadius = 0f

        (holder as? ItemViewHolder)?.image?.background = gd
        (holder as? ItemViewHolder)?.textView?.text = item.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.categories_home, parent, false)
        return CategoriesAdapter.ItemViewHolder(view)
    }

    override fun getItemCount() = items.size

    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.cardImage_categories_home)
        var textView: TextView = view.findViewById(R.id.categories_home_id)

        init {
            ButterKnife.bind(this,view)
        }
    }
}
