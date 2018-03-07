package it.corelab.airbooks.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import it.corelab.airbooks.OnSwipeTouchListener;
import it.corelab.airbooks.R;

public class BookDetail extends AppCompatActivity {

    private ImageView bookDetailCover;
    private ImageView bookDetailgenreColor;
    private TextView bookDetailTitle;
    private TextView bookDetailGenreLabel;
    private TextView bookDetailAuthor;
    private CardView bookDetailCardView;
    private CardView bookDetailCardviewGenre;
    private ImageButton leftArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookDetailCover = findViewById(R.id.image_bestweek_shared);
        bookDetailTitle = findViewById(R.id.Title_bookDetail);
        bookDetailgenreColor = findViewById(R.id.genre_color_shared);
        bookDetailCardView = findViewById(R.id.cardView_shared);
        bookDetailCardviewGenre = findViewById(R.id.cardView_categories_shared);
        bookDetailGenreLabel = findViewById(R.id.text_genre_label);
        bookDetailAuthor = findViewById(R.id.author_bookDetail);
        leftArrow = findViewById(R.id.left_arrow_book_detail);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        bookDetailCover.setImageResource(extras.getInt("pos"));
        bookDetailTitle.setText(extras.getString("title"));
        bookDetailgenreColor.setBackgroundResource(extras.getInt("genre"));
        bookDetailAuthor.setText(extras.getString("author"));

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final View parentReturn = (View) leftArrow.getParent();  // button: the view you want to enlarge hit area
        parentReturn.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                leftArrow.getHitRect(rect);
                rect.top -= 100;    // increase top hit area
                rect.left -= 100;   // increase left hit area
                rect.bottom += 100; // increase bottom hit area
                rect.right += 100;  // increase right hit area
                parentReturn.setTouchDelegate( new TouchDelegate( rect , leftArrow));
            }
        });

        final int drawable = extras.getInt("genre");

        switch (drawable) {
            case R.drawable.for_children:
                bookDetailGenreLabel.setText("For children");
                break;
            case R.drawable.biografy:
                bookDetailGenreLabel.setText("Biografy");
                break;
            case R.drawable.erotic:
                bookDetailGenreLabel.setText("Erotic");
                break;
            case R.drawable.comics_manga:
                bookDetailGenreLabel.setText("Comics");
                break;
            case R.drawable.sci_fi:
                bookDetailGenreLabel.setText("Sci-fi");
                break;
            default:bookDetailGenreLabel.setText("No Genre");
        }


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
