package it.corelab.studios.airbooks

import android.content.Context
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View

import it.corelab.studios.airbooks.fragment.FadeFragment

class CustomNested : NestedScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    fun takeScrollVariation() {
        setOnScrollChangeListener(OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val diff = v.height + v.scrollY - v.bottom

            val variation = 46.0f - diff * 0.05f
            //Log.i("AIRBOOKS >= 600:-----> ", "" + diff);

            //takeYPosition(FadeFragment.diagonalView);

            if (diff <= 600) {
                //FadeFragment.diagonalView.setAngle(16.0f);
                //FadeFragment.angleVariation = 16.0f;
            } else if (diff <= 900 && variation <= 16.0f) {
                //topBar.animate().scaleY(0.8f);
                //FadeFragment.diagonalView.setAngle(variation);
                //FadeFragment.angleVariation = variation;
                // Log.i("AIRBOOKS >= 61000:---> ", "" + (46.0f - (diff * 0.05f)));
            }
        })
    }

    private fun takeYPosition(view: View) {
        val xy = IntArray(2)
        view.getLocationOnScreen(xy)
        yHomePosition = xy[1].toFloat()
        FadeFragment.yPosition = xy[1]
        Log.i("CUSTOMNESTED Y: ", "$yHomePosition")
    }

    companion object {
        var yHomePosition: Float = 0.toFloat()
    }
}

