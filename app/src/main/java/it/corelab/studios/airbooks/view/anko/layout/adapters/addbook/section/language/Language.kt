package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.language

import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import java.util.*

class Language : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        constraintLayout {
            lparams(matchParent, wrapContent)

            textView {
                id = Ids.LANGUAGE

                text = Locale.getDefault().displayLanguage
                textSize = 16F
            }.lparams(width = wrapContent, height = wrapContent){
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID

                topMargin = dip(8)
                bottomMargin = dip(8)
                marginStart = dip(16)
            }

            imageView {
                Glide.with(ctx).load(R.drawable.ic_navigate_next).into(this)

            }.lparams(width = dip(24), height = dip(24)){
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                endToEnd = PARENT_ID

                topMargin = dip(9)
                bottomMargin = dip(8)
                marginEnd = dip(10)
            }
        }
    }

    private object Ids{
        const val LANGUAGE = R.id.TEXT_VIEW_LANGUAGE
    }
}
