package it.corelab.studios.airbooks.view.anko.layout.adapters.addbook.section.cover

import android.content.Intent
import android.graphics.Color
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.AddSection.PICK_IMAGE
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class CoverBook : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        linearLayout {
            id = Ids.LINEAR_LAYOUT

            lparams(matchParent, wrapContent)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL

            cardView {
                id = Ids.CARD_VIEW

                elevation = dip(0).toFloat()
                //backgroundColor = Color.parseColor("#000000")
                radius = dip(4).toFloat()

                imageView{
                    id = Ids.BOOK_COVER

                    Glide.with(ctx).load(R.drawable.ic_emptybook).into(this)
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams(width = matchParent, height = matchParent)

            }.lparams(width = dip(90), height = dip(130)){
                marginStart = dip(16)
                topMargin = dip(16)
                bottomMargin = dip(16)
            }

            textView{

                text = "Tap to choose a cover"
                textSize = 15F
                textColor = Color.parseColor("#5D636C")
                maxLines = 1

            }.lparams(width = wrapContent, height = wrapContent){
                marginStart = dip(16)
            }

            imageView {
                Glide.with(ctx).load(R.drawable.ic_navigate_next).into(this)

            }.lparams(width = dip(24), height = dip(24)){
                gravity = Gravity.END + Gravity.CENTER_VERTICAL
                marginStart = dip(36)
                topMargin = dip(1)
            }
        }
    }

    private object  Ids{
        const val LINEAR_LAYOUT = R.id.LINEAR_LAYOUT_CHOOSE_BOOK_COVER
        const val CARD_VIEW = R.id.CARD_VIEW_CHOOSE_BOOK_COVER
        const val BOOK_COVER = R.id.BOOK_COVER_CHOOSE_BOOK_COVER
    }
}