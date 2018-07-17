package it.corelab.studios.airbooks.section;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import it.corelab.studios.airbooks.R;

public class SignUp extends AppCompatActivity {

    protected TextInputEditText textInputEditText;
    protected TextInputEditText mail;
    protected Button signUp;
    protected TextInputEditText password;
    protected TextInputEditText email;
    protected TextInputEditText nation;
    protected TextInputEditText surname;
    protected TextInputEditText namePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Intent nationIntent = new Intent(this, Countries.class);


        signUp = findViewById(R.id.button_signup);
        password = findViewById(R.id.password_edit_password);
        email = findViewById(R.id.password_edit_email);
        nation = findViewById(R.id.password_edit_nation);
        surname = findViewById(R.id.password_edit_surname);
        namePerson = findViewById(R.id.password_edit_name);

        if (getIntent().getExtras() != null) {
            Log.i("NATION", getIntent().getStringExtra("nation"));
            textInputEditText.setText(getIntent().getStringExtra("nation"));
        }


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditTextEmpty(password)){
                    password.setError("insert a password");
                }if (isEmailValid(getString(email)) || isEditTextEmpty(email)){
                    email.setError("you've to insert an email");
                }if (isEditTextEmpty(nation)){
                    nation.setError("please insert a nation");
                }if (isEditTextEmpty(surname)){
                    surname.setError("please insert your surname");
                }if (isEditTextEmpty(namePerson)){
                    namePerson.setError("please insert you name");
                }
            }
        });

        nation.setOnClickListener(new View.OnClickListener() {
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


    // utility

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
