package it.corelab.studios.airbooks.view.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v4.content.ContextCompat
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View


class DividerItemDecoration : RecyclerView.ItemDecoration {

    private var divider: Drawable? = null

    /**
     * Default divider will be used
     */
    constructor(context: Context) {
        val styledAttributes = context.obtainStyledAttributes(ATTRS)
        divider = styledAttributes.getDrawable(0)
        styledAttributes.recycle()
    }

    /**
     * Custom divider will be used
     */
    constructor(context: Context, resId: Int) {
        divider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight

            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.right=100
        outRect.left=50
    }
    companion object {

        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}