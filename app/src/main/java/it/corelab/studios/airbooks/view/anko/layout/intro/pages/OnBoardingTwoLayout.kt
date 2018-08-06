package it.corelab.studios.airbooks.view.anko.layout.intro.pages

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.widget.TextView
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.view.fragment.intro.OnBoardingFragment
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint

class OnBoardingTwoLayout : AnkoComponent<OnBoardingFragment> {

    override fun createView(ui: AnkoContext<OnBoardingFragment>) = with(ui) {

        constraintLayout {
            id = Ids.CONST_LAYOUT_TWO

            imageView {
                id = Ids.SHELF_ONE

                Picasso.get().load(R.drawable.scaffale).into(this@imageView)
                y = -1500F
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToTop = PARENT_ID
                topMargin = dip(120)
                marginStart = dip(20)
                marginEnd = dip(20)
            }

            imageView {
                id = Ids.SHELF_TWO

                Picasso.get().load(R.drawable.scaffale).into(this@imageView)
                y = -1500F
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToBottom = Ids.SHELF_ONE
                topMargin = dip(110)
                marginStart = dip(20)
                marginEnd = dip(20)
            }

            imageView{
                id = Ids.SHELF_THREE

                Picasso.get().load(R.drawable.scaffale).into(this@imageView)
                y = -1500F
            }.lparams(width = matchConstraint, height = wrapContent) {
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID
                topToBottom = Ids.SHELF_TWO
                topMargin = dip(110)
                marginStart = dip(20)
                marginEnd = dip(20)
            }

            imageView {
                id = Ids.FIRST_FIRST_IMAGE

                Picasso.get().load(R.drawable.book2).into(this@imageView)
                x = -800F

            }.lparams(width = dip(84), height = dip(106)) {
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_ONE
                marginStart = dip(85)
                bottomMargin = dip(24)
            }



            imageView {
                id = Ids.FIRST_SECOND_IMAGE

                Picasso.get().load(R.drawable.book3).into(this@imageView)
                x = -800F

            }.lparams(width = dip(84), height = dip(106)) {
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_TWO
                marginStart = dip(85)
                bottomMargin = dip(24)
            }



            imageView  {
                id = Ids.FIRST_THIRD_IMAGE

                Picasso.get().load(R.drawable.book5).into(this@imageView)
                x = -800F
            }.lparams(width = dip(84), height = dip(106)){
                leftToLeft = PARENT_ID
                bottomToBottom = Ids.SHELF_THREE
                marginStart = dip(85)
                bottomMargin = dip(24)
            }

            imageView{
                id = Ids.SECOND_FIRST_IMAGE

                Picasso.get().load(R.drawable.book1).into(this@imageView)
                x = -800F

            }.lparams(width = dip(84), height = dip(106)) {
                leftToRight = Ids.FIRST_FIRST_IMAGE
                bottomToBottom = Ids.SHELF_ONE
                leftMargin = dip(20)
                bottomMargin = dip(24)
            }

            imageView {
                id = Ids.SECOND_SECOND_IMAGE

                Picasso.get().load(R.drawable.book4).into(this@imageView)
                x = -800F

            }.lparams(width = dip(84), height = dip(106)) {
                leftToRight = Ids.FIRST_SECOND_IMAGE
                bottomToBottom = Ids.SHELF_TWO
                leftMargin = dip(20)
                bottomMargin = dip(24)
            }

            imageView {
                id = Ids.SECOND_THIRD_IMAGE

                Picasso.get().load(R.drawable.book7).into(this@imageView)
                x = -800F

            }.lparams(width = dip(84), height = dip(106)) {
                leftToRight = Ids.FIRST_THIRD_IMAGE
                bottomToBottom = Ids.SHELF_THREE
                leftMargin = dip(20)
                bottomMargin = dip(24)
            }

            textView(R.string.title_page_two){
                id = Ids.TITLE_PAGE_TWO

                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                textSize = sp(7).toFloat()

            }.lparams(width = matchParent, height = wrapContent){
                topToBottom = Ids.SHELF_THREE
                topMargin = dip(35)
                marginStart = dip(15)
                marginEnd = dip(15)
            }

            textView(R.string.description_page_two){
                id = Ids.DESC_PAGE_TWO

                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                textSize = sp(5).toFloat()

            }.lparams(width = matchParent, height = wrapContent){
                topToBottom = Ids.TITLE_PAGE_TWO
                topMargin = dip(8)
                marginStart = dip(15)
                marginEnd = dip(15)
            }
        }
    }

    private object Ids{
        const val CONST_LAYOUT_TWO = R.id.CONST_LAYOUT_TWO
        const val SHELF_ONE = R.id.SHELF_ONE
        const val SHELF_TWO = R.id.SHELF_TWO
        const val SHELF_THREE = R.id.SHELF_THREE
        const val FIRST_FIRST_IMAGE = R.id.FIRST_FIRST_IMAGE
        const val FIRST_SECOND_IMAGE = R.id.FIRST_SECOND_IMAGE
        const val FIRST_THIRD_IMAGE = R.id.FIRST_THIRD_IMAGE
        const val SECOND_FIRST_IMAGE = R.id.SECOND_FIRST_IMAGE
        const val SECOND_SECOND_IMAGE = R.id.SECOND_SECOND_IMAGE
        const val SECOND_THIRD_IMAGE = R.id.SECOND_THIRD_IMAGE
        const val TITLE_PAGE_TWO = R.id.TITLE_PAGE_TWO
        const val DESC_PAGE_TWO = R.id.DESC_PAGE_TWO
    }
}