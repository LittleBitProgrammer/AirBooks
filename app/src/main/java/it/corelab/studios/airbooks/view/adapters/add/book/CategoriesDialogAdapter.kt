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
import it.corelab.studios.airbooks.view.adapters.home.CategoriesAdapter
import it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories.CategoriesItem
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.backgroundColor
import android.support.v7.widget.CardView




class CategoriesDialogAdapter(itemList: List<Genre>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: List<Genre> = itemList

    var mRecyclerView: RecyclerView? = null

    var cardViewList: ArrayList<ImageView> = ArrayList()
    var selectdPos = RecyclerView.NO_POSITION

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        mRecyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        (holder as? ItemViewHolder)?.itemView?.setSelected(selectdPos == position)
        if (!cardViewList.contains((holder as? ItemViewHolder)?.image)){
        cardViewList.add((holder as? ItemViewHolder)?.image!!)
            }

        val colors = intArrayOf(Color.parseColor("#" + item.firstColor), Color.parseColor("#" + item.secondColor))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors)
        gd.cornerRadius = 0f

        (holder as? ItemViewHolder)?.image?.background = gd
        (holder as? ItemViewHolder)?.textView?.text = item.name

        (holder as? ItemViewHolder)?.itemView?.setOnClickListener {
            for (tempItemView in cardViewList) {
                tempItemView.backgroundColor = Color.parseColor("#CCCCCC")

            }
            (holder as? ItemViewHolder)?.image?.background = gd
            //mRecyclerView?.smoothScrollToPosition(position)
        }
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