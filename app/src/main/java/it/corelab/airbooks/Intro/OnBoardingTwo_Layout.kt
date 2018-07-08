package it.corelab.airbooks.Intro

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.view.ViewManager
import android.widget.ImageView
import it.corelab.airbooks.R
import it.corelab.airbooks.widget.JustifyTextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.custom.ankoView

class OnBoardingTwo_Layout : AnkoComponent<OnboardingFragment> {

    override fun createView(ui: AnkoContext<OnboardingFragment>) = with(ui) {

        constraintLayout {
            id = Ids.CONST_LAYOUT_TWO

            imageView(R.drawable.scaffale) {
                id = Ids.SHELF_ONE
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToTop = PARENT_ID
                topMargin = dip(110)
                marginStart = dip(8)
                marginStart = dip(8)
            }

            imageView(R.drawable.scaffale) {
                id = Ids.SHELF_TWO
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToBottom = Ids.SHELF_ONE
                topMargin = dip(102)
                marginStart = dip(8)
                marginStart = dip(8)
            }

            imageView(R.drawable.scaffale) {
                id = Ids.SHELF_THREE
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToBottom = Ids.SHELF_TWO
                topMargin = dip(102)
                marginStart = dip(8)
                marginStart = dip(8)
            }

            cardView {
                clipToPadding = false
                cardElevation = dip(7).toFloat()
                id = Ids.FIRST_TOP_CARD

                imageView(R.drawable.book2) {
                    id = Ids.FIRST_TOP_IMAGE
                    scaleType = ImageView.ScaleType.FIT_XY

                }.lparams(width = matchParent, height = matchParent)


            }.lparams(width = dip(74), height = dip(106)) {
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_ONE
                marginStart = dip(75)
                bottomMargin = dip(26)
            }



            cardView {
                clipToPadding = false
                cardElevation = dip(7).toFloat()
                id = Ids.FIRST_TOP_CARD

                imageView(R.drawable.book3) {
                    id = Ids.FIRST_TOP_IMAGE
                    scaleType = ImageView.ScaleType.FIT_XY

                }.lparams(width = matchParent, height = matchParent)


            }.lparams(width = dip(74), height = dip(106)) {
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_TWO
                marginStart = dip(75)
                bottomMargin = dip(26)
            }



            cardView {
                clipToPadding = false
                cardElevation = dip(7).toFloat()
                id = Ids.FIRST_TOP_CARD

                imageView (R.drawable.book5){
                    id = Ids.FIRST_TOP_IMAGE
                    scaleType = ImageView.ScaleType.FIT_XY

                }.lparams(width = matchParent, height = matchParent)


            }.lparams(width = dip(74), height = dip(106)){
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_THREE
                marginStart = dip(75)
                bottomMargin = dip(26)
            }
        }
    }

    private object Ids{
        const val CONST_LAYOUT_TWO = 1
        const val SHELF_ONE = 2
        const val SHELF_TWO = 3
        const val SHELF_THREE = 4
        const val FIRST_TOP_CARD = 5
        const val FIRST_TOP_IMAGE = 6
    }
}