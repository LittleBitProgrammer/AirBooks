package it.corelab.airbooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.corelab.airbooks.OnSwipeTouchListener;
import it.corelab.airbooks.R;

public class BookDetail extends AppCompatActivity {

    private ImageView bookDetailCover;
    private ImageView bookDetailgenreColor;
    private TextView bookDetailTitle;
    private CardView bookDetailCardView;
    private CardView bookDetailCardviewGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookDetailCover = findViewById(R.id.image_bestweek_shared);
        bookDetailTitle = findViewById(R.id.Title_bookDetail);
        bookDetailgenreColor = findViewById(R.id.genre_color_shared);
        bookDetailCardView = findViewById(R.id.cardView_shared);
        bookDetailCardviewGenre = findViewById(R.id.cardView_categories_shared);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        bookDetailCover.setImageResource(extras.getInt("pos"));
        bookDetailTitle.setText(extras.getString("title"));
        bookDetailgenreColor.setBackgroundResource(extras.getInt("genre"));

        bookDetailCardView.setOnTouchListener(new OnSwipeTouchListener(BookDetail.this) {
            public void onSwipeTop() {
                Log.d("swipe bookDetail", "swipe TOP");
                bookDetailCardView.animate().translationY(0);
                bookDetailCardviewGenre.animate().translationY(0);
            }
            public void onSwipeBottom() {
                Log.d("swipe bookDetail", "swipe BOTTOM");
                bookDetailCardView.animate().translationY(20);
                bookDetailCardviewGenre.animate().translationY(-30);
            }
        });
    }
}
