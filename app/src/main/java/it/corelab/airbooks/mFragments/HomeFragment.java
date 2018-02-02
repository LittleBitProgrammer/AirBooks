package it.corelab.airbooks.mFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.corelab.airbooks.R;

/**
 * Created by Roberto_Vecchio on 02/02/18.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstance){
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        return rootView;
    }
}
