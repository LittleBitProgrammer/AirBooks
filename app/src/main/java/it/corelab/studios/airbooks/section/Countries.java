package it.corelab.studios.airbooks.section;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.view.adapters.CountriesListAdapter;

public class Countries extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String[] recourseList=this.getResources().getStringArray(R.array.CountryCodes);

        final ListView listView = findViewById(R.id.listView);
        CountriesListAdapter countriesListAdapter = new CountriesListAdapter(this,recourseList);
        listView.setAdapter(countriesListAdapter);
    }
}
