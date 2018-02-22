package it.corelab.airbooks;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.ArrayList;

import it.corelab.airbooks.adapters.CardViewReviewAdapter;
import it.corelab.airbooks.adapters.GravitySnapHelper;
import it.corelab.airbooks.adapters.InfiniteRotationAdapter;
import it.corelab.airbooks.adapters.SnapBestOfWeek;
import it.corelab.airbooks.adapters.SnapCategoriesAdapter;
import it.corelab.airbooks.adapters.SnapContinueReadAdapter;
import it.corelab.airbooks.adapters.SnapExploreRecyclerAdapter;
import it.corelab.airbooks.adapters.SnapLibraryAdapter;
import it.corelab.airbooks.adapters.SnapRecyclerAdapter;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Showcase;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;


public class FadeFragment extends Fragment {

   private FrameLayout fragmentContainer;
   private RecyclerView recyclerView;
   private RecyclerView recyclerViewLibrary;
   private RecyclerView cardReviewRecycleView;
   private SnappingRecyclerView recyclerCardExplore;
   private ArrayList<Item> items;
   private ArrayList<Item> reviewCard;
   private ArrayList<Item> exploreCardItem;
   private ArrayList<Item> libraryCardItem;
   private ArrayList<Showcase> showcaseCardItem;
   private Button buttonAction;

   private InfiniteRotationView rotationView;

   private RecyclerView rvContinueRead;
   private RecyclerView rvCategories;
   private RecyclerView rvBestWeek;


    private ArrayList<Item> rvContinueReadItem;
    private ArrayList<Item> rvCategoriesItem;
    private ArrayList<Item> rvBestWeekItem;

