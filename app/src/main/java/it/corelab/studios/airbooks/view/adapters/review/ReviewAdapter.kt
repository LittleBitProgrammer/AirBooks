package it.corelab.studios.airbooks.view.adapters.review

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.REVIEW.Item
import it.corelab.studios.airbooks.view.widget.ExpandableTextView

class ReviewAdapter(books: List<Item>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val reviewItems: List<Item> = books

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val review = reviewItems[position]

        Glide.with(context).load(review.authorProfilePicture).into((holder as? ItemViewHolder)?.roundedImage!!)
        (holder as? ItemViewHolder)?.nameSurname?.text = "${review.authorFirstName} ${review.authorLastName}"

        (holder as? ItemViewHolder)?.review?.text = review.description

        (holder as? ItemViewHolder)?.review?.setAnimationDuration(450L)
        (holder as? ItemViewHolder)?.review?.expandInterpolator = OvershootInterpolator(1.0f)
        (holder as? ItemViewHolder)?.review?.collapseInterpolator = LinearOutSlowInInterpolator()

        if ((holder as? ItemViewHolder)?.review?.text?.length!! < 200){
            (holder as? ItemViewHolder)?.review?.setOnClickListener {
                if ((holder as? ItemViewHolder)?.review?.isExpanded!!)
                {
                    (holder as? ItemViewHolder)?.review?.collapse()
                }
                else
                {
                    (holder as? ItemViewHolder)?.review?.expand()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_listview_item_all_reviews, parent, false)
        return ItemViewHolder(view)
    }



    override fun getItemCount()= reviewItems.size


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var roundedImage: ImageView = view.findViewById(R.id.rounded_review)
        var nameSurname: TextView = view.findViewById(R.id.name_surname_review)
        var review: ExpandableTextView = view.findViewById(R.id.expandable_text)


        init {
            ButterKnife.bind(this,view)
        }
    }
}
