package it.corelab.airbooks;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.widget.ImageView;

public class Utils {

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
