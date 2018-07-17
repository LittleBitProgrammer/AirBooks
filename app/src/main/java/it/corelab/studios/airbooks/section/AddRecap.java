package it.corelab.studios.airbooks.section;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.section.navigation.activity.MainActivity;

public class AddRecap extends AppCompatActivity {

    private ImageButton dismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recap);

        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);
        final Intent addIntent = new Intent(getApplicationContext(),MainActivity.class);

        dismiss = findViewById(R.id.dismiss_button_recap);
        ImageButton leftArrow = findViewById(R.id.left_arrow_add_recap);
        Button addButton = findViewById(R.id.color_button_next_add_book_recap);
        ImageView cover = findViewById(R.id.cover_recap);
        ImageView genreDrawable = findViewById(R.id.genre_recap);
        TextView genreNameLabel = findViewById(R.id.genreName);
        TextView recapDescription = findViewById(R.id.recap_description);
        TextView titleRecap = findViewById(R.id.title_recap);
        TextView filePath = findViewById(R.id.file_path_recap);

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        assert extra != null;
        int drawable = extra.getInt("categories");
        String genreName = extra.getString("nameCat");
        String uriString = extra.getString("image");
        String desciption = extra.getString("description");
        String title = extra.getString("title");
        String path = extra.getString("path");
        Uri uri = Uri.parse(uriString);
        cover.setImageURI(uri);
        genreDrawable.setImageResource(drawable);
        genreNameLabel.setText(genreName);
        recapDescription.setText(desciption);
        titleRecap.setText(title);
        filePath.setText(path);


        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(dismissIntent);
                overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(addIntent);
                Toast.makeText(getApplicationContext(),"Book added",Toast.LENGTH_LONG).show();
            }
        });

        final View parentDismiss = (View) dismiss.getParent();  // button: the view you want to enlarge hit area
        parentDismiss.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                dismiss.getHitRect(rect);
                rect.top -= 150;    // increase top hit area
                rect.left -= 150;   // increase left hit area
                rect.bottom += 100; // increase bottom hit area
                rect.right += 150;  // increase right hit area
                parentDismiss.setTouchDelegate( new TouchDelegate( rect , dismiss));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent returnIntent = new Intent(getApplicationContext(),AddDescription.class);
        returnIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(returnIntent);
        overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
    }
}
