package it.corelab.studios.airbooks.section;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import it.corelab.studios.airbooks.view.dialog.CustomDialogClass;
import it.corelab.studios.airbooks.model.Gesture.GestureHelper;
import it.corelab.studios.airbooks.R;

public class BookDetail extends AppCompatActivity {

    private ImageView bookDetailCover;
    private ImageView bookDetailgenreColor;
    private TextView bookDetailTitle;
    private TextView bookDetailGenreLabel;
    private TextView bookDetailAuthor;
    private TextView numbLovers;
    private TextView numbReaders;
    private CardView bookDetailCardView;
    private CardView bookDetailCardviewGenre;
    private ImageButton leftArrow;
    private ImageButton star;
    private static double screenInches;
    boolean isSwipedCenter = true;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);


        bookDetailCover = findViewById(R.id.image_bestweek_shared);
        bookDetailTitle = findViewById(R.id.title_bookDetail);
        //bookDetailgenreColor = findViewById(R.id.genre_color_shared);
        bookDetailCardView = findViewById(R.id.cardView_shared);
        bookDetailCardviewGenre = findViewById(R.id.cardView_categories_shared);
        bookDetailGenreLabel = findViewById(R.id.text_genre_label);
        bookDetailAuthor = findViewById(R.id.author_bookDetail);
        //star = findViewById(R.id.reviews_button_bookDetail);
        numbLovers = findViewById(R.id.numb_lovers_book_detail);
        numbReaders = findViewById(R.id.numb_readers_book_detail);
        //backgroundBookDetail = findViewById(R.id.diagonal_view_bookDetail);

        tv = findViewById(R.id.description_book_detail);

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Picasso.get().load(extras.getString("pos")).into(bookDetailCover);
        bookDetailTitle.setText(extras.getString("bookTitle"));
        //bookDetailAuthor.setText(extras.getString("bookAuthor"));
        numbLovers.setText("" + extras.getInt("loversNumb"));
        numbReaders.setText("" + extras.getInt("readersNumb" ));

        final Intent starIntent = new Intent(BookDetail.this,AllReviews.class);


        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(starIntent);
            }
        });

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
        }else {makeTextViewResizable(tv, 7, "View More", true);
        }


        final int genreID = extras.getInt("genre");

        switch (genreID) {
            case 1:
                //backgroundBookDetail.setImageResource(R.drawable.for_children);
                bookDetailGenreLabel.setText("For children");
                break;
            case 2:
                //backgroundBookDetail.setImageResource(R.drawable.biografy);
                bookDetailGenreLabel.setText("Biografy");
                break;
            case 3:
                //backgroundBookDetail.setImageResource(R.drawable.erotic);
                bookDetailGenreLabel.setText("Erotic");
                break;
            case 4:
                //backgroundBookDetail.setImageResource(R.drawable.sci_fi);
                bookDetailGenreLabel.setText("Sci-fi");
                break;
            case 5:
                //backgroundBookDetail.setImageResource(R.drawable.comics_manga);
                bookDetailGenreLabel.setText("Comics");
                break;

            default:
                bookDetailGenreLabel.setText("No Genre");
        }


        bookDetailCardView.setOnTouchListener(new GestureHelper(BookDetail.this) {

            public void onSwipeTop() {
                swipeTopAnimation();
            }

            public void onSwipeBottom() {
                swipeDownAnimation();
            }
            public void onClick() {
                if (isSwipedCenter){
                    tapDownAnimation();
                }
                else if (!isSwipedCenter){
                    tapTopAnimation();
                }

            }
        });
    }

    public void swipeDownAnimation(){
        Log.d("swipe bookDetail", "swipe BOTTOM");
        bookDetailCardView.animate().translationY(20);
        bookDetailCardviewGenre.animate().translationY(-30);
        this.isSwipedCenter = false;
    }

    public void swipeTopAnimation(){
        Log.d("swipe bookDetail", "swipe BOTTOM");
        bookDetailCardView.animate().translationY(0);
        bookDetailCardviewGenre.animate().translationY(0);
        this.isSwipedCenter = true;
    }

    public void tapDownAnimation(){
        Log.d("swipe bookDetail", "click to bottom");
        bookDetailCardView.animate().translationY(20);
        bookDetailCardviewGenre.animate().translationY(-30);
        this.isSwipedCenter = false;
    }

    public void tapTopAnimation(){
        Log.d("swipe bookDetail", "click to center");
        bookDetailCardView.animate().translationY(0);
        bookDetailCardviewGenre.animate().translationY(0);
        this.isSwipedCenter = true;
    }

    public void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

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
    private  SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            /*
            ssb.setSpan(new MySpannable(false){
                CustomDialogClass cdd=new CustomDialogClass(BookDetail.this);
                @Override
                public void onClick(View widget) {
                    Log.d("SHOWMORE", "view more pressed");
                    cdd.show();
                    ImageButton dismissButton = cdd.findViewById(R.id.dismiss_button_popup);
                    dismissButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cdd.dismiss();
                        }
                    });
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);*/
        }
        return ssb;

    }


    //how big is your screen size?
    // this is the method

    @SuppressLint("LogConditional")
    private void setRealDeviceSizeInPixels() {
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);


        // since SDK_INT = 1;
        int mWidthPixels = displayMetrics.widthPixels;
        int mHeightPixels = displayMetrics.heightPixels;

        // includes window decorations (statusbar bar/menu bar)
        try {
            Point realSize = new Point();
            Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
            mWidthPixels = realSize.x;
            mHeightPixels = realSize.y;
        } catch (Exception ignored) {
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(mWidthPixels/dm.xdpi,2);
        double y = Math.pow(mHeightPixels/dm.ydpi,2);
        screenInches = Math.sqrt(x+y);
        Log.d("SCREEN SIZE","Screen inches : " + screenInches);
    }

    public void openDialog(CustomDialogClass customDialogClass){
        customDialogClass.show();
    }
}
