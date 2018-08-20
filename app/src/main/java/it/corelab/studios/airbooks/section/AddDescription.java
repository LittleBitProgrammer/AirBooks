package it.corelab.studios.airbooks.section;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.view.activity.main.MainActivity;

public class AddDescription extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private TextView filePathLabel;
    private static final int PICKFILE_RESULT_CODE = 1;
    private String fileNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        editText = findViewById(R.id.editText_description);
        textView = findViewById(R.id.text_counter);
        filePathLabel = findViewById(R.id.filePath);
        Button nextButton = findViewById(R.id.color_button_next_add_book);


        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);
        final Intent nextIntent = new Intent(getApplicationContext(),AddRecap.class);

        ImageButton leftArrow = findViewById(R.id.left_arrow_add_description);
        final ImageButton dismiss = findViewById(R.id.dismiss_button_description);
        ImageButton uploadButton = findViewById(R.id.upload_arrow_id);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        assert extra != null;
        final String uri = extra.getString("image");
        final int drawable = extra.getInt("categories");
        final String genreName = extra.getString("nameCat");
        final String title = extra.getString("bookTitle");

        final TextWatcher txwatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                textView.setText( "Character left " + String.valueOf(600 - s.length()));
            }

            public void afterTextChanged(Editable s) {
            }
        };

        uploadButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
            intent1.setType("*/*");
            startActivityForResult(intent1,PICKFILE_RESULT_CODE);
        });

        nextButton.setOnClickListener(v -> {
            String description = editText.getText().toString().trim();

            nextIntent.putExtra("image",uri);
            nextIntent.putExtra("categories",drawable);
            nextIntent.putExtra("nameCat",genreName);
            nextIntent.putExtra("description", description);
            nextIntent.putExtra("bookTitle", title);
            nextIntent.putExtra("path", fileNameString);
            Toast.makeText(getApplicationContext(),description,Toast.LENGTH_LONG).show();
            nextIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(nextIntent);
        });

        editText.addTextChangedListener(txwatcher);

        editText.getViewTreeObserver().addOnGlobalLayoutListener(() -> isKeyboardShown(editText.getRootView()));


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        View.OnClickListener returnListener = v -> onBackPressed();

        View.OnClickListener dismissListner = v -> {
            dismissIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(dismissIntent);
            overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
        };

        leftArrow.setOnClickListener(returnListener);
        dismiss.setOnClickListener(dismissListner);

        final View parentDismiss = (View) dismiss.getParent();  // button: the view you want to enlarge hit area
        parentDismiss.post(() -> {
            final Rect rect = new Rect();
            dismiss.getHitRect(rect);
            rect.top -= 150;    // increase top hit area
            rect.left -= 150;   // increase left hit area
            rect.bottom += 100; // increase bottom hit area
            rect.right += 150;  // increase right hit area
            parentDismiss.setTouchDelegate( new TouchDelegate( rect , dismiss));
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case PICKFILE_RESULT_CODE:
                if (resultCode==RESULT_OK){
                    Uri uri = data.getData();
                    assert uri != null;
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    String path = myFile.getAbsolutePath();
                    String displayName;

                    if (uriString.startsWith("content://")){
                        Cursor cursor = null;
                        try{
                            cursor = this.getContentResolver().query(uri, null, null, null, null);
                            if (cursor != null && cursor.moveToFirst()){
                                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                                fileNameString = displayName;
                                filePathLabel.setText(displayName);
                                Log.e("upload file with name: ", displayName );
                            }
                        }finally {
                            assert cursor != null;
                            cursor.close();
                        }
                    }else if (uriString.startsWith("file://")){
                        displayName = myFile.getName();
                        fileNameString = displayName;
                        filePathLabel.setText(displayName);
                        Log.e("upload file with name: ", displayName );
                    }
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
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

    @SuppressLint("LogConditional")
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

        Log.i("tastiera", "isKeyboardShown ? " + isKeyboardShown + ", heightDiff:" + heightDiff + ", density:" + dm.density
                + "root view height:" + rootView.getHeight() + ", rect:" + r);

        if (isKeyboardShown){
            view.animate().y(-200);
        }else {
            view.animate().y(0);
        }

        return isKeyboardShown;
    }
}
