package it.corelab.studios.airbooks.view.anko.layout.login.host

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.view.ViewManager
import android.widget.TextView
import com.squareup.picasso.Picasso
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.activity.login.Login
import it.corelab.studios.airbooks.view.widget.RoundedImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.custom.ankoView

//inline fun ViewManager.roundImage(theme: Int = 0) = roundImage(theme) {}
inline fun ViewManager.roundImage(theme: Int = 0, init: RoundedImageView.() -> Unit)= ankoView({ RoundedImageView(it) }, theme, init)

class LoginHost : AnkoComponent<Login> {
    override fun createView(ui: AnkoContext<Login>) = with(ui) {

        scrollView {
            id = Ids.SCROLL_VIEW

            isFillViewport = true
            overScrollMode = View.OVER_SCROLL_NEVER

            constraintLayout {
                id = Ids.CONSTRAINT_LAYOUT

                background = ContextCompat.getDrawable(ctx,R.drawable.background_color)

                roundImage {
                    id = Ids.LOGO

                    Picasso.get().load(R.drawable.logo_login).into(this@roundImage)
                    contentDescription = resources.getString(R.string.simple_circle_background_login_host)

                }.lparams(width = dip(90), height = dip(90)){
                    topToTop = PARENT_ID
                    leftToLeft = PARENT_ID
                    rightToRight = PARENT_ID

                    topMargin = dip(34)
                    leftMargin = dip(0)
                    rightMargin = dip(0)
                }

                textView("Airbooks"){
                    id = Ids.APP_NAME

                    typeface = ResourcesCompat.getFont(ctx,R.font.koliko)
                    textSize = sp(15).toFloat()
                    textColor = ContextCompat.getColor(ctx, R.color.white)
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                }.lparams(width = wrapContent, height = wrapContent){
                    topToBottom = Ids.LOGO
                    rightToRight = PARENT_ID
                    leftToLeft = PARENT_ID

                    topMargin = dip(2)
                    leftMargin = dip(0)
                    rightMargin = dip(0)
                }

                frameLayout {
                    id = Ids.FRAME_CONTAINER

                }.lparams(width = matchConstraint, height = matchConstraint){
                    topToBottom = Ids.APP_NAME
                    bottomToBottom = PARENT_ID
                    endToEnd = PARENT_ID
                    startToStart = PARENT_ID

                    //this i necessary because the alternative is a general padding on the four side
                    //don't deactivate the padding
                    leftPadding = dip(0)
                    rightPadding = dip(0)
                    bottomPadding = dip(0)
                    topPadding = dip(0)

                }

            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private object Ids{
        const val SCROLL_VIEW = R.id.SCROLL_VIEW_LOGIN_ACTIVITY
        const val CONSTRAINT_LAYOUT = R.id.CONSTRAINT_LAYOUT_LOGIN_ACTIVITY
        const val LEFT_ARROW = R.id.LEFT_ARROW_LOGIN_ACTIVITY
        const val LOGO = R.id.LOGO_LOGIN_ACTIVITY
        const val APP_NAME = R.id.APP_NAME_LOGIN_ACTIVITY
        const val FRAME_CONTAINER = R.id.FRAME_CONTAINER_LOGIN_ACTIVITY
    }
}