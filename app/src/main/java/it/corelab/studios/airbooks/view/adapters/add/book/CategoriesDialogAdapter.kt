package it.corelab.studios.airbooks.view.adapters.add.book

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
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories.CategoriesItem
import org.jetbrains.anko.AnkoContext

class CategoriesDialogAdapter(itemList: List<Genre>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        return ItemViewHolder(CategoriesItem().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = items.size

    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.IMAGE_VIEW_CATEGORIES_DIALOG)
        var textView: TextView = view.findViewById(R.id.TEXT_VIEW_CATEGORIES_DIALOG)

        init {
            ButterKnife.bind(this,view)
        }
    }
}