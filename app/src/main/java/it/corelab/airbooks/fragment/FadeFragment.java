package it.corelab.airbooks.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import developer.shivam.library.DiagonalView;
import it.corelab.airbooks.CustomNested;
import it.corelab.airbooks.Http.HttpHandler;
import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.AddSection;
import it.corelab.airbooks.activity.MainActivity;
import it.corelab.airbooks.adapters.CardViewReviewAdapter;
import it.corelab.airbooks.adapters.InfiniteRotationAdapter;
import it.corelab.airbooks.adapters.SnapBestOfWeek;
import it.corelab.airbooks.adapters.SnapCategoriesAdapter;
import it.corelab.airbooks.adapters.SnapContinueReadAdapter;
import it.corelab.airbooks.adapters.SnapExploreRecyclerAdapter;
import it.corelab.airbooks.adapters.SnapLibraryAdapter;
import it.corelab.airbooks.adapters.SnapRecyclerAdapter;
import it.corelab.airbooks.object.Book;
import it.corelab.airbooks.object.Item;
import it.corelab.airbooks.object.Showcase;
import it.corelab.airbooks.object.User;
import it.corelab.airbooks.recyclerViewExtension.InfiniteRotationView;
import it.corelab.airbooks.recyclerViewExtension.SnappingRecyclerView;
import it.corelab.airbooks.widget.RoundedImageView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/*

* This is official coded by © Roberto Vecchio and property of all member of: corelab
* if you want to use this code send an email to: vecchioroberto93@gmail.com
* Any uses without permission could be subject to legal action

 */

public class FadeFragment extends Fragment {

    /*

    * frame layout that contains all the section managed in the bottom bar
    * @fragmentContainer is never accessed because
    * we have commented @willBeHidden and @willBeDisplay

     */

   private FrameLayout fragmentContainer;


   /*

   * Initialization of the @GLOBAL recyclerView
   * The first recyclerView is used for the management of section in the refresh method

    */

   private RecyclerView recyclerView;
   private GetBestOfWeek asyncTask;
   private GetBestOfWeek asyncTaskExplorer;
    private GetBestOfWeek asyncTaskLib;
   private GetCurrentUser userAsync;
   private static User userclass;
   private TextView nameSurname;
   private TextView fromTo;
   private TextView followers;
   private RoundedImageView profileImage;
   private static ProgressDialog pDialog;
   private CustomNested customNested;
   public static ImageView trapezoid;
   public static DiagonalView diagonalView;
   public static float angleVariation;
   public static int yPosition;
   public static DiagonalView exploreDiagonal;
    public static DiagonalView libDiagonal;
    public static DiagonalView profileDiagonal;
   //public static ImageView topBar;


    /*

   * This is the global variable of the autoScroll recyclerView
   * Is declared as global because it is called by onDestroyMethod in MainActivity

    */

    public static InfiniteRotationView rotationView;


   /*

   * Initialization of the ArrayList
   * These ArrayList are used to populate the cell of the recyclerView
   * Modify the generics < > to change what type you would use

    */

   private ArrayList<Item> items;
   private ArrayList<Item> reviewCard;
   private ArrayList<Item> exploreCardItem;
   private ArrayList<Item> libraryCardItem;
   private ArrayList<Item> rvContinueReadItem;
   private ArrayList<Item> rvCategoriesItem;
   private ArrayList<Showcase> showcaseCardItem;
   private static ArrayList<Book> bookArrayList;


   /*

   * create a new instance of the fragment
   * this instance of fragment is used by section to initialize different layout

    */

   public static FadeFragment newInstance(int index){
       FadeFragment fragment = new FadeFragment();
       Bundle b = new Bundle();
       b.putInt("index", index);
       fragment.setArguments(b);
       return fragment;
   }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (asyncTask != null) {
            asyncTask.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }else if (userAsync != null) {
            userAsync.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }else if (asyncTaskExplorer != null){
            asyncTaskExplorer.setListener(null); // PREVENT LEAK AFTER ACTIVITY DESTROYED
        }

