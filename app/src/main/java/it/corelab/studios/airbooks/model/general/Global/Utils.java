package it.corelab.studios.airbooks.model.general.Global;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Utils {

    /**
     * This method help easily to animate an imageView on X axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param xPosition initial X position of the imageView
     * @param finalXPosition final X position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImageX(final ImageView imageView, Float xPosition, Float finalXPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        imageView.setX(xPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sX = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, xPosition, finalXPosition);
        sX.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sX);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     * This method help easily to animate an imageView on X axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param finalXPosition final X position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImageX(final ImageView imageView, Float finalXPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sX = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, finalXPosition);
        sX.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sX);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     * This method help easily to animate an imageView on y axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param yPosition initial Y position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImageY(final ImageView imageView,  Float yPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        imageView.setY(yPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sY = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, yPosition, finalYPosition);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sY);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     * This method help easily to animate an imageView on y axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImageY(final ImageView imageView, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sY = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, finalYPosition);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sY);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     *  This method help easily to animate an imageView on x/y axis, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param xPosition initial X position of the imageView
     * @param yPosition initial Y position of the imageView
     * @param finalXPosition final X position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImage(final ImageView imageView, Float xPosition, Float yPosition, Float finalXPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

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

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     *  This method help easily to animate an imageView on x/y axis, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param imageView roundedImage that you want to animate
     * @param finalXPosition final X position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     */
    public static void animateImage(final ImageView imageView, Float finalXPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        imageView.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sX = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, finalXPosition);
        ObjectAnimator sY = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, finalYPosition);
        sX.setStartDelay(delay);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.playTogether(sX, sY);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                imageView.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     *
     * This method help easily to animate a TextView on y axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param text text that you want to animate
     * @param yPosition initial Y position of the imageView
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     *
     */
    public static void animateTextY(final TextView text, Float yPosition, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        text.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        text.setY(yPosition);
        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sY = ObjectAnimator.ofFloat(text, View.TRANSLATION_Y, yPosition, finalYPosition);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sY);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                text.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }
    /**
     *
     * This method help easily to animate a TextView on y axi, you can also set a duration, delay and the interpolator with a tension value
     * @author Roberto Vecchio
     * @param text text that you want to animate
     * @param finalYPosition final Y position of the imageView
     * @param delay if you want to put a delay of time on the animation start
     * @param duration duration of the animation
     * @param interpolator type of interpolator
     *
     */
    public static void animateTextY(final TextView text, Float finalYPosition, int delay, int duration, TimeInterpolator interpolator){

        text.setLayerType(View.LAYER_TYPE_HARDWARE,null);

        AnimatorSet anims = new AnimatorSet();
        ObjectAnimator sY = ObjectAnimator.ofFloat(text, View.TRANSLATION_Y, finalYPosition);
        sY.setStartDelay(delay);
        anims.setDuration(duration);
        anims.play(sY);
        anims.setInterpolator(interpolator);

        anims.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                text.setLayerType(View.LAYER_TYPE_NONE,null);
            }
        });

        anims.start();
    }

    /**
     * This method help to easily set the imageView on the X/Y axis
     * @author Roberto Vecchio
     * @param imageView ImageView tha you want to change the position
     * @param x position on the X value
     * @param y position on the Y value
     */
    public static void setXY(ImageView imageView, Float x, Float y){
        imageView.setY(y);
        imageView.setX(x);
    }

    /**
     * This method help to easily set the TextView on the X/Y axis
     * @author Roberto Vecchio
     * @param textView TextView tha you want to change the position
     * @param x position on the X value
     * @param y position on the Y value
     */
    public static void setXY(TextView textView, Float x, Float y){
        textView.setY(y);
        textView.setX(x);
    }
}