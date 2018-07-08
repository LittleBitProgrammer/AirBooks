package it.corelab.airbooks;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.widget.ImageView;

public class Utils {

    /**
     * This method help easily to animate an imageView on X axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView image that you want to animate
     * @param xPosition initial X position of the imageView
     * @param finalXPosition final X position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateIntroX(ImageView imageView, Float xPosition, Float finalXPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setX(xPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sX = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, xPosition, finalXPosition);
        sX.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sX);
        anims.setInterpolator(interpolator);

        anims.start();
    }

    /**
     * This method help easily to animate an imageView on y axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView image that you want to animate
     * @param yPosition initial Y position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateIntro(ImageView imageView,  Float yPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setY(yPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sY = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, yPosition, finalYPosition);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sY);
        anims.setInterpolator(interpolator);

        anims.start();
    }

    /**
     *  This method help easily to animate an imageView on x/y axis, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView image that you want to animate
     * @param xPosition initial X position of the imageView
     * @param yPosition initial Y position of the imageView
     * @param finalXPosition final X position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateIntro(ImageView imageView, Float xPosition, Float yPosition, Float finalXPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setX(xPosition);
        imageView.setY(yPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sX = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, xPosition, finalXPosition);
        ObjectAnimator sY = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, yPosition, finalYPosition);
        sX.setStartDelay(delay);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.playTogether(sX, sY);
        anims.setInterpolator(interpolator);

        anims.start();
    }
}
