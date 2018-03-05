package it.corelab.airbooks.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import it.corelab.airbooks.R;

public class AddDescription extends AppCompatActivity {

    private ImageButton leftArrow;
    private ImageButton dismiss;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        editText = findViewById(R.id.editText_description);
        textView = findViewById(R.id.text_counter);

        final Intent returnIntent = new Intent(getApplicationContext(),Categories.class);
        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);

        leftArrow = findViewById(R.id.left_arrow_add_description);
        dismiss = findViewById(R.id.dismiss_button_description);

        final TextWatcher txwatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView.setText( "Character left " + String.valueOf(600 - s.length()));
            }

            public void afterTextChanged(Editable s) {
            }
        };

        editText.addTextChangedListener(txwatcher);

        editText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                isKeyboardShown(editText.getRootView());
            }
        });


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        View.OnClickListener returnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnIntent.setFlags(returnIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(returnIntent);
                overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
            }
        };

        View.OnClickListener dismissListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissIntent.setFlags(dismissIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(dismissIntent);
                overridePendingTransition(R.anim.intent_from_top_in, R.anim.intent_from_top_out);
            }
        };

        leftArrow.setOnClickListener(returnListener);
        dismiss.setOnClickListener(dismissListner);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                if (!isPointInsideView(event.getRawX(), event.getRawY(), v)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    /**
     * Determines if given points are inside view
     * @param x - x coordinate of point
     * @param y - y coordinate of point
     * @param view - view object to compare
     * @return true if the points are within view bounds, false otherwise
     */
    private boolean isPointInsideView(float x, float y, View view) {
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        // point is inside view bounds
        return ((x > viewX && x < (viewX + view.getWidth())) &&
                (y > viewY && y < (viewY + view.getHeight())));
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        View v = getCurrentFocus();
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
            editText.clearFocus();
        }
        return super.onKeyUp(keyCode, event);
    }

    private boolean isKeyboardShown(View rootView) {
    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
        final int SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;

       View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
        int heightDiff = rootView.getBottom() - r.bottom;
    /* Threshold size: dp to pixels, multiply with display density */
        boolean isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density;

        Log.d("tastiera", "isKeyboardShown ? " + isKeyboardShown + ", heightDiff:" + heightDiff + ", density:" + dm.density
                + "root view height:" + rootView.getHeight() + ", rect:" + r);

        if (isKeyboardShown){
            view.animate().y(-200);
        }else {
            view.animate().y(0);
        }

        return isKeyboardShown;
    }
}
