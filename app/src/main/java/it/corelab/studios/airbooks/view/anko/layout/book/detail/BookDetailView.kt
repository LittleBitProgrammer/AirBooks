package it.corelab.studios.airbooks.view.anko.layout.book.detail

import android.graphics.Color
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.book.detail.DetailBook
import it.corelab.studios.airbooks.view.widget.JustifyTextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.recyclerview.v7.recyclerView

inline fun ViewManager.ratingBarView(theme: Int = R.style.RatingBarSmall, init: RatingBar.() -> Unit) = ankoView({ RatingBar(it) }, theme, init)
inline fun ViewManager.justifyView(theme: Int = 0, init: JustifyTextView.() -> Unit) = ankoView({ JustifyTextView(it,null) }, theme, init)

class BookDetailView: AnkoComponent<DetailBook> {

    lateinit var cardGenre: CardView
    lateinit var cardBook: CardView
    lateinit var coverBook: ImageView

    lateinit var linearGenre: LinearLayout

    lateinit var genreName: TextView
    lateinit var readersCount: TextView
    lateinit var loversCount: TextView
    lateinit var title: TextView
    lateinit var author: TextView
    lateinit var noTag: TextView

    lateinit var justified: JustifyTextView

    lateinit var ratingBar: RatingBar

    lateinit var tagRv: RecyclerView

