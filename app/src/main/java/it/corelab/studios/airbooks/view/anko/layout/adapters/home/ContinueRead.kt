package it.corelab.studios.airbooks.view.anko.layout.adapters.home

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MovieUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        linearLayout {
            orientation = LinearLayout.HORIZONTAL

            cardView {

                imageView {
                    scaleType = ImageView.ScaleType.FIT_XY
                    
                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(80), height = dip(115))
        }
    }
}