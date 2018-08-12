package it.corelab.studios.airbooks.view.adapters.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.ButterKnife
import com.bumptech.glide.Glide

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.HOME.ItemReading
import it.corelab.studios.airbooks.view.anko.layout.adapters.home.ContinueRead
import org.jetbrains.anko.AnkoContext

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

class ContinueReadAdapter(books: List<ItemReading>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val booksItems: List<ItemReading> = books

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val book = booksItems[position]

        Glide.with(context).load(book.coverUrl).into((holder as? ItemViewHolder)?.image!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(ContinueRead().createView(AnkoContext.create(parent.context, parent)))
    }



    override fun getItemCount()= booksItems.size


    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView = view.findViewById(R.id.IMAGE_VIEW_CONTINUE_READ_ADPTER)


        init {
            ButterKnife.bind(this,view)
        }
    }
}
