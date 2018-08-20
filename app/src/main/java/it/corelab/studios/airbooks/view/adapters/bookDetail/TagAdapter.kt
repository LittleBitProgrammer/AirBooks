package it.corelab.studios.airbooks.view.adapters.bookDetail

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R

class TagAdapter(tags: ArrayList<String>?, color: String): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val tagItems: ArrayList<String>? = tags
    private val colorBg: String = color

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderContainer = (holder as? ItemViewHolder)

        if (tagItems != null) {
            val tag = tagItems[position]

            holderContainer?.tagText?.text = tagItems[position]
            holderContainer?.tagText?.background?.setColorFilter(Color.parseColor("#$colorBg"), PorterDuff.Mode.SRC_ATOP)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adapter_tag,parent,false)

        return TagAdapter.ItemViewHolder(view)
    }

    override fun getItemCount(): Int = tagItems?.size ?: 0

    internal class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tagText: TextView = view.findViewById(R.id.tag_text_id)

        init {

            ButterKnife.bind(this,view)
        }
    }
}