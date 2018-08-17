package it.corelab.studios.airbooks.view.anko.layout.review

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.review.Review
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ReviewView: AnkoComponent<Review> {

    lateinit var recyclerview: RecyclerView

    override fun createView(ui: AnkoContext<Review>) = with(ui) {
        constraintLayout {
            id = Ids.CONSTRAINT_LAYOUT

            recyclerview = recyclerView {
                id = Ids.RECYCLER_VIEW

                ViewCompat.setNestedScrollingEnabled(this, false)
                setItemViewCacheSize(20)
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                this.setHasFixedSize(true)

            }.lparams(width = matchParent, height = wrapContent){

                bottomToBottom = PARENT_ID
                topToTop = PARENT_ID
                endToEnd = PARENT_ID
                startToStart = PARENT_ID

            }
        }

    }

    private object Ids{
        const val CONSTRAINT_LAYOUT = R.id.CONSTRAINT_LAYOUT_ALL_REVIEW
        const val RECYCLER_VIEW = R.id.RECYCLER_VIEW_ALL_REVIEW
    }
}