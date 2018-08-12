package it.corelab.studios.airbooks.view.fragment.book.detail

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import it.corelab.studios.airbooks.view.widget.CustomNested
import it.corelab.studios.airbooks.view.widget.DiagonalView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import it.corelab.studios.airbooks.model.Gesture.GestureHelper
import it.corelab.studios.airbooks.view.adapters.bookDetail.TagAdapter
import mehdi.sakout.fancybuttons.FancyButton
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.textColor

class DetailBook: Fragment(), OnReselectedDelegate {

    private var diagonalView: DiagonalView? = null
    private var customNestedHome: CustomNested? = null
    private var customNestedExplore: CustomNested? = null
    private var button: FancyButton? = null
    private var buttonPreference: FancyButton? = null
    private var linearBottom: LinearLayout? = null
    private var firstColor:String? = null
    private var secondColor:String? = null
    private var coverUrl:String? = null
    private var bookTitle:String? = null
    private var bookAuthor:String? = null
    private var bookGenre: String? = null
    private var bookReaders: Int? = null
    private var bookLovers: Int? = null
    private var counterReview: Int? = null
    private var bookDescription: String? = null
    private var tagsList: ArrayList<String>? = null
    private lateinit var coverImage: ImageView
    private lateinit var cardBook: CardView
    private lateinit var cardGenre: CardView
    private lateinit var linearCard: LinearLayout
    private lateinit var author: TextView
    private lateinit var title: TextView
    private lateinit var genre: TextView
    private lateinit var readers: TextView
    private lateinit var lovers: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var descriprionLabel: TextView
    private lateinit var tags: RecyclerView
    private lateinit var noTag: TextView
    internal var isSwipedCenter = true
    private var isComingFromHome: Boolean = false
    private var isComingFromExplore: Boolean = false
    private var isSaved: Boolean = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.activity_book_detail,null).apply {

                customNestedHome = activity?.findViewById(R.id.nested_home)
                customNestedExplore = activity?.findViewById(R.id.nested_explore)
                diagonalView = activity?.findViewById(R.id.diagonal_main)
                button = activity?.findViewById(R.id.color_button_read_now)
                buttonPreference = activity?.findViewById(R.id.preferred_button)
                linearBottom = activity?.findViewById(R.id.linearMain)
                coverImage = findViewById(R.id.image_bestweek_shared)
                cardBook = findViewById(R.id.cardView_shared)
                cardGenre = findViewById(R.id.cardView_categories_shared)
                linearCard = findViewById(R.id.linear_genre)
                title = findViewById(R.id.title_bookDetail)
                author = findViewById(R.id.author_bookDetail)
                genre = findViewById(R.id.text_genre_label)
                readers = findViewById(R.id.numb_readers_book_detail)
                lovers = findViewById(R.id.numb_lovers_book_detail)
                ratingBar = findViewById(R.id.ratingBar_book_detail)
                descriprionLabel = findViewById(R.id.description_book_detail)
                tags = findViewById(R.id.tag_book_detail)
                noTag = findViewById(R.id.no_tag_label)

                firstColor = arguments?.getString("firstColor")
                secondColor = arguments?.getString("secondColor")
                coverUrl = arguments?.getString("coverUrl")
                bookTitle = arguments?.getString("bookTitle")
                bookAuthor = arguments?.getString("bookAuthor")
                bookGenre = arguments?.getString("bookGenre")
                bookReaders = arguments?.getInt("bookReaders")
                bookLovers = arguments?.getInt("bookLovers")
                bookDescription = arguments?.getString("bookDescription")
                tagsList = arguments?.getStringArrayList("tags")
                isComingFromHome = arguments?.getBoolean("comingHome")!!
                isComingFromExplore = arguments?.getBoolean("comingExplore")!!
                counterReview = arguments?.getInt("countNumb")
                isSaved = arguments?.getBoolean("isSaved")!!

                onUiThread {

                    if (isComingFromHome) {
                        customNestedHome?.animateToOriginal(diagonalView!!)
                        customNestedHome?.scrollTo(0, 0)
                    } else if (isComingFromExplore) {
                        customNestedExplore?.animateToOriginal(diagonalView!!)
                        customNestedExplore?.scrollTo(0, 0)
                    }

                    Glide.with(ctx).load(coverUrl).into(coverImage)
                    title.text = bookTitle
                    author.text = bookAuthor
                    genre.text = bookGenre
                    readers.text = "$bookReaders"
                    lovers.text = "$bookLovers"
                    descriprionLabel.text = bookDescription

                    val stars: LayerDrawable = (ratingBar.progressDrawable as LayerDrawable)
                    stars.getDrawable(2).setColorFilter(Color.parseColor("#$firstColor"), PorterDuff.Mode.SRC_ATOP)

                    val sharedPreferences = activity?.getSharedPreferences(activity?.packageName, Context.MODE_PRIVATE)
                    sharedPreferences?.edit()?.putString("firstColor", arguments!!.getString("firstColor"))?.apply()
                    sharedPreferences?.edit()?.putString("secondColor", arguments!!.getString("secondColor"))?.apply()

                    val colors = intArrayOf(Color.parseColor("#${arguments?.getString("firstColor")}"), Color.parseColor("#${arguments?.getString("secondColor")}"))

                    val gd = GradientDrawable(
                            GradientDrawable.Orientation.LEFT_RIGHT,
                            colors)
                    gd.cornerRadius = 0f

                    val gd2 = GradientDrawable(
                            GradientDrawable.Orientation.LEFT_RIGHT,
                            colors)
                    gd.cornerRadius = 0f

                    diagonalView?.background = gd
                    linearCard.background = gd2
                    button?.backgroundColor = Color.parseColor("#$firstColor")
                    buttonPreference!!.backgroundColor = Color.parseColor("#$secondColor")

                    if (!isSaved) {
                        buttonPreference?.setIconResource(R.drawable.ic_bookmark_border)
                        buttonPreference?.setOnClickListener {
                            buttonPreference?.setIconResource(R.drawable.ic_bookmark)
                            isSaved = true
                        }
                    } else {
                        buttonPreference?.setIconResource(R.drawable.ic_bookmark)
                        buttonPreference?.setOnClickListener {
                            buttonPreference?.setIconResource(R.drawable.ic_bookmark_border)
                            isSaved = false
                        }
                    }

                    cardBook.setOnTouchListener(object : GestureHelper(ctx) {
                        override fun onClick() {
                            if (isSwipedCenter) {
                                tapDownAnimation()
                            } else if (!isSwipedCenter) {
                                tapTopAnimation()
                            }

                        }
                    })

                    if (tagsList == null) {
                        noTag.visibility = View.VISIBLE
                        noTag.textColor = Color.parseColor("#$firstColor")
                    }

                    val layoutManager = FlexboxLayoutManager(ctx)
                    layoutManager.flexDirection = FlexDirection.ROW
                    layoutManager.justifyContent = JustifyContent.FLEX_START
                    tags.setItemViewCacheSize(20)
                    tags.layoutManager = layoutManager
                    tags.setHasFixedSize(true)

                    val tagAdapter = TagAdapter(tagsList, firstColor!!)
                    tags.adapter = tagAdapter

                }

            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isSectionVisible()) setupActionBar()
        if (linearBottom?.visibility == View.INVISIBLE){
            linearBottom?.visibility = View.VISIBLE
            linearBottom?.isEnabled = true
        }
    }

    fun tapDownAnimation() {
        Log.d("swipe bookDetail", "click to bottom")
        cardBook.animate().translationY(20f)
        cardGenre.animate().translationY(-30f)
        this.isSwipedCenter = false
    }

    fun tapTopAnimation() {
        Log.d("swipe bookDetail", "click to center")
        cardBook.animate().translationY(0f)
        cardGenre.animate().translationY(0f)
        this.isSwipedCenter = true
    }

    private fun setupActionBar() = setupActionBar("Book Detail", 4, counterReview.toString())

    override fun onReselected() = setupActionBar()
}