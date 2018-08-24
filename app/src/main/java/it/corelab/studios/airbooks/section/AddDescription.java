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
    private static final int PICKFILE_RESULT_CODE = 2;
    private String fileNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_description);

        editText = findViewById(R.id.editText_description);
        textView = findViewById(R.id.text_counter);
        filePathLabel = findViewById(R.id.filePath);

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

        uploadButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
            intent1.setType("*/*");
            startActivityForResult(intent1,PICKFILE_RESULT_CODE);
        });


        View.OnClickListener returnListener = v -> onBackPressed();

        View.OnClickListener dismissListner = v -> {
            dismissIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(dismissIntent);
            overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
        };

        leftArrow.setOnClickListener(returnListener);
        dismiss.setOnClickListener(dismissListner);

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
                                Log.e("UPLOAD", "upload file with name: upload file with name: " + displayName );
                            }
                        }finally {
                            assert cursor != null;
                            cursor.close();
                        }
                    }else if (uriString.startsWith("file://")){
                        displayName = myFile.getName();
                        fileNameString = displayName;
                        filePathLabel.setText(displayName);
                        Log.e("UPLOAD", "upload file with name: upload file with name: " + displayName );
                    }
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }
}
