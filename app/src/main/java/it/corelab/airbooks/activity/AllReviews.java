package it.corelab.airbooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import it.corelab.airbooks.R;
import it.corelab.airbooks.adapters.CustomListViewAdapter;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Review;

public class AllReviews extends AppCompatActivity {

    private ImageButton dismissButton;
    private TextView reviews;
    private ListView listView;

    private ArrayList<Review> listViewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        Review review = new Review();

        dismissButton = findViewById(R.id.dismiss_button_all_reviews);
        reviews = findViewById(R.id.top_bar_name_all_reviews);
        listView = findViewById(R.id.listView_all_reviews);

        createElementListView();

        CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this, R.layout.custom_listview_item_all_reviews, listViewItem );
        listView.setAdapter(customListViewAdapter);

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

    private void createElementListView(){

        listViewItem = new ArrayList<>();

        listViewItem.add(new Review(R.drawable.group_2, "Roberto Vecchio", 1, "This book is very fantastic, no words !!!!",5, false));
        listViewItem.add(new Review( R.drawable.profile_2,"Monica Sabolo", 2, "“Oltre l’inverno” è l’opera letteraria di un’autrice che, negli ultimi anni, comincia a scrivere ogni suo libro l’8 di Gennaio.\n" +
                "Si parla di una bufera di neve che coinvolge la città di Brooklyn e più nello specifico di tre persone le cui vite si intrecciano: Lucia Maraz, Richard Bowmaster e Evelyn Ortega",4, false));
        listViewItem.add(new Review(R.drawable.group_2,"Roberto Vecchio", 3, "Isabel Allende ci racconta una storia di amori, politica, dittature e fughe. Un romanzo ambientato in una Brooklyn paralizzata da una forte bufera di neve.", 3,false));
        listViewItem.add(new Review(R.drawable.profile_2,"Monica Sabolo", 7, "Tra tutti i libri di Allende che ho letto questo è quello che mi ha meno coinvolto, potrei definirlo il più ingenuo. Mi ha dato la sensazione di leggere una pagina di diario di una ragazzina degli anni '60",5, false));
        listViewItem.add(new Review(R.drawable.group_2,"Roberto Vecchio", 4, "buon libro scritto molto bene",3, false));
        listViewItem.add(new Review(R.drawable.profile_2,"Monica Sabolo", 8, "Libro particolare .....da' l'idea di un libro a spirale . Isabelle Allende descrive i personaggi e le situazioni da loro vissute in modo particolare ......",4, false));
        listViewItem.add(new Review(R.drawable.group_2,"Roberto Vecchio", 5, "Amo la Allende, ho letto quasi tutti i suoi libri. Questo romanzo l'ho letto nel giro di una settimana e devo dire che la storia scorre tranquillamente. ",4, false));
        listViewItem.add(new Review(R.drawable.profile_2,"Monica Sabolo", 9, "Ho letto alcuni libri di Isabel Allende a partire dal suo primo grande successo. La sua narrativa scorrevole e appetitosa mi piace e m'invita alla lettura", 3,false));
        listViewItem.add(new Review(R.drawable.group_2,"Roberto Vecchio", 6, "Bel libro, scorrevole emozionante anche se mi aspettavo di più da Isabel Allende.",2, false));
        listViewItem.add(new Review(R.drawable.profile_2,"Monica Sabolo", 6, "This book is very fantastic, no words !!!!", 5,false));

    }
}
