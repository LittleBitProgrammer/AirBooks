package it.corelab.airbooks;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import it.corelab.airbooks.adapters.CountriesListAdapter;

public class CountryDialog extends android.support.v4.app.DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.country_dialog, container,
                false);
        getDialog().setTitle("Choose your country");
        String[] recourseList=this.getResources().getStringArray(R.array.CountryCodes);
        final ListView listView = rootView.findViewById(R.id.list_dynamic);
        CountriesListAdapter countriesListAdapter = new CountriesListAdapter(getActivity(),recourseList);
        listView.setAdapter(countriesListAdapter);
        // Do something else
        return rootView;
    }
}
