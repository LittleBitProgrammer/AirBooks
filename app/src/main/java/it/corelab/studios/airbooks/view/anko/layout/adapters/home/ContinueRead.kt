package it.corelab.studios.airbooks.view.anko.layout.adapters.home

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.LinearLayout
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ContinueRead : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            id = Ids.LINEAR_LAYOUT
            clipToPadding = false
            clipChildren = false
            outlineProvider = ViewOutlineProvider.BOUNDS
            orientation = LinearLayout.HORIZONTAL

            cardView {
                id = Ids.CARD_VIEW
                clipToPadding = false
                clipChildren = false
                radius = dip(3).toFloat()
                elevation = dip(4).toFloat()
                preventCornerOverlap = false
                useCompatPadding = true

                imageView {
                    id = Ids.IMAGE_VIEW
                    scaleType = ImageView.ScaleType.FIT_XY

                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(80), height = dip(115)){
                rightPadding = dip(4)
                bottomPadding = dip(9)
            }
        }
    }

    private object Ids{
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_CONTINUE_READ_ADAPTER
        const val CARD_VIEW = R.id.CARD_VIEW_CONTINUE_READ_ADAPTER
        const val IMAGE_VIEW = R.id.IMAGE_VIEW_CONTINUE_READ_ADPTER
    }
}