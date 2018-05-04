package it.corelab.airbooks.activity;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.corelab.airbooks.R;
import it.corelab.airbooks.widget.RoundedImageView;

public class Login extends AppCompatActivity {

    private Button loginButton;
    private Button forgotButton;
    private Button facebookButton;
    private Button signUpButton;
    protected TextInputEditText email;
    protected TextInputEditText password;
    protected TextInputLayout passwordLayout;
    protected ImageView background;
    protected ImageView owl;
    protected ImageView editBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Intent signUpIntent = new Intent(this, SignUp.class);

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        loginButton = findViewById(R.id.button);
        forgotButton = findViewById(R.id.button2);
        facebookButton = findViewById(R.id.button4);
        signUpButton = findViewById(R.id.sign_up);
        email = findViewById(R.id.edit_text);
        password = findViewById(R.id.password_edit_password);
        passwordLayout = findViewById(R.id.text_input_password);
        background = findViewById(R.id.owlBackground);
        owl = findViewById(R.id.imageView3);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid(getString(email))){
                    email.setError("you've to insert an email");
                }
                if(isEditTextEmpty(password)){
                    //passwordLayout.setPasswordVisibilityToggleEnabled(false);
                    password.setError("Please insert a password");
                }

                Log.i("BUTTON PRESSED", "login button pressed");
            }
        });

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON PRESSED", "forgot password button pressed");
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON PRESSED", "facebook button pressed");
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON PRESSED", "sign up button pressed");
                signUpIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(signUpIntent);
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getString(TextInputEditText textInputEditText){
        String stringa = textInputEditText.getText().toString();
        return stringa;
    }

    public boolean isEditTextEmpty(EditText editText){
        return  editText.length() == 0;
    }
}
