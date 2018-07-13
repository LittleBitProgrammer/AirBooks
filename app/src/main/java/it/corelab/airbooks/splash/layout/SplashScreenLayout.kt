package it.corelab.airbooks.splash.layout

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import it.corelab.airbooks.R
import it.corelab.airbooks.splash.activity.SplashActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class SplashScreenLayout : AnkoComponent<SplashActivity> {
    override fun createView(ui: AnkoContext<SplashActivity>)= with(ui) {

        constraintLayout {
            id = Ids.CONSTRAINT_LAYOUT
            this.setBackgroundColor(resources.getColor(R.color.white))

            imageView(R.drawable.icon_splash_screen) {
                id = Ids.CENTRAL_LOGO

            }.lparams(width = dip(150), height = dip(150)){
                topToTop = PARENT_ID
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID
                bottomToBottom = PARENT_ID

                topMargin = dip(0)
                bottomMargin = dip(100)
                rightMargin = dip(0)
                leftMargin = dip(0)
            }

            progressBar {
                id = Ids.PROGRESS_BAR

            }.lparams(width = dip(60), height = dip(60)){
                topToBottom = Ids.CENTRAL_LOGO
                leftToLeft = PARENT_ID
                rightToRight = PARENT_ID

                topMargin = dip(80)
            }
        }
    }

        private object Ids {
            const val CONSTRAINT_LAYOUT = R.id.CONSTRAINT_LAYOUT_SPLASH_SCREEN
            const val CENTRAL_LOGO = R.id.CENTRAL_LOGO
            const val PROGRESS_BAR = R.id.PROGRESS_BAR_SPLASH_SCREEN
        }
}