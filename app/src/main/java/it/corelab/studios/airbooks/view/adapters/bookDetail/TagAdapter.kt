package it.corelab.studios.airbooks.view.adapters.bookDetail

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import butterknife.ButterKnife
import it.corelab.studios.airbooks.R

class TagAdapter(tags: ArrayList<String>?, color: String, private val isFromAddBook: Boolean): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val tagItems: ArrayList<String>? = tags
    private val colorBg: String = color

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderContainer = (holder as? ItemViewHolder)
        val holderContainer2 = (holder as? ItemViewHolder2)

        if (tagItems != null) {
            val tag = tagItems[position]

            if (isFromAddBook){

                holderContainer2?.tagText?.text = tag

                holderContainer2?.itemView?.setOnClickListener {
                    deleteItem(position, holderContainer2)
                    Log.e("DELETEITEM", "ELIMINATO")
                }
            }else{
                holderContainer?.tagText?.text = tag
                holderContainer?.tagText?.background?.setColorFilter(Color.parseColor("#$colorBg"), PorterDuff.Mode.SRC_ATOP)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = if (isFromAddBook) {
            LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.adpter_tag_add_book, parent, false)
        }else{
            LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.adapter_tag, parent, false)
        }

        return if (isFromAddBook){
            ItemViewHolder2(view)
        }else{
            ItemViewHolder(view)
        }
    }

    override fun getItemCount(): Int = tagItems?.size ?: 0

    internal class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tagText: TextView = view.findViewById(R.id.tag_text_id)

        init {

            ButterKnife.bind(this,view)
        }
    }

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