package it.corelab.airbooks.Intro

import android.view.Gravity
import android.widget.LinearLayout
import it.corelab.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.support.v4.viewPager

class OnBoardingViewPager_Host : AnkoComponent<IntroActivity> {
    override fun createView(ui: AnkoContext<IntroActivity>) = with(ui) {

        coordinatorLayout {
            id = Ids.ONBOARDING_LAYOUT
            fitsSystemWindows = true

            viewPager {
                id = Ids.ONBOARDING_VIEWPAGER
                clipToPadding = false

            }.lparams(width = matchParent, height = matchParent)

            frameLayout {

                id = Ids.FRAME_LAYOUT
                padding= dip(16)

                linearLayout {
                    id = Ids.LINEAR_LAYOUT
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = Ids.INDICATOR_ONE
                        background = resources.getDrawable(R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    imageView {
                        id = Ids.INDICATOR_TWO
                        background = resources.getDrawable(R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    imageView {
                        id = Ids.INDICATOR_THREE
                        background = resources.getDrawable(R.drawable.indicator_unselected)

                    }.lparams(width = dip(8), height = dip(8)){
                        marginEnd = dip(8)
                        rightMargin = dip(8)
                    }

                    imageView {
                        id = Ids.INDICATOR_FOUR
                        background = resources.getDrawable(R.drawable.indicator_unselected)

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
        const val ONBOARDING_LAYOUT = R.id.ONBOARDING_LAYOUT_BASE
        const val ONBOARDING_VIEWPAGER = R.id.ONBOARDING_VIEWPAGER_BASE
        const val FRAME_LAYOUT = R.id.FRAME_LAYOUT_BASE
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_BASE
        const val INDICATOR_ONE = R.id.INDICATOR_ONE
        const val INDICATOR_TWO = R.id.INDICATOR_TWO
        const val INDICATOR_THREE = R.id.INDICATOR_THREE
        const val INDICATOR_FOUR = R.id.INDICATOR_FOUR
    }
}