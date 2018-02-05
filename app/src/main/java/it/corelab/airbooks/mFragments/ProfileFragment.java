package it.corelab.airbooks.mFragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

import it.corelab.airbooks.GravitySnapHelper;
import it.corelab.airbooks.Item;
import it.corelab.airbooks.MainAdapter;
import it.corelab.airbooks.R;
import it.corelab.airbooks.SnapRecyclerAdapter;

/**
 * Created by Roberto_Vecchio on 02/02/18.
 */

public class ProfileFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Item> items;


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



        createApps();

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        SnapRecyclerAdapter adapter = new SnapRecyclerAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);


        return rootView;
    }
    private void createApps() {
        items = new ArrayList<>();
        items.add(new Item("Google+", R.drawable.all_this));
        items.add(new Item("Facebook", R.drawable.titan));
        items.add(new Item("LinkedIn", R.drawable.spazio));
        items.add(new Item("Youtube", R.drawable.art_bookcover));
        items.add(new Item("Instagram", R.drawable.creative_bookcover));
        items.add(new Item("Skype", R.drawable.cupcake));
        items.add(new Item("Twitter", R.drawable.fiore));
        items.add(new Item("Wikipedia", R.drawable.gelato));
        items.add(new Item("Whats app", R.drawable.lampadina));
        items.add(new Item("Pokemon Go", R.drawable.papera));
    }
}
