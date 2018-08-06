package it.corelab.studios.airbooks.view.anko.layout.intro.host

import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import it.corelab.studios.airbooks.view.activity.intro.IntroActivity
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.viewPager

class OnBoardingViewPagerHost : AnkoComponent<IntroActivity> {

    lateinit var dot : ImageView
    lateinit var dotTwo : ImageView
    lateinit var dotThree : ImageView
    lateinit var dotFour : ImageView
    lateinit var viewPager: ViewPager

    override fun createView(ui: AnkoContext<IntroActivity>) = with(ui) {

        coordinatorLayout {
            id = Ids.ON_BOARDING_LAYOUT
            fitsSystemWindows = true

            viewPager = viewPager {
                id = Ids.ON_BOARDING_VIEWPAGER
                clipToPadding = false
                overScrollMode = View.OVER_SCROLL_NEVER

            }.lparams(width = matchParent, height = matchParent)

            frameLayout {

                id = Ids.FRAME_LAYOUT
                padding= dip(16)

                linearLayout {
                    id = Ids.LINEAR_LAYOUT
                    orientation = LinearLayout.HORIZONTAL

                    dot = imageView {
                        id = Ids.INDICATOR_ONE
                        background = ContextCompat.getDrawable(ctx,R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    dotTwo = imageView {
                        id = Ids.INDICATOR_TWO
                        background = ContextCompat.getDrawable(ctx,R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    dotThree = imageView {
                        id = Ids.INDICATOR_THREE
                        background = ContextCompat.getDrawable(ctx,R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    dotFour = imageView {
                        id = Ids.INDICATOR_FOUR
                        background = ContextCompat.getDrawable(ctx,R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                }.lparams( width = wrapContent, height = wrapContent){
                    gravity = Gravity.CENTER
                }
            }.lparams(width = matchParent, height = dip(56)){
                gravity = Gravity.BOTTOM
            }

        }

    }

    private object Ids{
        const val ON_BOARDING_LAYOUT = R.id.ONBOARDING_LAYOUT_BASE
        const val ON_BOARDING_VIEWPAGER = R.id.ONBOARDING_VIEWPAGER_BASE
        const val FRAME_LAYOUT = R.id.FRAME_LAYOUT_BASE
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_BASE
        const val INDICATOR_ONE = R.id.INDICATOR_ONE
        const val INDICATOR_TWO = R.id.INDICATOR_TWO
        const val INDICATOR_THREE = R.id.INDICATOR_THREE
        const val INDICATOR_FOUR = R.id.INDICATOR_FOUR
    }
}