    override fun createView(ui: AnkoContext<DetailBook>) = with(ui) {

        constraintLayout {
            id = Ids.CONSTRAINT_LAYOUT
            bottomPadding = dip(65)
            descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

            cardGenre = cardView {
                id = Ids.CARD_VIEW_CATEGORIES

                radius = dip(4).toFloat()
                setContentPadding(dip(2),dip(2),dip(2),dip(0))

                linearGenre = linearLayout {
                    id = Ids.LINEAR_GENRE

                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER

                    genreName = textView {
                        id = Ids.GENRE_TEXT

                        text = resources.getString(R.string.drawable_card_genre)
                        textColor = Color.parseColor("#F9F9F9")
                        textSize = 14F
                    }.lparams(width = wrapContent, height = wrapContent)

                    linearLayout {
                        id = Ids.LINEAR_ICON

                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        imageView {
                            id= Ids.READERS_IMAGE

                            Glide.with(ctx).load(R.drawable.ic_readers).into(this)
                        }.lparams(width = dip(12), height = dip(12)){
                            topMargin = dip(2)
                        }

                        readersCount = textView {
                            id = Ids.READERS_COUNT

                            text = resources.getString(R.string.numbers_readers_text_placeHolder)
                            textColor = Color.parseColor("#FFFFFF")
                            textSize = 12F
                        }.lparams(width = wrapContent, height = wrapContent)

                        imageView {
                            id = Ids.LOVERS_IMAGE

                            Glide.with(ctx).load(R.drawable.ic_bookmark).into(this)
                        }.lparams(width = dip(12), height = dip(12)){
                            topMargin = dip(2)
                        }

                        loversCount = textView {
                            id = Ids.LOVERS_COUNT

                            text = resources.getString(R.string.numbers_lovers_text_placeHolder)
                            textColor = Color.parseColor("#FFFFFF")
                            textSize = 12F
                        }.lparams(width = wrapContent, height = wrapContent)

                    }.lparams(width = matchParent, height = wrapContent, weight = 4F)

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(124), height = dip(65)){
                marginStart = dip(0)
                marginEnd = dip(0)
                bottomMargin = dip(220)
                bottomToBottom = Ids.CARD_VIEW_BOOK_IMAGE
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
            }

            cardBook = cardView {
                id = Ids.CARD_VIEW_BOOK_IMAGE

                radius = dip(4).toFloat()

                coverBook = imageView {
                    id = Ids.IMAGE_VIEW_BOOK_IMAGE

                    scaleType = ImageView.ScaleType.FIT_XY
                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(175), height = dip(261)){
                topMargin = dip(50)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToTop = PARENT_ID
            }

            linearLayout {
                id = Ids.LINEAR_BOTTOM

                gravity = Gravity.CENTER_HORIZONTAL
                orientation = LinearLayout.VERTICAL

                title = textView {
                    id = Ids.TITLE

                    maxLines = 2
                    ellipsize = TextUtils.TruncateAt.END
                    text = resources.getString(R.string.title_book_detail)
                    textColor = Color.parseColor("#6C7A8C")
                    textSize = 22F
                    this.setTypeface(null,Typeface.BOLD)
                }.lparams(width = wrapContent, height = wrapContent){
                    marginStart = dip(20)
                    marginEnd = dip(20)
                }

                author = textView {
                    id = Ids.AUTHOR

                    maxLines = 2
                    ellipsize = TextUtils.TruncateAt.END
                    typeface = Typeface.createFromAsset(context.assets,"didot.ttf")
                    text = resources.getString(R.string.author_book_detail)
                    textColor = Color.parseColor("#6C7A8C")
                    textSize = 18F
                }.lparams(width = wrapContent, height = wrapContent){
                    marginStart = dip(12)
                    marginEnd = dip(12)
                    topMargin = dip(2)
                }

                ratingBar = ratingBarView {
                    id = Ids.RATING_BAR

                    this.setIsIndicator(true)
                    numStars = 5
                    scaleX = 0.5F
                    scaleY = 0.5F
                    stepSize = 0.5F
                }.lparams(width = wrapContent, height = wrapContent){
                    topMargin = dip(6)
                }

                justified = justifyView {
                    id = Ids.JUSTIFY_TEXT

                    text = resources.getString(R.string.placeholder_text_showMore_alert)
                    textSize = 16F
                }.lparams(width = matchParent, height = wrapContent){
                    marginStart = dip(10)
                    topMargin = dip(4)
                }

                textView {
                    id = Ids.TAG_LABEL

                    text = "Tag:"
                    textColor = Color.parseColor("#6C7A8C")
                    textSize = 15F
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(8)
                    marginStart = dip(10)
                }

                tagRv = recyclerView {
                    id = Ids.TAG_RV

                }.lparams(width = matchParent, height = wrapContent){
                    marginStart = dip(10)
                }

                noTag = textView {
                    id = Ids.NO_TAG

                    text = "Questo libro non ha nessun tag"
                    textSize = 16F
                    visibility = View.INVISIBLE
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(4)
                    marginStart = dip(10)
                }

                textView {
                    id = Ids.COPIRIGHT

                    text = "© Tutti i diritti riservati all'autore di questo libro."
                    textSize = 13F
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(2)
                    marginStart = dip(10)
                }

            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(10)
                endToEnd = PARENT_ID
                startToStart = PARENT_ID
                topToBottom = Ids.CARD_VIEW_BOOK_IMAGE
            }
        }
    }

    private object Ids{
        const val CONSTRAINT_LAYOUT = R.id.CONSTRAINT_LAYOUT_BOOK_DETAIL
        const val CARD_VIEW_CATEGORIES = R.id.CARD_VIEW_CATEGORIES_BOOK_DETAIL
        const val CARD_VIEW_BOOK_IMAGE = R.id.CARD_VIEW_BOOK_IMAGE
        const val IMAGE_VIEW_BOOK_IMAGE = R.id.IMAGE_VIEW_BOOK_IMAGE
        const val LINEAR_GENRE = R.id.LINEAR_LAYOUT_GENRE_BOOK_DETAIL
        const val GENRE_TEXT = R.id.GENRE_TYPE_BOOK_DETAIL
        const val LINEAR_ICON = R.id.LINEAR_ICON_GENRE_BOOK_DETAIL
        const val READERS_IMAGE = R.id.READERS_IMAGE_BOOK_DETAIL
        const val READERS_COUNT = R.id.READERS_COUNT_BOOK_DETAIL
        const val LOVERS_IMAGE = R.id.LOVERS_IMAGE_BOOK_DETAIL
        const val LOVERS_COUNT = R.id.LOVERS_COUNT_BOOK_DETAIL
        const val LINEAR_BOTTOM = R.id.LINEAR_LAYOUT_BOTTOM_BOOK_DETAIL
        const val TITLE = R.id.TITLE_BOOK_DETAIL
        const val AUTHOR = R.id.AUTHOR_BOOK_DETAIL
        const val RATING_BAR = R.id.RATING_BAR_BOOK_DETAIL
        const val JUSTIFY_TEXT = R.id.JUSTIFY_TEXT_VIEW_BOOK_DETAIL
        const val TAG_LABEL = R.id.TAG_LABEL_BOOK_DETAIL
        const val TAG_RV = R.id.TAG_RV_BOOK_DETAIL
        const val NO_TAG = R.id.NO_TAG_LABEL_BOOK_DETAIL
        const val COPIRIGHT = R.id.COPIRIGHT_LABEL
    }
}