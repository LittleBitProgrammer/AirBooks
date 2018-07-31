package it.corelab.studios.airbooks.adapters.HOME

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.ButterKnife

import com.squareup.picasso.Picasso

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.data.model.HOME.ItemReading

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

class SnapContinueReadAdapter( books: List<ItemReading>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val booksItems: List<ItemReading> = books

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val book = booksItems[position]

        Picasso.get().load(book.coverUrl).into((holder as? ItemViewHolder)?.image)
        Log.i("PIPINO", "\$booksItems.size()")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.rv_continue_read, parent, false)
        return SnapContinueReadAdapter.ItemViewHolder(view)
    }



    override fun getItemCount()= booksItems.size


    internal class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView = view.findViewById(R.id.cover_image)


        init {
            ButterKnife.bind(this,view)
        }
    }
}
