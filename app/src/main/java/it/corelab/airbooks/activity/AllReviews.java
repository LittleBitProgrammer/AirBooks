package it.corelab.airbooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.adapters.CustomListViewAdapter;
import it.corelab.airbooks.object.Item;

public class AllReviews extends AppCompatActivity {

    private ImageButton dismissButton;
    private ListView listView;

    private ArrayList<Item> listViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        dismissButton = findViewById(R.id.dismiss_button_all_reviews);
        listView = findViewById(R.id.listView_all_reviews);

        createElementListView();

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this, R.layout.custom_listview_item_all_reviews, listViewItem );
        listView.setAdapter(customListViewAdapter);


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

    private void createElementListView(){

        listViewItem = new ArrayList<>();
        listViewItem.add(new Item( R.drawable.group_2));
        listViewItem.add(new Item( R.drawable.profile_2));
        listViewItem.add(new Item(R.drawable.group_2));
        listViewItem.add(new Item(R.drawable.profile_2));
        listViewItem.add(new Item(R.drawable.group_2));
        listViewItem.add(new Item(R.drawable.profile_2));
        listViewItem.add(new Item(R.drawable.group_2));
        listViewItem.add(new Item(R.drawable.profile_2));
        listViewItem.add(new Item(R.drawable.group_2));
        listViewItem.add(new Item(R.drawable.profile_2));
    }
}
