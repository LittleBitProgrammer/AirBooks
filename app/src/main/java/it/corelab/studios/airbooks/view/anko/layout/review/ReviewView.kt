package it.corelab.studios.airbooks.view.anko.layout.review

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.review.Review
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class ReviewView: AnkoComponent<Review> {

    override fun createView(ui: AnkoContext<Review>) = with(ui) {
        constraintLayout {
            id = Ids.CONSTRAINT_LAYOUT

            listView {
                id = Ids.LIST_VIEW

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
        const val LIST_VIEW = R.id.LIST_VIEW_ALL_REVIEW
    }
}