package it.corelab.airbooks.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

import it.corelab.airbooks.R;
import it.corelab.airbooks.section.SignUp;
import it.corelab.airbooks.section.login.fragment.SignUpFragment;


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
        final SignUpFragment sign = new SignUpFragment();

        final Intent adpterIntent = new Intent(context, SignUp.class);

        final View rowView = inflater.inflate(R.layout.country_list_item, parent, false);
        final TextView textView = rowView.findViewById(R.id.txtViewCountryName);

        String[] g=values[position].split(",");
        textView.setText(GetCountryZipCode(g[1]).trim());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign.nation.setText(textView.getText().toString());
                Objects.requireNonNull(SignUpFragment.Companion.getCountryDialog()).dismiss();
            }
        });
        return rowView;
    }

    private String GetCountryZipCode(String ssid){
        Locale loc = new Locale("", ssid);

        return loc.getDisplayCountry().trim();
    }
}