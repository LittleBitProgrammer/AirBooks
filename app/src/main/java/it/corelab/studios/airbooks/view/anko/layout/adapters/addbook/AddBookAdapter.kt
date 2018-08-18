package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R

class AddBookAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.home_best_of_week, parent, false)
        return ItemViewHolder(view)
    }



    override fun getItemCount() = 0


    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {

        init {

            ButterKnife.bind(this,view)
        }
    }
}