package it.corelab.studios.airbooks.section;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.adapters.CustomListViewAdapter;
import it.corelab.studios.airbooks.object.Reviews;

public class AllReviews extends AppCompatActivity {

    private ImageButton dismissButton;
    private TextView reviews;
    private ListView listView;

    private ArrayList<Reviews> listViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        dismissButton = findViewById(R.id.dismiss_button_all_reviews);
        reviews = findViewById(R.id.top_bar_name_all_reviews);
        listView = findViewById(R.id.listView_all_reviews);

        //createElementListView();

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this, R.layout.custom_listview_item_all_reviews, listViewItem );
        listView.setAdapter(customListViewAdapter);

        listView.setDrawingCacheEnabled(true);
        listView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        listView.buildDrawingCache();

        reviews.setText("REVIEWS (" + listView.getAdapter().getCount() +" )");
        reviews.setTextSize(18);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}
