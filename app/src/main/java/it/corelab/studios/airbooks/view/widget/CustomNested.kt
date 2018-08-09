package it.corelab.studios.airbooks.view.widget

import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.view.animation.FastOutLinearInInterpolator
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator


class CustomNested : NestedScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var lastYPosition = 0F
    private var lastAngleVariation = 14F

    fun animateToOriginal(diagonalView: DiagonalView){
        diagonalView.animate().translationY(0F).interpolator = DecelerateInterpolator()

        ValueAnimator.ofFloat(diagonalView.getAngle(),14F).apply {
            addUpdateListener { animation -> diagonalView.setAngle(animation.animatedValue as Float)
                duration = 500
                interpolator = LinearOutSlowInInterpolator() }
        }.start()
    }

    fun animateDiagonal(diagonalView: DiagonalView){

        diagonalView.animate().translationY(lastYPosition).interpolator = DecelerateInterpolator()

        if (lastAngleVariation < 14){
            ValueAnimator.ofFloat(diagonalView.getAngle(),lastAngleVariation).apply {
                addUpdateListener { animation -> diagonalView.setAngle(animation.animatedValue as Float)
                    duration = 500
                    interpolator = LinearOutSlowInInterpolator() }
            }.start()
        }else {

            ValueAnimator.ofFloat(diagonalView.getAngle(), lastAngleVariation).apply {
                addUpdateListener { animation ->
                    diagonalView.setAngle(animation.animatedValue as Float)
                    duration = 600
                    interpolator = FastOutLinearInInterpolator()
                }
            }.start()

        }

    }

    fun takeScrollVariation(diagonalView: DiagonalView) {
        diagonalView.setAngle(14F)

        setOnScrollChangeListener(OnScrollChangeListener { diagonal, _, scrollY, _, _ ->
            val diff = diagonal.height + diagonal.scrollY - diagonal.bottom

            var variation = 43.25f - diff * 0.05f

            lastYPosition = -scrollY.toFloat()
            //takeYPosition(diagonalView)

            if (diff <= 585) {

                diagonalView.setAngle(14F)
                diagonalView.y = -scrollY.toFloat()
                variation = 14F
                lastAngleVariation = variation

            } else if (diff <= 906 && variation <= 14.0f) {

                diagonalView.setAngle(variation)
                diagonalView.y = -scrollY.toFloat()
                lastAngleVariation = variation
                Log.i("ANGLE", "$lastAngleVariation")

            }else{
                diagonalView.y = -scrollY.toFloat()
            }
        })
    }

    private fun takeYPosition(view: View) {
        val xy = IntArray(2)
        view.getLocationOnScreen(xy)
        lastYPosition = xy[1].toFloat()
    }

}

