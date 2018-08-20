package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.categories

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class CategoriesItem : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        frameLayout {

            clipToPadding = false
            clipChildren = false
            outlineProvider = ViewOutlineProvider.BOUNDS

            cardView {

                radius = dip(5).toFloat()
                preventCornerOverlap = true

                imageView {
                    id = Ids.IMAGE_VIEW

                    scaleType = ImageView.ScaleType.FIT_XY
                }.lparams(width = matchParent, height = matchParent)

                textView {
                    id = Ids.TEXT_VIEW

                    gravity = Gravity.CENTER_VERTICAL
                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                    textColor = Color.parseColor("#FFFFFF")
                    textSize = 14F
                    typeface = Typeface.DEFAULT_BOLD
                    isSelectable = false

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(115), height = dip(38)) {
                bottomMargin = dip(9)
                rightMargin = dip(7)
            }

        }
    }

    private object Ids {

        const val IMAGE_VIEW = R.id.IMAGE_VIEW_CATEGORIES_DIALOG
        const val TEXT_VIEW = R.id.TEXT_VIEW_CATEGORIES_DIALOG

    }
}