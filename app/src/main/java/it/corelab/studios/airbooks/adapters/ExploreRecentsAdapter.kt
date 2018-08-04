package it.corelab.studios.airbooks.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import butterknife.ButterKnife
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.adapters.home.BestOfWeekAdapter
import it.corelab.studios.airbooks.data.model.EXPLORE.Item

class ExploreRecentsAdapter(books: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bookItems: List<Item> = books


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = bookItems[position]
        val holderContainer = (holder as? ItemViewHolder)

        val colors = intArrayOf(Color.parseColor("#" + book.genre.firstColor), Color.parseColor("#" + book.genre.secondColor))
        val gd = GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                colors)
        gd.cornerRadius = 0f

        Picasso.get().load(book.coverUrl).into(holderContainer?.coverImage)

        holderContainer?.bookTitle?.text = book.title
        holderContainer?.bookAuthor?.text = (book.authorFirstName + book.authorLastName)
        holderContainer?.numbReaders?.text = "${book.readings}"
        holderContainer?.colorGenre?.background = gd
        holderContainer?.readersImage?.setColorFilter(Color.parseColor("#${book.genre.firstColor}"))
        holderContainer?.numbReaders?.setTextColor(Color.parseColor("#${book.genre.firstColor}"))
        holderContainer?.bookRatingImage?.setColorFilter(Color.parseColor("#${book.genre.secondColor}"))
        holderContainer?.bookAverageRating?.text = "${book.averageRating}"
        holderContainer?.bookAverageRating?.setTextColor(Color.parseColor("#${book.genre.secondColor}"))

        holderContainer?.itemView?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailBook,null)
        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.home_best_of_week, parent, false)
        return ExploreRecentsAdapter.ItemViewHolder(view)
    }



    override fun getItemCount() = bookItems.size


    internal class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val coverImage: ImageView = view.findViewById(R.id.image_bestweek)
        val bookTitle: TextView = view.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = view.findViewById(R.id.author_bestweek)
        val colorGenre: ImageView = view.findViewById(R.id.color_diagonal)
        val readersImage: ImageView = view.findViewById(R.id.readers_best_week)
        val numbReaders: TextView = view.findViewById(R.id.numb_readers_best_week)
        val bookRatingImage: ImageView = view.findViewById(R.id.starImage)
        val bookAverageRating: TextView = view.findViewById(R.id.bookAverageRating)

        init {

            ButterKnife.bind(this,view)
        }
    }
}