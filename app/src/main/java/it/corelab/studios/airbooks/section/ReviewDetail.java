package it.corelab.studios.airbooks.section;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.widget.JustifyTextView;

public class ReviewDetail extends AppCompatActivity {

    private ImageView imageCover;
    private TextView title;
    private TextView author;
    private JustifyTextView review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);

        imageCover = findViewById(R.id.image_cover_shared);
        title = findViewById(R.id.Title_bookDetail);
        author = findViewById(R.id.author_bookDetail);
        review = findViewById(R.id.description_review_detail);

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        imageCover.setImageResource(extras.getInt("image"));
        title.setText(extras.getString("title"));
        author.setText(extras.getString("author"));
        review.setText(extras.getString("review"));

    }
}
