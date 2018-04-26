package it.corelab.airbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Locale;

import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.SignUp;

public class CountriesListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public CountriesListAdapter(Context context, String[] values) {
        super(context, R.layout.country_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Intent adpterIntent = new Intent(context, SignUp.class);

        final View rowView = inflater.inflate(R.layout.country_list_item, parent, false);
        final TextView textView = rowView.findViewById(R.id.txtViewCountryName);

        String[] g=values[position].split(",");
        textView.setText(GetCountryZipCode(g[1]).trim());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nation = textView.getText().toString();
                adpterIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                adpterIntent.putExtra("nation", nation);
                context.startActivity(adpterIntent);
                Log.i("NATION", nation);
            }
        });

        return rowView;
    }

    private String GetCountryZipCode(String ssid){
        Locale loc = new Locale("", ssid);

        return loc.getDisplayCountry().trim();
    }
}