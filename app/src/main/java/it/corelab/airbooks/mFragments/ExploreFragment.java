package it.corelab.airbooks.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.corelab.airbooks.MainAdapter;
import it.corelab.airbooks.R;

/**
 * Created by Roberto_Vecchio on 02/02/18.
 */

public class ExploreFragment extends android.support.v4.app.Fragment{


    //=======================
    //  costructor required
    //=======================

    public ExploreFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstance){

        View rootView = inflater.inflate(R.layout.explore_fragment, container, false);
        return rootView;
    }
}
