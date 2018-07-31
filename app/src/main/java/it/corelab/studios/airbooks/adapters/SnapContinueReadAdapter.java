package it.corelab.studios.airbooks.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.`object`.Book

/**
 * Created by Roberto_Vecchio on 18/02/18.
 */

class SnapContinueReadAdapter(context: Context, private val books: ArrayList<Book>) : RecyclerView.Adapter<SnapContinueReadAdapter.ReyclerViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        this.layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapContinueReadAdapter.ReyclerViewHolder {
        val book = layoutInflater.inflate(R.layout.rv_continue_read, parent, false)

        return SnapContinueReadAdapter.ReyclerViewHolder(book)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SnapContinueReadAdapter.ReyclerViewHolder, position: Int) {
        val book = books[position]

        Picasso.get().load(book.coverUrl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return books.size
    }

    internal inner class ReyclerViewHolder private constructor(v: View) : RecyclerView.ViewHolder(v) {
        private val image: ImageView


        init {

            image = v.findViewById(R.id.review_cover_image)
        }
    }
}
