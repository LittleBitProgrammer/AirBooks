package it.corelab.airbooks.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import it.corelab.airbooks.R;

public class AddSection extends AppCompatActivity {

    private ImageView centralCard;
    private ImageButton returnButton;
    private Button nextButton;
    private TextInputLayout textInputLayout;
    private EditText editText;
    private TextInputEditText editTextInput;
    public static final int PICK_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_section);

        final Intent categoriesIntent = new Intent(getApplicationContext(),Categories.class);

        centralCard = findViewById(R.id.placeholder_add);
        returnButton = findViewById(R.id.left_arrow_add_categories);
        nextButton = findViewById(R.id.color_button_next);
        editTextInput = findViewById(R.id.edit_text);
        editText = findViewById(R.id.edit_text3);
        textInputLayout = findViewById(R.id.text_input_layout);

        editText.setEnabled(false);

        textInputLayout.setHint("Insert your title here");

        final Intent returnButtonIntent = new Intent(AddSection.this, MainActivity.class);




        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        View.OnClickListener centralCardListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
            }
        };

        View.OnClickListener nextCategoriesListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesIntent.setFlags(categoriesIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(categoriesIntent);
            }
        };

        View.OnClickListener returnButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButtonIntent.setFlags(returnButtonIntent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(returnButtonIntent);
                overridePendingTransition(R.anim.intent_from_top_in, R.anim.intent_from_top_out);
            }
        };

        centralCard.setOnClickListener(centralCardListener);
        returnButton.setOnClickListener(returnButtonListener);
        nextButton.setOnClickListener(nextCategoriesListener);

        final View parentReturn = (View) returnButton.getParent();  // button: the view you want to enlarge hit area
        parentReturn.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                returnButton.getHitRect(rect);
                rect.top -= 200;    // increase top hit area
                rect.left -= 200;   // increase left hit area
                rect.bottom += 200; // increase bottom hit area
                rect.right += 200;  // increase right hit area
                parentReturn.setTouchDelegate( new TouchDelegate( rect , returnButton));
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
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        View v = getCurrentFocus();
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            editTextInput.postDelayed(new Runnable() {

                @Override
                public void run() {
                    editTextInput.clearFocus();
                }

            }, 100);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.intent_from_top_in, R.anim.intent_from_top_out);
        Toast.makeText(getApplicationContext(),"prova",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

            // Do something with the bitmap
            centralCard.setImageURI(pickedImage);


            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }
}
