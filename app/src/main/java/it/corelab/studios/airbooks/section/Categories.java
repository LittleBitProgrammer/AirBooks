package it.corelab.studios.airbooks.section;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.adapters.CategoriesAddRv;
import it.corelab.studios.airbooks.object.Item;
import it.corelab.studios.airbooks.section.navigation.activity.MainActivity;

public class Categories extends AppCompatActivity {

    private ImageButton leftArrow;
    private ImageButton dismiss;
    private ArrayList<Item> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);

        //createCategioriesRvAdd();

        leftArrow = findViewById(R.id.left_arrow_add_categories);
        dismiss = findViewById(R.id.dismiss_button);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        String uriString = extra.getString("image");
        String title = extra.getString("bookTitle");

        //take and set @URI image
       /* Uri uri = Uri.parse(extra.getString("image"));
        imageView.setImageURI(uri);*/

        RecyclerView categoriesAddRv = findViewById(R.id.categories_add_rv);

        categoriesAddRv.setItemViewCacheSize(20);
        categoriesAddRv.setDrawingCacheEnabled(true);
        categoriesAddRv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
        categoriesAddRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        categoriesAddRv.setHasFixedSize(true);

        CategoriesAddRv categoriesAdapter = new CategoriesAddRv(getApplicationContext(), categories, uriString, title);
        categoriesAddRv.setAdapter(categoriesAdapter);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ViewCompat.setNestedScrollingEnabled(categoriesAddRv,false);

        View.OnClickListener returnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        };

        View.OnClickListener dismissListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(dismissIntent);
                overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
            }
        };

        leftArrow.setOnClickListener(returnListener);
        dismiss.setOnClickListener(dismissListner);


        // range button

        final View parentDismiss = (View) dismiss.getParent();  // button: the view you want to enlarge hit area
        parentDismiss.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                dismiss.getHitRect(rect);
                rect.top -= 150;    // increase top hit area
                rect.left -= 150;   // increase left hit area
                rect.bottom += 150; // increase bottom hit area
                rect.right += 150;  // increase right hit area
                parentDismiss.setTouchDelegate( new TouchDelegate( rect , dismiss));
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Intent returnIntent = new Intent(getApplicationContext(),AddSection.class);
        returnIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(returnIntent);
        overridePendingTransition(R.anim.intent_from_right_in, R.anim.intent_from_right_out);
    }


}
