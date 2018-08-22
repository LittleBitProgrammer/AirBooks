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

    fun animateToActionBar(diagonalView: DiagonalView?){
        diagonalView?.animate()?.translationY(-900F)?.interpolator = DecelerateInterpolator()

        ValueAnimator.ofFloat(diagonalView?.getAngle()!!,0F).apply {
            addUpdateListener {
                diagonalView.setAngle(animatedValue as Float)
            duration = 500
            interpolator = LinearOutSlowInInterpolator()}
        }.start()
    }
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
        val startAngle = 14F
        diagonalView.setAngle(startAngle)

        setOnScrollChangeListener(OnScrollChangeListener { diagonal, _, scrollY, _, _ ->
            val scrollAmount = diagonal.height + diagonal.scrollY - diagonal.bottom
            val rightMaxScroll = 585F
            val leftMaxScroll = 855F
            val pHeight: Float = leftMaxScroll - rightMaxScroll
            val diff:Float = scrollAmount - rightMaxScroll

            var angle = startAngle

            lastYPosition = -scrollY.toFloat()

            if (diff in 0..pHeight.toInt()){
                angle = startAngle * (1 - diff / pHeight)
            } else if (diff > pHeight) {
                angle  = 0F
            }

            diagonalView.setAngle(angle)
            diagonalView.y = -scrollY.toFloat()
            lastAngleVariation = angle

            /*
            var variation = 43.25f - scrollAmount * 0.05f

            if (scrollAmount <= 585) {

                diagonalView.setAngle(14F)
                diagonalView.y = -scrollY.toFloat()

                variation = 14F
                lastAngleVariation = variation

            } else if (scrollAmount <= 906 && variation <= 14.0f) {

                diagonalView.setAngle(variation)
                diagonalView.y = -scrollY.toFloat()
                lastAngleVariation = variation

            }else{
                diagonalView.y = -scrollY.toFloat()
            }
            */
        })
    }

    private fun takeYPosition(view: View) {
        val xy = IntArray(2)
        view.getLocationOnScreen(xy)
        lastYPosition = xy[1].toFloat()
    }

}

