package it.corelab.airbooks.intro.Pages.Layout

import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import it.corelab.airbooks.intro.Handler.OnboardingFragment
import it.corelab.airbooks.R
import org.jetbrains.anko.*

class OnboardingFour_Layout: AnkoComponent<OnboardingFragment> {
    override fun createView(ui: AnkoContext<OnboardingFragment>) = with(ui){

        /*
       *
       * XLARGE CONFIGURATION / TABLET - VERSION
       *
        */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.XLARGE ){

        }

        /*
       *
       * LARGE CONFIGURATION - VERSION
       *
        */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.LARGE ){

        }

        /*
       *
       * NORMAL CONFIGURATION - VERSION
       *
        */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.NORMAL ){

            return@with linearLayout {

                id = Ids.EXTERNAL_LINEARLAYOUT_PAGE_FOUR

                orientation = LinearLayout.VERTICAL

                linearLayout{
                    id = Ids.TOP_LINEAR_LAYOUT_PAGE_FOUR
                    orientation = LinearLayout.VERTICAL

                    //this.setBackgroundColor(resources.getColor(R.color.forChildrenDark))

                    imageView(R.drawable.teacup){
                        id = Ids.TEACUP

                        y = 2200F
                    }.lparams(width = dip(160), height = dip(60)){
                        marginEnd = dip(100)
                        marginStart = dip(100)
                        topMargin = dip(10)

                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    textView("+"){
                        id = Ids.PLUS_UNDER_TEACUP

                        y = 2200F
                        textSize = sp(13).toFloat()
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(-10)
                    }

                    imageView(R.drawable.sofa){
                        id = Ids.SOFA

                        y = 2200F
                    }.lparams(width = wrapContent, height = dip(100)){
                        marginStart = dip(80)
                        marginEnd = dip(80)
                        topMargin = dip(0)
                    }

                    textView("+"){
                        id = Ids.PLUS_UNDER_SOFA

                        y = 2200F
                        textSize = sp(13).toFloat()
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(0)
                    }

                    linearLayout {
                        id = Ids.LINEAR_BOOKS_PAGE_FOUR

                        orientation = LinearLayout.HORIZONTAL

                        imageView(R.drawable.book2){
                            id = Ids.LEFT_BOOK_PAGE_FOUR

                            x = -1100F

                        }.lparams(width = dip(75), height = dip(107)){
                            marginStart = dip(60)
                        }

                        imageView(R.drawable.book3){
                            id = Ids.CENTER_BOOK_PAGE_FOUR

                            x = -1100F
                        }.lparams(width = dip(75), height = dip(107)){
                            marginStart = dip(10)
                        }

                        imageView(R.drawable.book5){
                            id = Ids.RIGHT_BOOK_PAGE_FOUR

                            x =-1100F
                        }.lparams(width = dip(75), height = dip(107)){
                            marginStart = dip(10)
                        }
                    }.lparams(width = matchParent, height = wrapContent)

                    textView("="){
                        id = Ids.EQUAL_PAGE_FOUR

                        y = 2200F
                        textSize = sp(13).toFloat()
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                    }.lparams(width = matchParent, height = dip(35)){
                        topMargin = dip(-5)
                    }

                    linearLayout {
                        id = Ids.LINEAR_EMOJI
                        //this.setBackgroundColor(resources.getColor(R.color.biografyDark))

                        imageView(R.drawable.emoji_heart_face){
                            id = Ids.FACE_HEART

                            y = 2200F
                        }.lparams(width = dip(30), height = dip(30)){
                            topMargin = dip(20)
                            marginStart = dip(145)
                        }

                        imageView(R.drawable.heart){
                            id = Ids.HEART_EMOJI

                            y = 2200F
                        }.lparams(width = dip(30), height = dip(30)){
                            topMargin = dip(20)
                            marginStart = dip(10)
                        }
                    }.lparams(width = matchParent, height = dip(70)){
                        topMargin = dip(-10)
                    }

                }.lparams(width = matchParent, height = wrapContent, weight = 0.01F)

                linearLayout {
                    id = Ids.BOTTOM_LINEAR_LAYOUT_PAGE_FOUR
                    orientation = LinearLayout.VERTICAL
                    //this.setBackgroundColor(resources.getColor(R.color.scifiDark))

                    textView{
                        id = Ids.TITLE_PAGE_FOUR
                        text = resources.getText(R.string.title_page_four)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textColor,null)
                        textSize = sp(8).toFloat()
                    }.lparams(width = matchParent, height = wrapContent) {

                        topMargin = dip(0)
                        //marginStart = dip(15)
                        //marginEnd = dip(15)
                    }

                    textView{
                        id = Ids.DESCRIPTION_PAGE_FOUR
                        text = resources.getText(R.string.description_page_four)
                        textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                        textColor = ResourcesCompat.getColor(resources,R.color.textDesc,null)
                        textSize = sp(5).toFloat()

                    }.lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(8)
                        marginStart = dip(15)
                        marginEnd = dip(15)
                    }

                }.lparams(width = matchParent, height = wrapContent, weight = 0.7F)

                linearLayout {
                    id = Ids.END_LINEAR_LAYOUT_PAGE_FOUR
                    orientation = LinearLayout.VERTICAL
                    //this.setBackgroundColor(resources.getColor(R.color.biografyDark))

                    button(R.string.button_text_page_four){
                        id = Ids.BUTTON_PAGE_FOUR

                        textColor =  ContextCompat.getColor(context,R.color.white)
                        background = resources.getDrawable(R.drawable.button_intro)
                        stateListAnimator = null

                    }.lparams(width = matchParent, height = dip(35)){

                        marginStart = dip(30)
                        marginEnd = dip(30)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }.lparams(width = matchParent, height = wrapContent, weight = 2F)
            }

        }

        /*
       *
       * SMALL CONFIGURATION - VERSION
       *
        */

        configuration( orientation = Orientation.PORTRAIT, screenSize  = ScreenSize.SMALL ){

        }
        linearLayout {

            id = Ids.EXTERNAL_LINEARLAYOUT_PAGE_FOUR

            imageView(R.drawable.teacup){
                id = Ids.TEACUP

                scaleType = ImageView.ScaleType.FIT_XY

            }.lparams(width = wrapContent, height = wrapContent)
        }
    }

    private object Ids{
        const val EXTERNAL_LINEARLAYOUT_PAGE_FOUR = R.id.EXTERNAL_LINEARLAYOUT_PAGE_FOUR
        const val TOP_LINEAR_LAYOUT_PAGE_FOUR = R.id.TOP_LINEAR_LAYOUT_PAGE_FOUR
        const val BOTTOM_LINEAR_LAYOUT_PAGE_FOUR = R.id.BOTTOM_LINEAR_LAYOUT_PAGE_FOUR
        const val END_LINEAR_LAYOUT_PAGE_FOUR = R.id.END_LINEAR_LAYOUT_PAGE_FOUR
        const val TEACUP = R.id.TEACUP
        const val PLUS_UNDER_TEACUP = R.id.PLUS_UNDER_TEACUP
        const val SOFA = R.id.SOFA
        const val PLUS_UNDER_SOFA = R.id.PLUS_UNDER_SOFA
        const val LINEAR_BOOKS_PAGE_FOUR = R.id.LINEAR_BOOKS_PAGE_FOUR
        const val LEFT_BOOK_PAGE_FOUR = R.id.LEFT_BOOK_PAGE_FOUR
        const val CENTER_BOOK_PAGE_FOUR = R.id.CENTER_BOOK_PAGE_FOUR
        const val RIGHT_BOOK_PAGE_FOUR = R.id.RIGHT_BOOK_PAGE_FOUR
        const val EQUAL_PAGE_FOUR = R.id.EQUAL_PAGE_FOUR
        const val FACE_HEART = R.id.FACE_HEART
        const val HEART_EMOJI = R.id.HEART_EMOJI
        const val LINEAR_EMOJI = R.id.LINEAR_EMOJI
        const val TITLE_PAGE_FOUR = R.id.TITLE_PAGE_FOUR
        const val DESCRIPTION_PAGE_FOUR = R.id.DESCRIPTION_PAGE_FOUR
        const val BUTTON_PAGE_FOUR = R.id.BUTTON_PAGE_FOUR
    }
}