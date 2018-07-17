package it.corelab.studios.airbooks.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.`object`.Showcase

/**
 * Created by Roberto_Vecchio on 16/02/18.
 */
class InfiniteRotationAdapter(itemList: ArrayList<Showcase>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: List<Showcase> = listOf(itemList.last()) + itemList + listOf(itemList.first())

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.imageShowCase?.setBackgroundResource(list[position % list.size].drawable)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.home_showcase, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = list.size

    internal class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var imageShowCase: ImageView = view.findViewById(R.id.cardImage_showcase)

        init {
            ButterKnife.bind(this,view)
        }
    }
}