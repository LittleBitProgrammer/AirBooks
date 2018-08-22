package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.pdf

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class Pdf : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {

            lparams(matchParent, wrapContent)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL

            cardView {

                elevation = dip(0).toFloat()
                backgroundColor = Color.parseColor("#00000000")

                imageView {

                    Glide.with(ctx).load(R.drawable.ic_empty_pdf).into(this)
                    scaleType = ImageView.ScaleType.FIT_XY
                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(60), height = dip(85)) {
                marginStart = dip(16)
                topMargin = dip(16)
                bottomMargin = dip(16)
            }

            textView {

                text = "Tap to choose the book"
                textSize = 15F
                textColor = Color.parseColor("#5D636C")
                maxLines = 1

            }.lparams(width = wrapContent, height = wrapContent) {
                marginStart = dip(16)
            }

            imageView {
                Glide.with(ctx).load(R.drawable.ic_navigate_next).into(this)

            }.lparams(width = dip(24), height = dip(24)) {
                gravity = Gravity.END + Gravity.CENTER_VERTICAL
                marginStart = dip(36)
                topMargin = dip(1)
            }
        }
    }
}