        rotationView.stopAutoScroll();
    }


   /*=====================================================================
                                onCreateView
    ====================================================================*/

   /*

   * This is our onCreate method
   * @onCreate call different layout
   * based on the index that the bottomBar return in airbook's app
   * after the index return the method to init our choosen @USER INTERFACE
   * and finally return the @VIEW to the user

   */


   @Override
   public View onCreateView(@NonNull LayoutInflater inflanter, ViewGroup container, Bundle savedInstanceState){

       switch (getArguments().getInt("index", 0)) {
           case 0: {
               View view = inflanter.inflate(R.layout.home_fragment, container, false);
               initHome(view);
               return view;
           }
           case 1: {
               View view = inflanter.inflate(R.layout.explore_fragment, container, false);
               initExplore(view);
               return view;
           }
           case 2: {
               View view = inflanter.inflate(R.layout.library_fragment, container, false);
               initLibrary(view);
               return view;
           }
           case 3: {
               View view = inflanter.inflate(R.layout.profile_fragment, container, false);
               initProfile(view);
               return view;
           }
           default: {
               View view = inflanter.inflate(R.layout.profile_fragment, container, false);
               initProfile(view);
               return view;
           }
       }

   }



   /*

   // USE THIS METHOD IF YOU NEED TO ANIMATE IN ENTRY A SECTION OF BOTTOM
   * Called when a fragment will be displayed
   * Do what you want here, for example animate the content

   * ========================================================================================= *
 */

   public void willBeDisplayed(){
       if(fragmentContainer!= null){
           exploreDiagonal.setAngle(angleVariation);
           Log.i("HIDDEN", "" + angleVariation);
       }
   }

