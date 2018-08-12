package it.corelab.studios.airbooks.view.anko.layout.home

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.TextView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.home.HomeFragment
import it.corelab.studios.airbooks.view.widget.InfiniteRotationView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.recyclerview.v7.recyclerView

inline fun ViewManager.infiniteRotation(theme: Int = 0, init: InfiniteRotationView.() -> Unit) = ankoView({ InfiniteRotationView(it,null) }, theme, init)
class Home: AnkoComponent<HomeFragment> {

    lateinit var continueReadLabel: TextView
    lateinit var bestBookLabel: TextView
    lateinit var categoriesLabel: TextView

    lateinit var infiniteRotationView: InfiniteRotationView
    lateinit var continueReading: RecyclerView
    lateinit var bestBookRV: RecyclerView
    lateinit var categoriesRV: RecyclerView

    override fun createView(ui: AnkoContext<HomeFragment>) = with(ui) {

        linearLayout {
            id = Ids.LINEAR_LAYOUT

            orientation = LinearLayout.VERTICAL
            bottomPadding = dip(15)
            descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

            infiniteRotationView = infiniteRotation {
                id = Ids.INFINITE_ROTATION

            }.lparams(width = matchParent, height = dip(171))

            continueReadLabel = textView {
                id = Ids.CONTINUE_READ

                text = resources.getString(R.string.continue_read)
                textColor = Color.parseColor("#FFFFFF")
                textSize = 14F

            }.lparams(width = wrapContent, height = wrapContent){
                topMargin = dip(16)
                marginStart = dip(10)
            }

            continueReading = recyclerView {
                id = Ids.CONTINUE_READING_RV

            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(6)
                marginStart = dip(10)
            }

            bestBookLabel = textView {
                id= Ids.BEST_BOOK_LABEL

                text = resources.getString(R.string.best_weeek)
                textColor = Color.parseColor("#6E7071")
                textSize = 14F

            }.lparams(width = wrapContent, height = wrapContent){
                marginStart = dip(10)
            }

            bestBookRV = recyclerView {
                id = Ids.BEST_BOOK_RV

            }.lparams(width = matchParent, height = wrapContent)

            categoriesLabel = textView {
                id = Ids.CATEGORIES_LABEL

                text = resources.getString(R.string.categories_home)
                textColor = Color.parseColor("#6E7071")
                textSize = 14F
            }.lparams(width = wrapContent, height = wrapContent){
                marginStart = dip(10)
            }

            categoriesRV = recyclerView {
                id = Ids.CATEGORIES_RV

            }.lparams(width = matchParent, height = wrapContent){
                topMargin = dip(6)
            }
        }
    }

    private object Ids{
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_HOME
        const val INFINITE_ROTATION = R.id.INFINITE_ROTATION_HOME
        const val CONTINUE_READ = R.id.CONTINUE_READ_LABEL
        const val CONTINUE_READING_RV = R.id.CONTINUE_READING_RECYCLER_VIEW
        const val BEST_BOOK_LABEL = R.id.BEST_BOOK_LABEL
        const val BEST_BOOK_RV = R.id.BEST_BOOK_RV
        const val CATEGORIES_LABEL = R.id.CATEGORIES_LABEL
        const val CATEGORIES_RV = R.id.CATEGORIES_RV
    }
}