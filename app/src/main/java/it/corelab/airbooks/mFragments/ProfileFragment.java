package it.corelab.airbooks.mFragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import it.corelab.airbooks.MainAdapter;
import it.corelab.airbooks.R;

/**
 * Created by Roberto_Vecchio on 02/02/18.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Drawable> mImageset;


    //=======================
    //  costructor required
    //=======================

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIstance){
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        Resources resources = getResources();

        Drawable allThis = resources.getDrawable(R.drawable.all_this);
        Drawable titan = resources.getDrawable(R.drawable.titan);
        Drawable spazio = resources.getDrawable(R.drawable.spazio);
        Drawable art = resources.getDrawable(R.drawable.art_bookcover);
        Drawable creative = resources.getDrawable(R.drawable.creative_bookcover);
        Drawable cupcake = resources.getDrawable(R.drawable.cupcake);
        Drawable fiore = resources.getDrawable(R.drawable.fiore);
        Drawable gelato = resources.getDrawable(R.drawable.gelato);
        Drawable lampadina = resources.getDrawable(R.drawable.lampadina);
        Drawable papera = resources.getDrawable(R.drawable.papera);

        mImageset = new ArrayList<>();

        mImageset.add(allThis);
        mImageset.add(titan);
        mImageset.add(spazio);
        mImageset.add(art);
        mImageset.add(creative);
        mImageset.add(cupcake);
        mImageset.add(fiore);
        mImageset.add(gelato);
        mImageset.add(lampadina);
        mImageset.add(papera);

        for (int i = 0; i <10 ; i++) {
            mImageset.get(i);
        }


        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainAdapter(mImageset);
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
