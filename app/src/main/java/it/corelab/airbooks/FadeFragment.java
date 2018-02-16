package it.corelab.airbooks;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import it.corelab.airbooks.adapters.CardViewReviewAdapter;
import it.corelab.airbooks.adapters.GravitySnapHelper;
import it.corelab.airbooks.adapters.SnapExploreRecyclerAdapter;
import it.corelab.airbooks.adapters.SnapRecyclerAdapter;
import it.corelab.airbooks.adapters.SnapShowcaseRecyclerAdapter;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Showcase;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class FadeFragment extends Fragment {
   private FrameLayout fragmentContainer;
   private RecyclerView recyclerView;
   private RecyclerView cardReviewRecycleView;
   private RecyclerView recyclerCardShowcase;
   private LinearLayoutManager linearLayoutManager;
   private SnappingRecyclerView recyclerCardExplore;
   private ArrayList<Item> items;
   private ArrayList<Item> reviewCard;
   private ArrayList<Item> exploreCardItem;
   private ArrayList<Showcase> showcaseCardItem;
   private Button buttonAction;
   private Timer timer;

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

   int cursor = 0;

   class AdvertisementTimerTask extends TimerTask{
       final int count;

       public AdvertisementTimerTask(int count){
           this.count = count;
       }

       @Override
       public void run(){
           if (cursor < count){
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       recyclerCardShowcase.smoothScrollToPosition(cursor);
                       cursor++;
                   }
               });
           }
           if(cursor >= count){
               cursor = 0;
           }
       }
   }

   public void onStop(){
       super.onStop();
       if (timer != null){
           timer.cancel();
       }
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflanter, ViewGroup container, Bundle savedInstanceState){
       if(getArguments().getInt("index", 0) == 0){
           View view= inflanter.inflate(R.layout.home_fragment, container, false);
           initHome(view);
           return view;
       }else if(getArguments().getInt("index",0) == 1 ){
           View view = inflanter.inflate(R.layout.explore_fragment, container, false);
           initExplore(view);
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
    home init
     */

    public void initHome(View view){

        createShowcaseCard();

        fragmentContainer = view.findViewById(R.id.fragment_container);

        recyclerCardShowcase = view.findViewById(R.id.rv_showcase);

        SnapShowcaseRecyclerAdapter adapterShowcase = new SnapShowcaseRecyclerAdapter(getActivity(), showcaseCardItem);
        recyclerCardShowcase.setAdapter(adapterShowcase);

        recyclerCardShowcase.setItemViewCacheSize(20);
        recyclerCardShowcase.setDrawingCacheEnabled(true);
        recyclerCardShowcase.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        timer = new Timer();
        timer.schedule(new AdvertisementTimerTask(recyclerCardShowcase.getAdapter().getItemCount()),0,4*1000);
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.CENTER_HORIZONTAL);
        snapHelper.attachToRecyclerView(recyclerView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCardShowcase.setLayoutManager(linearLayoutManager);
        recyclerCardShowcase.setHasFixedSize(true);

        recyclerCardShowcase.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    cursor = linearLayoutManager.findLastVisibleItemPosition();
                }
            }
        });
    }
    /*
    explore init
     */

    public void initExplore(View view){

        createExploreCard();

        fragmentContainer = view.findViewById(R.id.fragment_container);

        recyclerCardExplore = view.findViewById(R.id.recycler_view_explore);
        recyclerCardExplore.enableViewScaling(true);

        recyclerCardExplore.setItemViewCacheSize(20);
        recyclerCardExplore.setDrawingCacheEnabled(true);
        recyclerCardExplore.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        SnapExploreRecyclerAdapter adapter = new SnapExploreRecyclerAdapter(getActivity(), exploreCardItem);
        recyclerCardExplore.setAdapter(adapter);
        OverScrollDecoratorHelper.setUpOverScroll(recyclerCardExplore, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

    }

   /*
   profile init
    */

   public void initProfile(View view){
       createApps();
       createReviewCard();
       fragmentContainer = view.findViewById(R.id.fragment_container);

       buttonAction = view.findViewById(R.id.buttonAction);
       final View parent = (View) buttonAction.getParent();  // button: the view you want to enlarge hit area
       parent.post( new Runnable() {
           public void run() {
               final Rect rect = new Rect();
               buttonAction.getHitRect(rect);
               rect.top -= 100;    // increase top hit area
               rect.left -= 100;   // increase left hit area
               rect.bottom += 100; // increase bottom hit area
               rect.right += 100;  // increase right hit area
               parent.setTouchDelegate( new TouchDelegate( rect , buttonAction));
           }
       });

       buttonAction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu = new PopupMenu(getActivity(), buttonAction);
               popupMenu.getMenuInflater().inflate(R.menu.actions, popupMenu.getMenu());

               popupMenu.show();
           }
       });

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

    private void createExploreCard(){
        exploreCardItem = new ArrayList<>();

        exploreCardItem.add(new Item("All this", R.drawable.all_this, "Jojo Moyes", 93));
        exploreCardItem.add(new Item("Titan", R.drawable.titan, "Alessandro Baricco",25));
        exploreCardItem.add(new Item("Spazio", R.drawable.spazio, "Chiara Gamberale",801));
        exploreCardItem.add(new Item("Bookcover", R.drawable.art_bookcover, "Stephanie Meyer",1044));
        exploreCardItem.add(new Item("Creative", R.drawable.creative_bookcover, "Fabio Volo",528));
        exploreCardItem.add(new Item("Cupcake", R.drawable.cupcake, "Federico Moccia",19));
        exploreCardItem.add(new Item("fiore", R.drawable.fiore, "Dan Brown",10246));
        exploreCardItem.add(new Item("gelato", R.drawable.gelato, "Sam Pvnik", 94));
        exploreCardItem.add(new Item("Lampadina", R.drawable.lampadina, "George Orwell",621));
        exploreCardItem.add(new Item("Papera", R.drawable.papera,"Harper Lee",67));
    }

    private void createShowcaseCard(){
        showcaseCardItem = new ArrayList<>();

        showcaseCardItem.add(new Showcase(R.drawable.shota, "SHERLOCK"));
        showcaseCardItem.add(new Showcase(R.drawable.got, "GOT"));
        showcaseCardItem.add(new Showcase(R.drawable.stranger, "STRANGER"));
    }
}
