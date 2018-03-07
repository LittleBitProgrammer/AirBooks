package it.corelab.airbooks.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.corelab.airbooks.MySpannable;
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
    private static double screenInches;

    private TextView tv;

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

        tv = (TextView) findViewById(R.id.description_book_detail);

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

        setRealDeviceSizeInPixels();


        if (screenInches >= 5){
            makeTextViewResizable(tv, 8, "View More", true);
        }


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

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() - 4) + " " + expandText;
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                }
                tv.setText(text);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                tv.setText(
                        addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                viewMore), TextView.BufferType.SPANNABLE);
            }
        });

    }
    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false){
                @Override
                public void onClick(View widget) {
                    Log.d("SHOWMORE", "testo di prova ahahahahahah");
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }


    //how big is your screen size?
    // this is the method

    private void setRealDeviceSizeInPixels() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);


        // since SDK_INT = 1;
        int mWidthPixels = displayMetrics.widthPixels;
        int mHeightPixels = displayMetrics.heightPixels;

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (Exception ignored) {
            }
        }

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(mWidthPixels/dm.xdpi,2);
        double y = Math.pow(mHeightPixels/dm.ydpi,2);
        screenInches = Math.sqrt(x+y);
        Log.d("SCREENSIZE","Screen inches : " + screenInches);
    }
}
