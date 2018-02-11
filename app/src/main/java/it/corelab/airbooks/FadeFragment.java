package it.corelab.airbooks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import java.util.ArrayList;

import it.corelab.airbooks.adapters.CardViewReviewAdapter;
import it.corelab.airbooks.adapters.GravitySnapHelper;
import it.corelab.airbooks.adapters.SnapRecyclerAdapter;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class FadeFragment extends Fragment {
   private FrameLayout fragmentContainer;
   private RecyclerView recyclerView;
   private RecyclerView cardReviewRecycleView;
   private ArrayList<Item> items;
   private ArrayList<Item> reviewCard;

   /*
   create a new istance of the fragment
    */
   public static FadeFragment newInstance(int index){
       FadeFragment fragment = new FadeFragment();
       Bundle b = new Bundle();
       b.putInt("index", index);
       fragment.setArguments(b);
       return fragment;
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflanter, ViewGroup container, Bundle savedInstanceState){
       if(getArguments().getInt("index", 0) == 0){
           View view= inflanter.inflate(R.layout.home_fragment, container, false);
           return view;
       }else if(getArguments().getInt("index",0) == 1 ){
           View view = inflanter.inflate(R.layout.explore_fragment, container, false);
           return view;
       }else if(getArguments().getInt("index", 0) == 2){
           View view = inflanter.inflate(R.layout.library_fragment, container, false);
           return view;
       }else{
           View view = inflanter.inflate(R.layout.profile_fragment, container, false);
           initProfile(view);
           return view;
       }
   }

   /*
   Called when a fragment will be displayed
    */

   public void willBeDisplayed(){
       // Do what you want here, for example animate the content
       if(fragmentContainer!= null){
           Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
           fragmentContainer.startAnimation(fadeIn);
       }
   }

   /*
   called when a fragment will be hidden
    */

   public void willBeHidden(){
       if (fragmentContainer != null){
           Animation fadeOut= AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
           fragmentContainer.startAnimation(fadeOut);
       }
   }

   /*
   refresh
    */

    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

   /*
   profile init
    */

   public void initProfile(View view){
       createApps();
       createReviewCard();
       fragmentContainer = view.findViewById(R.id.fragment_container);
       recyclerView = view.findViewById(R.id.recycler_view);
       recyclerView.setHasFixedSize(true);

       recyclerView.setItemViewCacheSize(20);
       recyclerView.setDrawingCacheEnabled(true);
       recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
       
       SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
       snapHelper.attachToRecyclerView(recyclerView);

       // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       recyclerView.setHasFixedSize(true);

       SnapRecyclerAdapter adapter = new SnapRecyclerAdapter(getActivity(), items);
       recyclerView.setAdapter(adapter);
       OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

       //=================================================

       cardReviewRecycleView = view.findViewById(R.id.recycler_view_cardView);
       SnapHelper snapCardHelper = new GravitySnapHelper(Gravity.START);
       snapCardHelper.attachToRecyclerView(cardReviewRecycleView);

       cardReviewRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       cardReviewRecycleView.setHasFixedSize(true);

       CardViewReviewAdapter reviewAdapter = new CardViewReviewAdapter(getActivity(),reviewCard);
       cardReviewRecycleView.setAdapter(reviewAdapter);
       OverScrollDecoratorHelper.setUpOverScroll(cardReviewRecycleView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
   }
    private void createApps() {
        items = new ArrayList<>();

        items.add(new Item("AllThis", R.drawable.all_this));
        items.add(new Item("Titan", R.drawable.titan));
        items.add(new Item("Spazio", R.drawable.spazio));
        items.add(new Item("ArtBook", R.drawable.art_bookcover));
        items.add(new Item("Creative", R.drawable.creative_bookcover));
        items.add(new Item("Cupcake", R.drawable.cupcake));
        items.add(new Item("Fiore", R.drawable.fiore));
        items.add(new Item("Gelato", R.drawable.gelato));
        items.add(new Item("Lampadina", R.drawable.lampadina));
        items.add(new Item("Papera", R.drawable.papera));
}
    private void createReviewCard(){
        reviewCard = new ArrayList<>();
        reviewCard.add(new Item("All this", R.drawable.all_this));
        reviewCard.add(new Item("Titn", R.drawable.titan));
        reviewCard.add(new Item("Spazio", R.drawable.spazio));
        reviewCard.add(new Item("Bookcover", R.drawable.art_bookcover));
        reviewCard.add(new Item("Creative", R.drawable.creative_bookcover));
        reviewCard.add(new Item("Cupcake", R.drawable.cupcake));
        reviewCard.add(new Item("fiore", R.drawable.fiore));
        reviewCard.add(new Item("gelato", R.drawable.gelato));
        reviewCard.add(new Item("Lampadina", R.drawable.lampadina));
        reviewCard.add(new Item("Papera", R.drawable.papera));
    }
}
