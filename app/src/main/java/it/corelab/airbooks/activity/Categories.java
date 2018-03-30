package it.corelab.airbooks.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.TouchDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.adapters.CategoriesAddRv;
import it.corelab.airbooks.object.Item;

public class Categories extends AppCompatActivity {

    private ImageButton leftArrow;
    private ImageButton dismiss;
    private ArrayList<Item> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final Intent dismissIntent = new Intent(getApplicationContext(),MainActivity.class);

        createCategioriesRvAdd();

        leftArrow = findViewById(R.id.left_arrow_add_categories);
        dismiss = findViewById(R.id.dismiss_button);

        RecyclerView categoriesAddRv = findViewById(R.id.categories_add_rv);

        categoriesAddRv.setItemViewCacheSize(20);
        categoriesAddRv.setDrawingCacheEnabled(true);
        categoriesAddRv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
        categoriesAddRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        categoriesAddRv.setHasFixedSize(true);

        CategoriesAddRv categoriesAdapter = new CategoriesAddRv(getApplicationContext(), categories);
        categoriesAddRv.setAdapter(categoriesAdapter);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        final View parentReturn = (View) leftArrow.getParent();  // button: the view you want to enlarge hit area
        parentReturn.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                leftArrow.getHitRect(rect);
                rect.top -= 200;    // increase top hit area
                rect.left -= 200;   // increase left hit area
                rect.bottom += 200; // increase bottom hit area
                rect.right += 200;  // increase right hit area
                parentReturn.setTouchDelegate( new TouchDelegate( rect , leftArrow));
            }
        });

        final View parentDismiss = (View) dismiss.getParent();  // button: the view you want to enlarge hit area
        parentReturn.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                dismiss.getHitRect(rect);
                rect.top -= 200;    // increase top hit area
                rect.left -= 200;   // increase left hit area
                rect.bottom += 200; // increase bottom hit area
                rect.right += 200;  // increase right hit area
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

    public void createCategioriesRvAdd(){

        categories = new ArrayList<>();

        categories.add(new Item(R.drawable.sci_fi, "Sci-fi"));
        categories.add(new Item(R.drawable.for_children, "Adolescenti e Ragazzi"));
        categories.add(new Item(R.drawable.biografy, "Biografia"));
        categories.add(new Item(R.drawable.comics_manga, "Fumetti e Manga"));
        categories.add(new Item(R.drawable.yellow_thriller, "Gialli e Thriller"));
        categories.add(new Item(R.drawable.fantasy, "humor"));
        categories.add(new Item(R.drawable.manual, "Manuali"));
        categories.add(new Item(R.drawable.fantasy, "libri per bambini"));
        categories.add(new Item(R.drawable.fantasy, "religione"));
        categories.add(new Item(R.drawable.self_help, "Self help"));
        categories.add(new Item(R.drawable.fantasy, "sport"));
        categories.add(new Item(R.drawable.fantasy, "storico"));
        categories.add(new Item(R.drawable.fantasy, "avventura"));
        categories.add(new Item(R.drawable.fantasy, "azione"));
        categories.add(new Item(R.drawable.fantasy, "fan fiction"));
        categories.add(new Item(R.drawable.fantasy, "fantasy"));
        categories.add(new Item(R.drawable.fantasy, "horror"));
        categories.add(new Item(R.drawable.fantasy, "poesia"));
        categories.add(new Item(R.drawable.wise, "saggistica"));
        categories.add(new Item(R.drawable.fantasy, "teen fiction"));
        categories.add(new Item(R.drawable.erotic, "Erotica"));
        categories.add(new Item(R.drawable.fantasy, "letteratura gastonomica"));
        categories.add(new Item(R.drawable.fantasy, "drammatico"));
        categories.add(new Item(R.drawable.fantasy, "sentimentale"));
        categories.add(new Item(R.drawable.fantasy, "altro"));
    }
}
