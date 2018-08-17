package it.corelab.studios.airbooks.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.REVIEW.Item

class ReviewAdapter(books: List<Item>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val reviewItems: List<Item> = books

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val review = reviewItems[position]

        Glide.with(context).load(review.authorProfilePicture).into((holder as? ItemViewHolder)?.roundedImage!!)
        (holder as? ItemViewHolder)?.nameSurname?.text = "${review.authorFirstName} ${review.authorLastName}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_listview_item_all_reviews, parent, false)
        return ReviewAdapter.ItemViewHolder(view)
    }



    override fun getItemCount()= reviewItems.size


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var roundedImage: ImageView = view.findViewById(R.id.rounded_review)
        var nameSurname: TextView = view.findViewById(R.id.name_surname_review)


        init {
            ButterKnife.bind(this,view)
        }
    }
}
