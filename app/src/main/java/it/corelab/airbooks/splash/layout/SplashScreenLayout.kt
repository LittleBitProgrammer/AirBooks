package it.corelab.airbooks.splash.layout

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.widget.LinearLayout
import it.corelab.airbooks.R
import it.corelab.airbooks.splash.activity.SplashActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class SplashScreenLayout : AnkoComponent<SplashActivity> {
    override fun createView(ui: AnkoContext<SplashActivity>)= with(ui) {

        constraintLayout {
            id = Ids.LINEAR_LAYOUT_EXTERNAL
            this.setBackgroundColor(resources.getColor(R.color.white))

            linearLayout {
                id = Ids.LINEAR_INTERNAL

                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER

                textView("Airbooks"){
                    id = Ids.APP_NAME

                    typeface = ResourcesCompat.getFont(ctx,R.font.koliko)
                    textSize = sp(15).toFloat()
                    textColor = resources.getColor(R.color.title_color_splash_screen)
                    alpha = 0F
                }.lparams(width = wrapContent, height = wrapContent){
                    topMargin = dip(16)
                }

                textView("Leggi, crea, ama"){
                    id = Ids.SUBTITLE

                    typeface = ResourcesCompat.getFont(ctx,R.font.cookie)
                    textSize = sp(13).toFloat()
                    textColor = resources.getColor(R.color.description_color_splash_screen)
                    alpha = 0F
                }.lparams(width = wrapContent, height = dip(60)){
                    topMargin = dip(0)
                }

            }.lparams(width = matchParent, height = wrapContent){
                //topPadding = dip(-130)
                topToTop = Ids.CENTRAL_LOGO
                topMargin = dip(50)
            }
            imageView(R.drawable.icon_splash_screen) {
                id = Ids.CENTRAL_LOGO

            }.lparams(width = dip(150), height = dip(150)){
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                rightToRight = PARENT_ID
                leftToLeft = PARENT_ID

                topMargin = dip(0)
                leftMargin = dip(0)
                rightMargin = dip(0)
                bottomMargin = dip(0)
            }
        }
    }

        private object Ids {
            const val LINEAR_LAYOUT_EXTERNAL = R.id.LINEAR_LAYOUT_EXTERNAL_SPLASH_SCREEN
            const val CENTRAL_LOGO = R.id.CENTRAL_LOGO
            const val LINEAR_INTERNAL = R.id.LINEAR_INTERNAL_SPLASH_SCREEN
            const val APP_NAME = R.id.APP_NAME_SPLASH_SCREEN
            const val SUBTITLE = R.id.SUBTITLE
        }
}