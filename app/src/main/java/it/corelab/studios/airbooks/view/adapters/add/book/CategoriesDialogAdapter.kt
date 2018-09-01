package it.corelab.studios.airbooks.view.adapters.add.book

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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

    var mRecyclerView: RecyclerView? = null

    var selectdPos = RecyclerView.NO_POSITION

    var layoutManager: GridLayoutManager? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        mRecyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        val colors = intArrayOf(Color.parseColor("#" + item.firstColor), Color.parseColor("#" + item.secondColor))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors)
        gd.cornerRadius = 0f

        if (selectdPos == position){ (holder as? ItemViewHolder)?.image?.background = gd
            _genreId = item.id
        } else
            (holder as? ItemViewHolder)?.image?.setBackgroundColor( Color.parseColor("#CCCCCC"))
        (holder as? ItemViewHolder)?.textView?.text = item.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(CategoriesItem().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
         override fun onClick(p0: View?) {
             if (adapterPosition == RecyclerView.NO_POSITION) return

             // Updating old as well as new positions
             notifyItemChanged(selectdPos)
             layoutManager = ((mRecyclerView?.layoutManager) as? GridLayoutManager)

             if (adapterPosition < items.size-2){
             layoutManager?.scrollToPositionWithOffset(adapterPosition,300)
                 Log.i("GRANDEZZA", "$adapterPosition < ${items.size-2}")
             }

             selectdPos = adapterPosition
             notifyItemChanged(selectdPos)

         }

         var image: ImageView = view.findViewById(R.id.IMAGE_VIEW_CATEGORIES_DIALOG)
        var textView: TextView = view.findViewById(R.id.TEXT_VIEW_CATEGORIES_DIALOG)

        init {
            ButterKnife.bind(this,view)
            itemView.setOnClickListener(this)
        }

    }
    companion object {
        var _genreId: Int? = null
    }
}