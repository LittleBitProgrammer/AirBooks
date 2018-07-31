package it.corelab.studios.airbooks

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import developer.shivam.library.DiagonalView


class CustomNested : NestedScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun takeScrollVariation(diagonalView: DiagonalView) {
        diagonalView.setAngle(14F)

        setOnScrollChangeListener(OnScrollChangeListener { diagonal, scrollX, scrollY, oldScrollX, oldScrollY ->
            val diff = diagonal.height + diagonal.scrollY - diagonal.bottom

            val variation = 47.0f - diff * 0.05f
            Log.i("variationlog", "" + diff);

            takeYPosition(diagonalView)

            if (diff <= 620) {
                diagonalView.setAngle(14F)
                diagonalView.y = -scrollY.toFloat()
            } else if (diff <= 906 && variation <= 14.0f) {
                diagonalView.setAngle(variation)
                diagonalView.y = -scrollY.toFloat()
                // Log.i("AIRBOOKS >= 61000:---> ", "" + (46.0f - (diff * 0.05f)));
            }else{
                diagonalView.y = -scrollY.toFloat()
            }
        })
    }

    private fun takeYPosition(view: View) {
        val xy = IntArray(2)
        view.getLocationOnScreen(xy)
        val yViewPosition = xy[1].toFloat()
        Log.i("CUSTOMNESTED Y: ", "$yViewPosition")
    }

}