/*
   * ========================================================================================= *





   // USE THIS METHOD IF YOU NEED TO ANIMATE IN EXIT A SECTION OF BOTTOM
   * called when a fragment will be hidden
   * Do what you want here, for example animate the content


   * ========================================================================================= *
 */

   public void willBeHidden(){
       if (fragmentContainer != null){
           exploreDiagonal.setAngle(angleVariation);
       }
   }

 /*
   * ========================================================================================= *



   * @refresh method
   * used to smooth scroll to choosen section of bottomBar

    */

    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }


    /*=====================================================================
                                initHome()
    =====================================================================*/

    /*

    * home initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the home

     */

    @SuppressLint("ClickableViewAccessibility")
    public void initHome(final View view){

        customNested = view.findViewById(R.id.nested_home);
        diagonalView = view.findViewById(R.id.diagonal_view);
        //trapezoid = view.findViewById(R.id.trapezoid);
        //topBar = view.findViewById(R.id.topbar);

        customNested.snap();
        angleVariation = 16.0f;



        /*

         * This is the intent for the add section
         * is declared final to access to inner method
         * this is the biggest section initialization for now

         */

        final Intent intentAddSection = new Intent(getActivity(), AddSection.class);


        /*

        * these method initialize and fill the empty recyclerView with the choosen elements
        * @EMPTY RECYCLERVIEW FILLED:
        *
        * 1. rotationView
        * 2. rvContinueRead
        * 3. rvCategories
        * 4. rvBestWeek

         */

        createShowcaseCard();
        createRvContinueReadItem();
        createRvCategoriesItem();
        bookArrayList = new ArrayList<>();


        /*

        Initialize the @fragmentContainer with the choosen layout

         */

        fragmentContainer = view.findViewById(R.id.fragment_container);


        /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line
        * @rotationView is declared as global so it is an exception for guidelines

         */

        // 1. GROUP
        final ImageButton addButtonHome = view.findViewById(R.id.add_button_home);
        //final NestedScrollView nestedScrollView = view.findViewById(R.id.nested_home);

        // 2. GROUP
        ImageButton search = view.findViewById(R.id.search_button_home);

        // 3. GROUP
        rotationView = view.findViewById(R.id.rv_showcase);

        final RecyclerView rvContinueRead = view.findViewById(R.id.rv_continue_reading);
        RecyclerView rvCategories = view.findViewById(R.id.rv_categories);
        final RecyclerView rvBestWeek = view.findViewById(R.id.rv_bestweek);


        /*

        * disabled nested scroll for the different @recyclerView in home
        * add here same command if you want to add other recyclerView section

         */

        ViewCompat.setNestedScrollingEnabled(rotationView, false);
        ViewCompat.setNestedScrollingEnabled(rvContinueRead, false);
        ViewCompat.setNestedScrollingEnabled(rvCategories, false);
        ViewCompat.setNestedScrollingEnabled(rvBestWeek, false);


        /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

        rvBestWeek.setItemViewCacheSize(20);
        rvBestWeek.setDrawingCacheEnabled(true);
        rvBestWeek.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        rvContinueRead.setItemViewCacheSize(20);
        rvContinueRead.setDrawingCacheEnabled(true);
        rvContinueRead.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        rvCategories.setItemViewCacheSize(20);
        rvCategories.setDrawingCacheEnabled(true);
        rvCategories.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        /*

        * Here we are setting the layout manager that manage the recyclerView
        * Change it carefully, it could change completely your layout appearance
        * we set also a commmand for each recyclerView to have a fixedSize
        * @rvBestWeek has a particular method to block the scroll

         */

        rvContinueRead.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvContinueRead.setHasFixedSize(true);
        rvBestWeek.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rvBestWeek.setHasFixedSize(true);


        rvCategories.setLayoutManager(new GridLayoutManager(getActivity(), 2,GridLayoutManager.HORIZONTAL,false));
        rvCategories.setHasFixedSize(true);





        /*

        * we can't use one adapter for all the recyclerView
        * so here we are initializing and creating different adapter
        * and then assigning to the different recyclerView
        * different adapter initialization used only for @rotationView
        * @FOLLOW the guidelines of the other

         */

        SnapCategoriesAdapter snapCategoriesAdapter = new SnapCategoriesAdapter(getActivity(), rvCategoriesItem);
        rvCategories.setAdapter(snapCategoriesAdapter);


        rotationView.setAdapter(new InfiniteRotationAdapter(showcaseCardItem));


        /*

        * autoScroll customization

         */

        rotationView.autoScroll(3, 3000);


        /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

        OverScrollDecoratorHelper.setUpOverScroll(rvContinueRead, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(rvCategories, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);


        asyncTask = new GetBestOfWeek(this);
        asyncTask.setListener(new GetBestOfWeek.BestOfWekkAsyncTaskLinestener() {
            @Override
            public void onExampleAsyncTaskFinished(Integer value) {
                // update UI in Activity here
                //dismiss the progress dialog
               /* if (pDialog.isShowing()){
                    pDialog.dismiss();
                }*/

                SnapContinueReadAdapter snapContinueReadAdapter = new SnapContinueReadAdapter(getActivity(), bookArrayList);
                rvContinueRead.setAdapter(snapContinueReadAdapter);

                SnapBestOfWeek snapBestOfWeek = new SnapBestOfWeek(getActivity(),bookArrayList);
                rvBestWeek.setAdapter(snapBestOfWeek);

            }
        });
        asyncTask.execute();


        /*

        * This method is used to enlarge the hit area of @addButton
        * for now the area is 200, to modify comunicate it to design team

         */

        final View parent = (View) addButtonHome.getParent();  // button: the view you want to enlarge hit area
        parent.post( new Runnable() {
            public void run() {
                final Rect rect = new Rect();
                addButtonHome.getHitRect(rect);
                rect.top -= 200;    // increase top hit area
                rect.left -= 200;   // increase left hit area
                rect.bottom += 200; // increase bottom hit area
                rect.right += 200;  // increase right hit area
                parent.setTouchDelegate( new TouchDelegate( rect , addButtonHome));
            }
        });


        /*

        * This method is used to show automatically the bottomNavigation at the end of the page
        * this isn't scripted to a specifically height
        * it is modular and adapted automatically to every height you choose for the main layout

         */

        //final ImageView trapezoid = view.findViewById(R.id.trapezoid);
       customNested.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (customNested.getChildAt(0).getBottom() <= (customNested.getHeight() + customNested.getScrollY())) {
                    //scroll view is at bottom
                    MainActivity.bottomNavigation.restoreBottomNavigation(true);
                }
                //@ELSE scroll view is not at bottom

            }
        });


        /*

        * This method is used for action on tap of @addButton
        * the setFlags is used to avoid multiple intent on multiple tap
        * the pending transition override the animation of the intent

         */

        addButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentAddSection.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentAddSection);
                assert getContext() != null;
                ((Activity)getContext()).overridePendingTransition(R.anim.intent_from_left_in, R.anim.intent_from_left_out);
            }
        });

    }


    /*=====================================================================
                                initExplore()
    =====================================================================*/

    /*

    * Explore initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the explore section

     */

    public void initExplore(View view){
        bookArrayList = new ArrayList<>();


        /*

        * these method initialize and fill the empty recyclerView with the choosen elements
        * @EMPTY RECYCLERVIEW FILLED:
        *
        * 1. ExploreCard

         */

        createExploreCard();


        /*

        Initialize the @fragmentContainer with the choosen layout

         */

        fragmentContainer = view.findViewById(R.id.fragment_container);
        exploreDiagonal = view.findViewById(R.id.diagonal_view_explore);




        /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line

         */

        //1. GROUP

        //2. GROUP

        //3.GROUP
        final SnappingRecyclerView recyclerCardExplore = view.findViewById(R.id.recycler_view_explore);


        /*

        * The first command enable the @SCALING on horizontal scroll of @recyclerView
        * The second command enable the @ALPHA SCALING on horizontal scroll of @recyclerView
        * uncomment second command to alpha scaling

         */

        recyclerCardExplore.enableViewScaling(true);
        //recyclerCardExplore.enableAlphaScaling(true);


        /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

        recyclerCardExplore.setItemViewCacheSize(20);
        recyclerCardExplore.setDrawingCacheEnabled(true);
        recyclerCardExplore.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        /*

        * we can't use one adapter for all the recyclerView
        * so here we are initializing and creating different adapter
        * and then assigning to the different recyclerView
        * @FOLLOW the guidelines of these

         */


        /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

        OverScrollDecoratorHelper.setUpOverScroll(recyclerCardExplore, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        asyncTaskExplorer = new GetBestOfWeek(this);
        asyncTaskExplorer.setListener(new GetBestOfWeek.BestOfWekkAsyncTaskLinestener() {
            @Override
            public void onExampleAsyncTaskFinished(Integer value) {
                // update UI in Activity here
                //dismiss the progress dialog
              /*  if (pDialog.isShowing()){
                    pDialog.dismiss();
                }*/

                SnapExploreRecyclerAdapter adapter = new SnapExploreRecyclerAdapter(getActivity(), bookArrayList);
                recyclerCardExplore.setAdapter(adapter);
            }
        });
        asyncTaskExplorer.execute();

    }


    /*=====================================================================
                                initLibrary()
    =====================================================================*/

    /*

    * Library initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the library section

     */

    public void initLibrary(View view){


        /*

        * these method initialize and fill the empty recyclerView with the choosen elements
        * @EMPTY RECYCLERVIEW FILLED:
        *
        * 1. recyclerViewLibrary

         */

        createLibraryCard();


        /*

        Initialize the @fragmentContainer with the choosen layout

         */

        fragmentContainer = view.findViewById(R.id.fragment_container);

        libDiagonal = view.findViewById(R.id.diagonal_view_library);


        /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line

         */

        //1. GROUP

        //2. GROUP

        //3.GROUP
        final RecyclerView recyclerViewLibrary = view.findViewById(R.id.recycler_view_cardView_library);


        /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

        recyclerViewLibrary.setItemViewCacheSize(20);
        recyclerViewLibrary.setDrawingCacheEnabled(true);
        recyclerViewLibrary.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


        /*

        * Here we are setting the layout manager that manage the recyclerView
        * Change it carefully, it could change completely your layout appearance
        * we set also a commmand for each recyclerView to have a fixedSize

         */

        recyclerViewLibrary.setLayoutManager(new GridLayoutManager(getActivity(),3, GridLayout.VERTICAL,false));
        recyclerViewLibrary.setHasFixedSize(true);


        /*

        * we can't use one adapter for all the recyclerView
        * so here we are initializing and creating different adapter
        * and then assigning to the different recyclerView
        * @FOLLOW the guidelines of these

         */




        /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

        OverScrollDecoratorHelper.setUpOverScroll(recyclerViewLibrary, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        asyncTaskLib = new GetBestOfWeek(this);
        asyncTaskLib.setListener(new GetBestOfWeek.BestOfWekkAsyncTaskLinestener() {
            @Override
            public void onExampleAsyncTaskFinished(Integer value) {
                // update UI in Activity here
                //dismiss the progress dialog
              /*  if (pDialog.isShowing()){
                    pDialog.dismiss();
                }*/

                SnapLibraryAdapter snapLibraryAdapter = new SnapLibraryAdapter(getActivity(), bookArrayList );
                recyclerViewLibrary.setAdapter(snapLibraryAdapter);
            }
        });
        asyncTaskLib.execute();

    }


    /*=====================================================================
                                initProfile()
    =====================================================================*/

    /*

    * Profile initialization
    * this method is used by @onCreateView at @125 line
    * to initialize the @USER INTERFACE of the profile section

     */

   public void initProfile(View view){

       /*

        * these method initialize and fill the empty recyclerView with the choosen elements
        * @EMPTY RECYCLERVIEW FILLED:
        *
        * 1. recyclerView
        * 2. cardReviewRecycleView

         */

       createApps();
       createReviewCard();


       /*

        Initialize the @fragmentContainer with the choosen layout

         */

       fragmentContainer = view.findViewById(R.id.fragment_container);
       profileDiagonal = view.findViewById(R.id.diagonal_view_profile);


       /*

        * Initialization of different variables @GROUP
        *
        * @1. GROUP = declare final to access to inner method
        * @2. GROUP = normal widget and view variables
        * @3. GROUP = recyclerView @creation and @initialization
        *
        * @CREATION and @INITIALIZATION must be done in one line

         */

       //1. GROUP
       final Button buttonAction = view.findViewById(R.id.buttonAction);
       final TextView nameSurname = view.findViewById(R.id.NameSurname);
       final TextView fromTo = view.findViewById(R.id.fromTo);
       final TextView followers = view.findViewById(R.id.followNumber);
       final RoundedImageView profileImage = view.findViewById(R.id.profileImagePlaceHolder);

       userAsync = new GetCurrentUser(this);
       userAsync.setListener(new GetCurrentUser.userAsyncTaskLinestener() {
           @Override
           public void onExampleAsyncTaskFinished(Integer value) {
               // update UI in Activity here
               nameSurname.setText(userclass.getFirstName() + " " + userclass.getLastName());
               fromTo.setText(userclass.getNationality());
               followers.setText(userclass.getFollower() + "");
               Picasso.get().load(userclass.getProfilePictureUrl()).into(profileImage);
           }
       });
       userAsync.execute();

       //2. GROUP

       //3.GROUP
       RecyclerView cardReviewRecycleView = view.findViewById(R.id.recycler_view_cardView);
       RecyclerView recyclerViewAllBooks = view.findViewById(R.id.recycler_view);

       /*

        * optimization of the recyclerView with the use of the @CACHE
        * we are:
        *
        * 1.Setting the cache size
        * 2.Enabling drawing cache
        * 3.Setting cache quality

         */

       recyclerViewAllBooks.setItemViewCacheSize(20);
       recyclerViewAllBooks.setDrawingCacheEnabled(true);
       recyclerViewAllBooks.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);


       /*

        * Here we are setting the layout manager that manage the recyclerView
        * Change it carefully, it could change completely your layout appearance
        * we set also a commmand for each recyclerView to have a fixedSize

         */

       recyclerViewAllBooks.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       recyclerViewAllBooks.setHasFixedSize(true);

       cardReviewRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
       cardReviewRecycleView.setHasFixedSize(true);


       /*

        * we can't use one adapter for all the recyclerView
        * so here we are initializing and creating different adapter
        * and then assigning to the different recyclerView
        * @FOLLOW the guidelines of these

         */

       SnapRecyclerAdapter adapter = new SnapRecyclerAdapter(getActivity(), items);
       recyclerViewAllBooks.setAdapter(adapter);

       CardViewReviewAdapter reviewAdapter = new CardViewReviewAdapter(getActivity(),reviewCard);
       cardReviewRecycleView.setAdapter(reviewAdapter);


       /*

        * This command is used for the bounce effect on @recyclerView
        * if you want add effect add here

         */

       OverScrollDecoratorHelper.setUpOverScroll(recyclerViewAllBooks, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
       OverScrollDecoratorHelper.setUpOverScroll(cardReviewRecycleView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);


       /*

        * This method is used to enlarge the hit area of @addButton
        * for now the area is 200, to modify comunicate it to design team

         */

       final View parent = (View) buttonAction.getParent();  // button: the view you want to enlarge hit area
       parent.post( new Runnable() {
           public void run() {
               final Rect rect = new Rect();
               buttonAction.getHitRect(rect);
               rect.top -= 200;    // increase top hit area
               rect.left -= 200;   // increase left hit area
               rect.bottom += 200; // increase bottom hit area
               rect.right += 200;  // increase right hit area
               parent.setTouchDelegate( new TouchDelegate( rect , buttonAction));
           }
       });



       /*

        * This method is used for action on tap of @addButton
        * In this case it set the popUp menu action

         */

       buttonAction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               PopupMenu popupMenu = new PopupMenu(getActivity(), buttonAction);
               popupMenu.getMenuInflater().inflate(R.menu.actions, popupMenu.getMenu());

               popupMenu.show();
           }
       });

   }


   /*=====================================================================
                           Filling recyclerView methods
    =====================================================================*/

    /*

    * These methods is used to fill the elements of recyclerViews
    * But for now are only for placeHolder
    * @NEXT TODO:// collegue to dataBase

     */

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
        reviewCard.add(new Item("All this has nothing",R.drawable.all_this, 5, "Jojo Moyes", "This book is very fantastic, no words !!!!",R.drawable.for_children));
        reviewCard.add(new Item("Titan", R.drawable.titan,4,"Alessandro Baricco","“Oltre l’inverno” è l’opera letteraria di un’autrice che, negli ultimi anni, comincia a scrivere ogni suo libro l’8 di Gennaio.\n" +
                "Si parla di una bufera di neve che coinvolge la città di Brooklyn e più nello specifico di tre persone le cui vite si intrecciano: Lucia Maraz, Richard Bowmaster e Evelyn Ortega",R.drawable.sci_fi));
        reviewCard.add(new Item("Spazio", R.drawable.spazio,3,"Chiara Gamberale","Isabel Allende ci racconta una storia di amori, politica, dittature e fughe. Un romanzo ambientato in una Brooklyn paralizzata da una forte bufera di neve.",R.drawable.biografy));
        reviewCard.add(new Item("Bookcover", R.drawable.art_bookcover,2,"Stephanie Meyer", "Tra tutti i libri di Allende che ho letto questo è quello che mi ha meno coinvolto, potrei definirlo il più ingenuo. Mi ha dato la sensazione di leggere una pagina di diario di una ragazzina degli anni '60",R.drawable.comics_manga));
        reviewCard.add(new Item("Creative", R.drawable.creative_bookcover,1,"Fabio Volo","buon libro scritto molto bene",R.drawable.wise));
        reviewCard.add(new Item("Cupcake", R.drawable.cupcake,5, "Federico Moccia","Libro particolare .....da' l'idea di un libro a spirale . Isabelle Allende descrive i personaggi e le situazioni da loro vissute in modo particolare",R.drawable.erotic));
        reviewCard.add(new Item("fiore", R.drawable.fiore,4,"Dan Brown","Amo la Allende, ho letto quasi tutti i suoi libri. Questo romanzo l'ho letto nel giro di una settimana e devo dire che la storia scorre tranquillamente.",R.drawable.self_help));
        reviewCard.add(new Item("gelato", R.drawable.gelato,3,"Sam Pvnik","Ho letto alcuni libri di Isabel Allende a partire dal suo primo grande successo. La sua narrativa scorrevole e appetitosa mi piace e m'invita alla lettura",R.drawable.yellow_thriller));
        reviewCard.add(new Item("Lampadina", R.drawable.lampadina,2,"George Orwell","Bel libro, scorrevole emozionante anche se mi aspettavo di più da Isabel Allende.",R.drawable.for_children));
        reviewCard.add(new Item("Papera", R.drawable.papera,1,"Harper Lee","This book is very fantastic, no words !!!!",R.drawable.sci_fi));
    }

    private void createExploreCard(){
        exploreCardItem = new ArrayList<>();

        exploreCardItem.add(new Item("All this", R.drawable.all_this, 93, 3 , "Jojo Moyes"));
        exploreCardItem.add(new Item("Titan", R.drawable.titan,25, 4, "Alessandro Baricco"));
        exploreCardItem.add(new Item("Spazio", R.drawable.spazio,801,5, "Chiara Gamberale"));
        exploreCardItem.add(new Item("Bookcover", R.drawable.art_bookcover,1044,2, "Stephanie Meyer"));
        exploreCardItem.add(new Item("Creative", R.drawable.creative_bookcover,528,1, "Fabio Volo"));
        exploreCardItem.add(new Item("Cupcake", R.drawable.cupcake,19,5, "Federico Moccia"));
        exploreCardItem.add(new Item("fiore", R.drawable.fiore,10246,4,"Dan Brown"));
        exploreCardItem.add(new Item("gelato", R.drawable.gelato, 94,3, "Sam Pvnik"));
        exploreCardItem.add(new Item("Lampadina", R.drawable.lampadina,621,1, "George Orwell"));
        exploreCardItem.add(new Item("Papera", R.drawable.papera,67,2,"Harper Lee"));
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

        showcaseCardItem.add( new Showcase(R.drawable.place_holder_sinistra));
        showcaseCardItem.add(new Showcase(R.drawable.place_holder_centro));
        showcaseCardItem.add(new Showcase(R.drawable.place_holder_destra));
    }

    public void createRvContinueReadItem() {

        rvContinueReadItem = new ArrayList<>();


        rvContinueReadItem.add(new Item( R.drawable.all_this));
        rvContinueReadItem.add(new Item( R.drawable.titan));
        rvContinueReadItem.add(new Item(R.drawable.spazio));
        rvContinueReadItem.add(new Item(R.drawable.art_bookcover));
        rvContinueReadItem.add(new Item(R.drawable.creative_bookcover));
        rvContinueReadItem.add(new Item(R.drawable.cupcake));
        rvContinueReadItem.add(new Item( R.drawable.fiore));
        rvContinueReadItem.add(new Item(R.drawable.gelato));
        rvContinueReadItem.add(new Item(R.drawable.lampadina));
        rvContinueReadItem.add(new Item(R.drawable.papera));
        rvContinueReadItem.add(new Item(R.drawable.all_this));
        rvContinueReadItem.add(new Item(R.drawable.titan));
        rvContinueReadItem.add(new Item(R.drawable.spazio));
        rvContinueReadItem.add(new Item(R.drawable.art_bookcover));
        rvContinueReadItem.add(new Item(R.drawable.creative_bookcover));
        rvContinueReadItem.add(new Item(R.drawable.cupcake));
        rvContinueReadItem.add(new Item(R.drawable.fiore));
    }
    public void createRvCategoriesItem(){
        rvCategoriesItem = new ArrayList<>();

        rvCategoriesItem.add(new Item(R.drawable.sci_fi, "Sci-fi"));
        rvCategoriesItem.add(new Item(R.drawable.for_children, "Per bambini"));
        rvCategoriesItem.add(new Item(R.drawable.biografy, "Biografia"));
        rvCategoriesItem.add(new Item(R.drawable.comics_manga, "Fumetti e Manga"));
        rvCategoriesItem.add(new Item(R.drawable.teen_fiction, "Teen fiction"));
        rvCategoriesItem.add(new Item(R.drawable.teenagers, "Adolescenti e ragazzi"));
        rvCategoriesItem.add(new Item(R.drawable.self_help, "Self help"));
        rvCategoriesItem.add(new Item(R.drawable.gastronomy, "Gastronomia"));
        rvCategoriesItem.add(new Item(R.drawable.religion, "Religione"));
        rvCategoriesItem.add(new Item(R.drawable.fan_fiction,"Fan fiction"));
        rvCategoriesItem.add(new Item(R.drawable.dramatic,"Drammatico"));
        rvCategoriesItem.add(new Item(R.drawable.wise, "Saggistica"));
        rvCategoriesItem.add(new Item(R.drawable.humor,"Humor"));
        rvCategoriesItem.add(new Item(R.drawable.fantasy_gradient, "Fantasy"));
        rvCategoriesItem.add(new Item(R.drawable.adventure, "Avventura"));
        rvCategoriesItem.add(new Item(R.drawable.erotic, "Erotic"));
        rvCategoriesItem.add(new Item(R.drawable.yellow_thriller, "Gialli e thriller"));
        rvCategoriesItem.add(new Item(R.drawable.manual, "Manuali"));
        rvCategoriesItem.add(new Item(R.drawable.horror_gradient, "Horror"));
        rvCategoriesItem.add(new Item(R.drawable.action, "Azione"));
        rvCategoriesItem.add(new Item(R.drawable.sport,"Sport"));
        rvCategoriesItem.add(new Item(R.drawable.poetry, "Poesia"));
        rvCategoriesItem.add(new Item(R.drawable.story,"Storia"));
        rvCategoriesItem.add(new Item(R.drawable.other, "Altro"));
    }

    public static class GetBestOfWeek extends AsyncTask<Void,Void,Integer> {

        private WeakReference<FadeFragment> activityReference;

        private String TAG = FadeFragment.class.getSimpleName();
        private String url ="http://airbooks.altervista.org/API/v2/books/";
        private GetBestOfWeek.BestOfWekkAsyncTaskLinestener listener;

        GetBestOfWeek(FadeFragment context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            FadeFragment activity = activityReference.get();
            //showing progress dialog
           /* pDialog = new ProgressDialog(activity.getActivity());
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();*/
        }

        @Override
        protected Integer doInBackground(Void... arg0){
            final FadeFragment activity = activityReference.get();

            HttpHandler sh = new HttpHandler();

            //Making a request to url and getting a response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject result = jsonObject.getJSONObject("result");

                    //Getting JSON Array node
                    JSONArray books = result.getJSONArray("items");

                    //looping through all books
                    for (int i = 0; i < books.length(); i++){
                        JSONObject c = books.getJSONObject(i);

                        String id = c.getString("id");
                        String userID = c.getString("user_id");
                        String title = c.getString("title");
                        String description = c.getString("description");
                        String genreID = c.getString("genre_id");
                        String language = c.getString("language");
                        String uploadDate = c.getString("upload_date");
                        String coverUrl = c.getString("cover_url");
                        String bookUrl = c.getString("book_url");
                        String authorName = c.getString("author_first_name");
                        String authorLastName = c.getString("author_last_name");
                        int readings = c.getInt("readings");
                        int lovers = c.getInt("lovers");
                        double averageRatings = c.getDouble("average_rating");
                        //boolean isSaved = c.getBoolean("is_saved");

                        //temp hash map for single book
                        HashMap<String,Object> bookItem = new HashMap<>();

                        //adding each child node to HashMap key => value
                        bookItem.put("id",id);
                        bookItem.put("user_id", userID);
                        bookItem.put("title", title);
                        bookItem.put("description", description);
                        bookItem.put("genre_id", genreID);
                        bookItem.put("language", language);
                        bookItem.put("upload_date", uploadDate);
                        bookItem.put("cover_url",coverUrl);
                        bookItem.put("book_url", bookUrl);
                        bookItem.put("readings", readings);
                        bookItem.put("lovers", lovers);
                        bookItem.put("average_rating", averageRatings);
                        bookItem.put("author_first_name",authorName);
                        bookItem.put("author_last_name", authorLastName);
                        // bookItem.put("is_saved", isSaved);

                        Book bookClass = new Book(bookItem);

                        bookArrayList.add(bookClass);
                    }
                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.getActivity(),"JSon parsing error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else{
                Log.e(TAG,"Couldn't get json from server");
                (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getActivity(),"Couldn't get json from server", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer value){
            super.onPostExecute(value);

            if (listener != null) {
                listener.onExampleAsyncTaskFinished(value);
            }
        }

        void setListener(GetBestOfWeek.BestOfWekkAsyncTaskLinestener listener) {
            this.listener = listener;
        }

        public interface BestOfWekkAsyncTaskLinestener {
            void onExampleAsyncTaskFinished(Integer value);
        }
    }

    public static class GetCurrentUser extends AsyncTask<Void,Void,Integer> {

        private String TAG = FadeFragment.class.getSimpleName();
        private String urlUser = "http://airbooks.altervista.org/API/v2/users/5acb8298b1d467.49059072/all";
        private GetCurrentUser.userAsyncTaskLinestener listener;
        private WeakReference<FadeFragment> activityReference;


        GetCurrentUser(FadeFragment context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            Log.e(TAG, "please wait");
        }

        @Override
        protected Integer doInBackground(Void... arg0){
            final FadeFragment activity = activityReference.get();
            HttpHandler sh = new HttpHandler();

            //Making a request to url and getting a response
            String jsonStr = sh.makeServiceCall(urlUser);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                try {

                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject userObj = jsonObject.getJSONObject("result");

                    String ID = userObj.getString("id");
                    String firstName = userObj.getString("first_name");
                    String lastName = userObj.getString("last_name");
                    String profilePictureUrl = userObj.getString("profile_picture_url");
                    String nationality = userObj.getString("nationality");
                    String email = userObj.getString("email");
                    int followers = Integer.parseInt(userObj.getString("followers_count"));

                    //temp hash map for single book
                    HashMap<String, Object> userItem = new HashMap<>();

                    userItem.put("id",ID);
                    userItem.put("first_name", firstName);
                    userItem.put("last_name", lastName);
                    userItem.put("profile_picture_url", profilePictureUrl);
                    userItem.put("nationality", nationality);
                    userItem.put("followers_count", followers);
                    userItem.put("email",email);

                    userclass = new User(userItem);

                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    (Objects.requireNonNull(activity.getActivity())).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.getActivity(),"JSon parsing error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else{
                Log.e(TAG,"Couldn't get json from server");
                ((Objects.requireNonNull(activity.getActivity()))).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.getActivity(),"Couldn't get json from server", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer value){
            super.onPostExecute(value);

            if (listener != null) {
                listener.onExampleAsyncTaskFinished(value);
            }
        }

        void setListener(GetCurrentUser.userAsyncTaskLinestener listener) {
            this.listener = listener;
        }

        public interface userAsyncTaskLinestener {
            void onExampleAsyncTaskFinished(Integer value);
        }
    }
}