    private ImageView add;
    private NestedScrollView nestedScrollView;


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
           View view = inflanter.inflate(R.layout.home_fragment, container, false);
           initHome(view);
           return view;
       }else if(getArguments().getInt("index",0) == 1 ){
           View view = inflanter.inflate(R.layout.explore_fragment, container, false);
           initExplore(view);
           return view;
       }else if(getArguments().getInt("index", 0) == 2){
           View view = inflanter.inflate(R.layout.library_fragment, container, false);
           initLibrary(view);
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

        final Intent intentAddSection = new Intent(getActivity(), AddSection.class);

        createShowcaseCard();
        createRvContinueReadItem();
        createRvCategoriesItem();
        createRvBestOfWeek();

        fragmentContainer = view.findViewById(R.id.fragment_container);

        rotationView = view.findViewById(R.id.rv_showcase);
        rvContinueRead = view.findViewById(R.id.rv_continue_reading);
        rvCategories = view.findViewById(R.id.rv_categories);
        rvBestWeek = view.findViewById(R.id.rv_bestweek);

        add = view.findViewById(R.id.add_button);
        nestedScrollView = view.findViewById(R.id.nested_home);


        rvBestWeek.setItemViewCacheSize(20);
        rvBestWeek.setDrawingCacheEnabled(true);
        rvBestWeek.setDrawingCacheQuality(view.DRAWING_CACHE_QUALITY_HIGH);

        rvContinueRead.setItemViewCacheSize(20);
        rvContinueRead.setDrawingCacheEnabled(true);
        rvContinueRead.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        rvCategories.setItemViewCacheSize(20);
        rvCategories.setDrawingCacheEnabled(true);
        rvCategories.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
        rvContinueRead.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false){});
        rvContinueRead.setHasFixedSize(true);

        // HORIZONTAL for Gravity START/END and VERTICAL for TOP/BOTTOM
        rvCategories.setLayoutManager(new GridLayoutManager(getActivity(), 2,GridLayout.HORIZONTAL,false));
        rvCategories.setHasFixedSize(true);

        //Horizontal for Gravity START/END and VERTICAL for TOP/BOTTOM
        rvBestWeek.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvBestWeek.setHasFixedSize(true);

        ViewCompat.setNestedScrollingEnabled(rotationView, false);
        ViewCompat.setNestedScrollingEnabled(rvContinueRead, false);
        ViewCompat.setNestedScrollingEnabled(rvCategories, false);
        ViewCompat.setNestedScrollingEnabled(rvBestWeek, false);

        SnapContinueReadAdapter snapContinueReadAdapter = new SnapContinueReadAdapter(getActivity(), rvContinueReadItem);
        rvContinueRead.setAdapter(snapContinueReadAdapter);

        SnapCategoriesAdapter snapCategoriesAdapter = new SnapCategoriesAdapter(getActivity(), rvCategoriesItem);
        rvCategories.setAdapter(snapCategoriesAdapter);

        SnapBestOfWeek snapBestOfWeek = new SnapBestOfWeek(getActivity(), rvBestWeekItem);
        rvBestWeek.setAdapter(snapBestOfWeek);


        rotationView.setAdapter(new InfiniteRotationAdapter(showcaseCardItem));
        rotationView.autoScroll(3, 3000);

        OverScrollDecoratorHelper.setUpOverScroll(rvContinueRead, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(rvCategories, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (nestedScrollView != null) {
                    if (nestedScrollView.getChildAt(0).getBottom() <= (nestedScrollView.getHeight() + nestedScrollView.getScrollY())) {
                        //scroll view is at bottom
                        MainActivity.bottomNavigation.restoreBottomNavigation(true);
                    } else {
                        //scroll view is not at bottom
                    }
                }
            }
        });

        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentAddSection);
                assert ((Activity)getContext()) != null;
                ((Activity)getContext()).overridePendingTransition(R.anim.intent_from_bottom_in, R.anim.intent_from_bottom_out);
            }
        };

        add.setOnClickListener(addListener);
    }

    /*
    explore init
     */

    public void initExplore(View view){

        createExploreCard();

        fragmentContainer = view.findViewById(R.id.fragment_container);

        recyclerCardExplore = view.findViewById(R.id.recycler_view_explore);
        recyclerCardExplore.enableViewScaling(true);
        //recyclerCardExplore.enableAlphaScaling(true);

        recyclerCardExplore.setItemViewCacheSize(20);
        recyclerCardExplore.setDrawingCacheEnabled(true);
        recyclerCardExplore.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        SnapExploreRecyclerAdapter adapter = new SnapExploreRecyclerAdapter(getActivity(), exploreCardItem);
        recyclerCardExplore.setAdapter(adapter);
        OverScrollDecoratorHelper.setUpOverScroll(recyclerCardExplore, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

    }

    /*
    library init
     */
    public void initLibrary(View view){

        createLibraryCard();

        fragmentContainer = view.findViewById(R.id.fragment_container);

        recyclerViewLibrary = view.findViewById(R.id.recycler_view_cardView_library);
        recyclerViewLibrary.setHasFixedSize(true);

        recyclerViewLibrary.setItemViewCacheSize(20);
        recyclerViewLibrary.setDrawingCacheEnabled(true);
        recyclerViewLibrary.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        recyclerViewLibrary.setLayoutManager(new GridLayoutManager(getActivity(),3, GridLayout.VERTICAL,false));

        SnapLibraryAdapter snapLibraryAdapter = new SnapLibraryAdapter(getActivity(), libraryCardItem );
        recyclerViewLibrary.setAdapter(snapLibraryAdapter);

        OverScrollDecoratorHelper.setUpOverScroll(recyclerViewLibrary, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

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
    private void createLibraryCard(){
        libraryCardItem = new ArrayList<>();

        libraryCardItem.add(new Item("All this", R.drawable.all_this, "Jojo Moyes", 93));
        libraryCardItem.add(new Item("Titan", R.drawable.titan, "Alessandro Baricco",25));
        libraryCardItem.add(new Item("Spazio", R.drawable.spazio, "Chiara Gamberale",801));
        libraryCardItem.add(new Item("Bookcover", R.drawable.art_bookcover, "Stephanie Meyer",1044));
        libraryCardItem.add(new Item("Creative", R.drawable.creative_bookcover, "Fabio Volo",528));
        libraryCardItem.add(new Item("Cupcake", R.drawable.cupcake, "Federico Moccia",19));
        libraryCardItem.add(new Item("fiore", R.drawable.fiore, "Dan Brown",10246));
        libraryCardItem.add(new Item("gelato", R.drawable.gelato, "Sam Pvnik", 94));
        libraryCardItem.add(new Item("Lampadina", R.drawable.lampadina, "George Orwell",621));
        libraryCardItem.add(new Item("Papera", R.drawable.papera,"Harper Lee",67));
        libraryCardItem.add(new Item("All this2", R.drawable.all_this, "Jojo Moyes", 93));
        libraryCardItem.add(new Item("Titan2", R.drawable.titan, "Alessandro Baricco",25));
        libraryCardItem.add(new Item("Spazio2", R.drawable.spazio, "Chiara Gamberale",801));
        libraryCardItem.add(new Item("Bookcover2", R.drawable.art_bookcover, "Stephanie Meyer",1044));
        libraryCardItem.add(new Item("Creative2", R.drawable.creative_bookcover, "Fabio Volo",528));
        libraryCardItem.add(new Item("Cupcake2", R.drawable.cupcake, "Federico Moccia",19));
        libraryCardItem.add(new Item("fiore2", R.drawable.fiore, "Dan Brown",10246));

    }
    public void createShowcaseCard() {

        showcaseCardItem = new ArrayList<>();

        showcaseCardItem.add( new Showcase(R.drawable.large, "Large"));
        showcaseCardItem.add(new Showcase(R.drawable.book_cover, "Colors"));
        showcaseCardItem.add(new Showcase(R.drawable.moon_cover, "Moon"));
    }

    public void createRvContinueReadItem() {

        rvContinueReadItem = new ArrayList<>();


        rvContinueReadItem.add(new Item("All this", R.drawable.all_this, "Jojo Moyes"));
        rvContinueReadItem.add(new Item("Titan", R.drawable.titan, "Alessandro Baricco"));
        rvContinueReadItem.add(new Item("Spazio", R.drawable.spazio, "Chiara Gamberale"));
        rvContinueReadItem.add(new Item("Bookcover", R.drawable.art_bookcover, "Stephanie Meyer"));
        rvContinueReadItem.add(new Item("Creative", R.drawable.creative_bookcover, "Fabio Volo"));
        rvContinueReadItem.add(new Item("Cupcake", R.drawable.cupcake, "Federico Moccia"));
        rvContinueReadItem.add(new Item("fiore", R.drawable.fiore, "Dan Brown"));
        rvContinueReadItem.add(new Item("gelato", R.drawable.gelato, "Sam Pvnik"));
        rvContinueReadItem.add(new Item("Lampadina", R.drawable.lampadina, "George Orwell"));
        rvContinueReadItem.add(new Item("Papera", R.drawable.papera,"Harper Lee"));
        rvContinueReadItem.add(new Item("All this2", R.drawable.all_this, "Jojo Moyes"));
        rvContinueReadItem.add(new Item("Titan2", R.drawable.titan, "Alessandro Baricco"));
        rvContinueReadItem.add(new Item("Spazio2", R.drawable.spazio, "Chiara Gamberale"));
        rvContinueReadItem.add(new Item("Bookcover2", R.drawable.art_bookcover, "Stephanie Meyer"));
        rvContinueReadItem.add(new Item("Creative2", R.drawable.creative_bookcover, "Fabio Volo"));
        rvContinueReadItem.add(new Item("Cupcake2", R.drawable.cupcake, "Federico Moccia"));
        rvContinueReadItem.add(new Item("fiore2", R.drawable.fiore, "Dan Brown"));
    }
    public void createRvCategoriesItem(){
        rvCategoriesItem = new ArrayList<>();

        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
        rvCategoriesItem.add(new Item(R.drawable.horror));
        rvCategoriesItem.add(new Item(R.drawable.comedy));
    }
    private void createRvBestOfWeek() {
        rvBestWeekItem = new ArrayList<>();

        rvBestWeekItem.add(new Item("All this", R.drawable.all_this, "Jojo Moyes"));
        rvBestWeekItem.add(new Item("Titan", R.drawable.titan, "Alessandro Baricco"));
        rvBestWeekItem.add(new Item("Spazio", R.drawable.spazio, "Chiara Gamberale"));
        rvBestWeekItem.add(new Item("Bookcover", R.drawable.art_bookcover, "Stephanie Meyer"));
        rvBestWeekItem.add(new Item("Creative", R.drawable.creative_bookcover, "Fabio Volo"));
    }

}
