package it.corelab.airbooks;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import it.corelab.airbooks.fragment.FadeFragment;

public class CustomNested extends NestedScrollView {
    private static final int INVALID_POINTER = -1;
    private int mLastMotionY;
    public static float yHomePosition;

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

               float variation = 46.0f - (diff * 0.05f);
               //Log.i("AIRBOOKS >= 600:-----> ", "" + diff);

               takeYPosition(FadeFragment.diagonalView);

               if (diff <= 600){
                   FadeFragment.diagonalView.setAngle(16.0f);
                   FadeFragment.angleVariation = 16.0f;
               }else if ( diff <= 900 && variation <= 16.0f){
                   //topBar.animate().scaleY(0.8f);
                   FadeFragment.diagonalView.setAngle(variation);
                   FadeFragment.angleVariation = variation;
                  // Log.i("AIRBOOKS >= 61000:---> ", "" + (46.0f - (diff * 0.05f)));
               }
           }
       });
   }

    public void takeYPosition(final View view) {
        int[] xy = new int[2];
        view.getLocationOnScreen(xy);
        FadeFragment.yPosition = xy[1];
    }
}

