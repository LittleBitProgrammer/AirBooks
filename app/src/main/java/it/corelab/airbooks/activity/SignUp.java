package it.corelab.airbooks.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import it.corelab.airbooks.R;

public class SignUp extends AppCompatActivity {

    private TextInputEditText textInputEditText;
    private TextInputEditText mail;
    public static String mailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Intent nationIntent = new Intent(this, Countries.class);

        textInputEditText = findViewById(R.id.password_edit_nation);
        mail = findViewById(R.id.password_edit_email);

        if (getIntent().getExtras() != null) {
            Log.i("NATION", getIntent().getStringExtra("nation"));
            textInputEditText.setText(getIntent().getStringExtra("nation"));
        }

        if (mailText != null){
            mail.setText(mailText);
            mailText = mail.getText().toString();
        }

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        textInputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(nationIntent);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof TextInputEditText) {
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
}
