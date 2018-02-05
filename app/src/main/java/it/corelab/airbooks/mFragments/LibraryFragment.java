package it.corelab.airbooks.mFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import it.corelab.airbooks.R;

/**
 * Created by Roberto_Vecchio on 02/02/18.
 */

public class LibraryFragment extends android.support.v4.app.Fragment {


    //=======================
    //  costructor required
    //=======================

    public LibraryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstance){
        View rootView = inflater.inflate(R.layout.library_fragment, container, false);
        return rootView;
    }
}
