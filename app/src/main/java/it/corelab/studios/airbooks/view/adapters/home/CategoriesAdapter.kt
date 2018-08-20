package it.corelab.studios.airbooks.view.adapters.home

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.HOME.Genre
import it.corelab.studios.airbooks.view.anko.layout.adapters.home.Categories
import org.jetbrains.anko.AnkoContext

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
        return ItemViewHolder(Categories().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = items.size

    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.IMAGE_VIEW_CATEGORIES_ADAPTER)
        var textView: TextView = view.findViewById(R.id.TEXT_VIEW_CATEGORIES_ADAPTER)

        init {
            ButterKnife.bind(this,view)
        }
    }
}
