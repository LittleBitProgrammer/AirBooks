package it.corelab.airbooks;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import it.corelab.airbooks.fragment.FadeFragment;

public class CustomNested extends NestedScrollView {
    private static final int INVALID_POINTER = -1;
    private int mLastMotionY;

    public CustomNested(Context context) {
        super(context);
    }

    public CustomNested(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNested(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


   public void snap(){
       setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
           @Override
           public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

               FadeFragment.trapezoid.setPivotY(0);



               float scrollValue = oldScrollY - scrollY;

               //Log.i("AIRBOOKS > 500", ""+ (1.0f - (0.0010f * ( oldScrollY))));

               //ImageView topBar = FadeFragment.topBar;
               //topBar.setPivotY(0);
               if (oldScrollY >= 800){
                   //topBar.animate().scaleY(0.8f);
               }else {
                   //topBar.animate().scaleY(1.0f);
               }
               //if (oldScrollY >= 0.0 || oldScrollY <= 10.0){
                   //FadeFragment.trapezoid.setScaleY(1.0f);
               //}

               //if (oldScrollY > 500 && oldScrollY< 900) {

                   //Log.i("AIRBOOKS > 500", ""+ (1.5f - (0.0010f * ( oldScrollY))));
                   //FadeFragment.trapezoid.setScaleY(1.5f - (0.0010f * (oldScrollY)));
                   //((Animatable)FadeFragment.trapezoid.getDrawable()).start();
               //}

           }
       });
   }
}

