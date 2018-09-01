package it.corelab.studios.airbooks.view.adapters.home

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.HOME.ItemBest
import org.jetbrains.anko.runOnUiThread
import kotlin.concurrent.thread

/**
 * Created by Roberto_Vecchio on 21/02/18.
 */

class BestOfWeekAdapter(books: List<ItemBest>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bookItems: List<ItemBest> = books

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val book = bookItems[position]

        thread(start = true) {
            val coverUrl = book.coverUrl
            val title = book.title
            val author = (book.authorFirstName + book.authorLastName)
            val readersNumber = book.readings.toString()
            val firstColor = Color.parseColor("#${book.genre.firstColor}")
            val secondColor = Color.parseColor("#${book.genre.secondColor}")
            val average = book.averageRating.toString()

            val holderContainer = (holder as? ItemViewHolder)

            val colors = intArrayOf(Color.parseColor("#" + book.genre.firstColor), Color.parseColor("#" + book.genre.secondColor))
            val gd = GradientDrawable(
                    GradientDrawable.Orientation.TL_BR,
                    colors)
            gd.cornerRadius = 0f

            context.runOnUiThread {
                Glide.with(context).load(coverUrl).into(holderContainer?.coverImage!!)
                holderContainer.bookTitle.text = title
                holderContainer.bookAuthor.text = author
                holderContainer.numbReaders.text = readersNumber
                holderContainer.colorGenre.background = gd
                holderContainer.readersImage.setColorFilter(firstColor)
                holderContainer.numbReaders.setTextColor(firstColor)
                holderContainer.bookRatingImage.setColorFilter(secondColor)
                holderContainer.bookAverageRating.text = average
                holderContainer.bookAverageRating.setTextColor(secondColor)
            }

        }

        (holder as? ItemViewHolder)?.itemView?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_detailBook, Bundle().apply {
                    thread(start = true) {
                        putString("firstColor", book.genre.firstColor)
                        putString("secondColor", book.genre.secondColor)
                        putString("coverUrl", book.coverUrl)
                        putString("bookTitle", book.title)
                        putString("bookAuthor", book.authorFirstName + " " + book.authorLastName)
                        putString("bookGenre", book.genre.name)
                        putString("bookDescription", book.description)
                        putString("bookId", book.id)
                        putInt("bookReaders", book.readings)
                        putInt("bookLovers", book.lovers)
                        putInt("countNumb", book.reviewsCount)
                        putDouble("star",book.averageRating)
                        putBoolean("comingHome", true)
                        putBoolean("isSaved", book.isSaved)
                        putStringArrayList("tags",book.tags)
                    }
                })
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.home_best_of_week, parent, false)
        return BestOfWeekAdapter.ItemViewHolder(view)
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
