@file:Suppress("NOTHING_TO_INLINE")

package it.corelab.studios.airbooks.view.anko.layout.intro.pages

import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.ViewManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.fragment.intro.OnBoardingFragment
import it.corelab.studios.airbooks.view.widget.RoundedImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

//inline fun ViewManager.roundImage(theme: Int = 0) = roundImage(theme) {}
inline fun ViewManager.roundImage(theme: Int = 0, init: RoundedImageView.() -> Unit) = ankoView({ RoundedImageView(it) }, theme, init)

class OnBoardingThreeLayout : AnkoComponent<OnBoardingFragment>{
    override fun createView(ui: AnkoContext<OnBoardingFragment>) = with(ui){

        linearLayout {
            id = Ids.EXTERNAL_LINEAR_LAYOUT_PAGE_THREE

            orientation = LinearLayout.VERTICAL

            linearLayout{
                id = Ids.TOP_LINEAR_LAYOUT_PAGE_THREE
                orientation = LinearLayout.VERTICAL

               // this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                roundImage{
                    id = Ids.ROUNDED_IMAGE_INTRO

                    Picasso.get().load(R.drawable.profile_picture).into(this@roundImage)
                    gravity = Gravity.CENTER_HORIZONTAL

                    y = -1500F

                }.lparams(width = dip(60),height = dip(60)){
                    topMargin =dip(30)
                }

                linearLayout {
                    id = Ids.LINEAR_STAR_RATING_INTRO
                    orientation = LinearLayout.HORIZONTAL

                    //this.setBackgroundColor(resources.getColor(R.color.forChildrenLight))

                    imageView{
                        id = Ids.STAR_ONE_INTRO

                        Picasso.get().load(R.drawable.star).into(this@imageView)
                        y = -1500F

                    }.lparams(width = dip(25), height = dip(25))

                    imageView{
                        id = Ids.STAR_TWO_INTRO

                        Picasso.get().load(R.drawable.star).into(this@imageView)
                        y = -1500F

                    }.lparams(width = dip(25), height = dip(25))

                    imageView{
                        id = Ids.STAR_THREE_INTRO

                        Picasso.get().load(R.drawable.star).into(this@imageView)
                        y = -1500F

                    }.lparams(width = dip(25), height = dip(25))

                    imageView{
                        id = Ids.STAR_FOUR_INTRO

                        Picasso.get().load(R.drawable.star).into(this@imageView)
                        y = -1500F

                    }.lparams(width = dip(25), height = dip(25))

                    imageView{
                        id = Ids.STAR_FIVE_INTRO

                        Picasso.get().load(R.drawable.star).into(this@imageView)
                        y = -1500F

                    }.lparams(width = dip(25), height = dip(25))

                }.lparams(width = wrapContent, height = wrapContent){
                    gravity = Gravity.CENTER_HORIZONTAL
                    topMargin = dip(8)
                }

                textView(R.string.book_spot_page_trhee_intro){
                    id = Ids.SPOT_BOOK_INTRO

                    y = -1500F
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textSize = sp(6).toFloat()

                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(12)
                }

                imageView{
                    id = Ids.BOOK_INTRO_PAGE_THREE

                    Picasso.get().load(R.drawable.book2).into(this@imageView)
                    y = -1500F
                    scaleType = ImageView.ScaleType.FIT_XY

                }.lparams(width = dip(200), height = dip(280)){
                    topMargin = dip(12)
                    marginStart = dip(10)
                    marginEnd = dip(10)
                }

            }.lparams(width = matchParent, height = wrapContent, weight = 1F)

            linearLayout {
                id = Ids.BOTTOM_LINEAR_LAYOUT_PAGE_THREE
                orientation = LinearLayout.VERTICAL

                //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                textView(R.string.title_intro_page_three){
                    id = Ids.TITLE_INTRO_PAGE_THREE

                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                    textSize = sp(7).toFloat()

                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(12)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

                textView(R.string.description_intro_page_three){
                    id = Ids.DESCRIPTION_INTRO_PAGE_THREE

                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                    textSize = sp(5).toFloat()

                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(8)
                    marginStart = dip(15)
                    marginEnd = dip(15)
                }

            }.lparams(width = matchParent, height = wrapContent, weight = 59.9F)
        }
    }

    private object Ids{

        const val EXTERNAL_LINEAR_LAYOUT_PAGE_THREE = R.id.EXTERNAL_LINEAR_LAYOUT_PAGE_THREE
        const val TOP_LINEAR_LAYOUT_PAGE_THREE = R.id.TOP_LINEAR_LAYOUT_PAGE_THREE
        const val BOTTOM_LINEAR_LAYOUT_PAGE_THREE = R.id.BOTTOM_LINEAR_LAYOUT_PAGE_THREE
        const val ROUNDED_IMAGE_INTRO = R.id.ROUNDED_IMAGE_INTRO
        const val LINEAR_STAR_RATING_INTRO = R.id.LINEAR_STAR_RATING_INTRO
        const val STAR_ONE_INTRO = R.id.STAR_ONE_INTRO
        const val STAR_TWO_INTRO = R.id.STAR_TWO_INTRO
        const val STAR_THREE_INTRO = R.id.STAR_THREE_INTRO
        const val STAR_FOUR_INTRO = R.id.STAR_FOUR_INTRO
        const val STAR_FIVE_INTRO = R.id.STAR_FIVE_INTRO
        const val SPOT_BOOK_INTRO = R.id.SPOT_BOOK_INTRO
        const val BOOK_INTRO_PAGE_THREE = R.id.BOKK_INTRO_PAGE_THREE
        const val TITLE_INTRO_PAGE_THREE = R.id.TITLE_INTRO_PAGE_THREE
        const val DESCRIPTION_INTRO_PAGE_THREE = R.id.DESCRIPTION_INTRO_PAGE_THREE

    }
}