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
               
               int diff = ( (v.getHeight() + v.getScrollY()) - v.getBottom());
               //Log.i("AIRBOOKS >= 600:-----> ", "" + diff);
               if (diff <= 600){
                   FadeFragment.diagonalView.setAngle(16.0f);
               }else if ( diff <= 900 && (46.0f - (diff * 0.05f)) <= 16.0f){
                   //topBar.animate().scaleY(0.8f);
                   FadeFragment.diagonalView.setAngle(46.0f - (diff * 0.05f));
                  // Log.i("AIRBOOKS >= 61000:---> ", "" + (46.0f - (diff * 0.05f)));
               }
           }
       });
   }
}

