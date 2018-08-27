package it.corelab.studios.airbooks.view.adapters.add.book

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R

class AddBookTagAdapter(tags: ArrayList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val tagItems: ArrayList<String>? = tags

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderContainer2 = (holder as? ItemViewHolder2)

        if (tagItems != null) {
            val tag = tagItems[position]

            holderContainer2?.tagText?.text = tag

            holderContainer2?.itemView?.setOnClickListener {
                deleteItem(position, holderContainer2)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adpter_tag_add_book, parent, false)

        return ItemViewHolder2(view)
    }

    override fun getItemCount(): Int = tagItems?.size ?: 0

    internal class ItemViewHolder2(view: View): RecyclerView.ViewHolder(view){

        val tagText: TextView = view.findViewById(R.id.tag_text_id)
        val dismiss: ImageButton = view.findViewById(R.id.dismiss_tag_adapter)

        init {
            ButterKnife.bind(this,view)
        }
    }

    private fun deleteItem(position: Int, holder: RecyclerView.ViewHolder) {
        tagItems?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, tagItems?.size!!)
        holder.itemView.visibility = View.GONE
    }
}