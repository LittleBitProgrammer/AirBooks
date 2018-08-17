package it.corelab.studios.airbooks.view.anko.layout.adapters.home

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

class Categories : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        frameLayout {
            id = Ids.FRAME_LAYOUT

            clipToPadding = false
            clipChildren = false
            outlineProvider = ViewOutlineProvider.BOUNDS

            cardView {
                id = Ids.CARD_VIEW

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
                    textSize = 16F
                    typeface = Typeface.DEFAULT_BOLD
                    isSelectable = false

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(190), height = dip(68)){
                bottomMargin = dip(9)
                rightMargin = dip(9)
            }

        }
    }

    private object Ids{

        const val FRAME_LAYOUT = R.id.FRAME_LAYOUT_CATEGORIES_ADAPTER
        const val CARD_VIEW = R.id.CARD_VIEW_CATEGORIES_ADAPTER
        const val IMAGE_VIEW = R.id.IMAGE_VIEW_CATEGORIES_ADAPTER
        const val TEXT_VIEW = R.id.TEXT_VIEW_CATEGORIES_ADAPTER

    }
}