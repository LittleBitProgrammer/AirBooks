package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.pdf

import android.graphics.Color
import android.opengl.Visibility
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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
                this.setCardBackgroundColor(Color.TRANSPARENT)
                radius = dip(4).toFloat()

                imageView {
                    id = Ids.IMAGE

                    Glide.with(ctx).load(R.drawable.ic_empty_pdf).into(this)
                    scaleType = ImageView.ScaleType.FIT_XY
                }.lparams(width = matchParent, height = matchParent)

                textView {
                    id = Ids.EXTENSION

                    text = "   "
                    textSize = 14F
                    textColor = Color.parseColor("#FFFFFF")
                    maxLines = 1

                    textAlignment = TextView.TEXT_ALIGNMENT_CENTER

                    visibility = View.INVISIBLE
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER_VERTICAL
                }

            }.lparams(width = dip(60), height = dip(85)) {
                marginStart = dip(16)
                topMargin = dip(16)
                bottomMargin = dip(16)
            }

            textView {
                id = Ids.TEXT

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

    private object Ids{
        const val TEXT = R.id.TEXT_VIEW_CHOOSE_FILE
        const val IMAGE =  R.id.IMAGE_VIEW_CHOOSE_FILE
        const val EXTENSION = R.id.TEXT_VIEW_EXTENSION_CHOOSE_FILE
    }
}