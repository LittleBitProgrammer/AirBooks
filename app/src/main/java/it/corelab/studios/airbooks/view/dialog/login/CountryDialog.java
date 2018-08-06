package it.corelab.studios.airbooks.view.dialog.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.view.adapters.CountriesListAdapter;

public class CountryDialog extends android.support.v4.app.DialogFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.country_dialog, container, false);

        getDialog().setTitle("Choose your country");

        String[] recourseList=this.getResources().getStringArray(R.array.CountryCodes);

        final ListView listView = rootView.findViewById(R.id.list_dynamic);

        CountriesListAdapter countriesListAdapter = new CountriesListAdapter(getActivity(),recourseList);
        listView.setAdapter(countriesListAdapter);

        return rootView;
    }
}
