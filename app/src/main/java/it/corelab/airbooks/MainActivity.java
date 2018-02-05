package it.corelab.airbooks;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import it.corelab.airbooks.mFragments.ExploreFragment;
import it.corelab.airbooks.mFragments.HomeFragment;
import it.corelab.airbooks.mFragments.LibraryFragment;
import it.corelab.airbooks.mFragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {


    //=============================
    //  class variable declaration
    //=============================

    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;
    private Toolbar toolbar;
    private TextView textView;
    private ImageButton search;
    private ImageButton add;
    private ImageButton add_prefer;


    //=============================
    //          bindView
    //=============================

    //this is an example of how to initilize
    //do not uncomment or the app will crash
    // @BindView(R.id.bottom_navigation) AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //==========================
        //       declaration
        //==========================

        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
        Resources resources = getResources();


        onCreateItem();

        //==========================
        //      findViewById
        //==========================

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = findViewById(R.id.toolbar_title);
        search = findViewById(R.id.search);
        add = findViewById(R.id.action_add);
        add_prefer = findViewById(R.id.add_preferiti);


        //==========================
        //  ActionBar customization
        //==========================

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        textView.setText("HOME");
        add_prefer.setClickable(false);
        add_prefer.setEnabled(false);
        add_prefer.setVisibility(View.INVISIBLE);


        //==========================
        // fragment inizialization
        //==========================

        setupViewPager();


        //==========================
        //      tab management
        //==========================

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (!wasSelected) {
                    viewPager.setCurrentItem(position);
                    if (position == 0){
                        pagerAdapter.getRegisteredFragment(1);
                        getSupportActionBar().show();
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        textView.setText(R.string.homeSection);

                        add.setClickable(true);
                        add.setEnabled(true);
                        add.setVisibility(View.VISIBLE);
                        search.setClickable(true);
                        search.setEnabled(true);
                        search.setVisibility(View.VISIBLE);
                        add_prefer.setClickable(false);
                        add_prefer.setEnabled(false);
                        add_prefer.setVisibility(View.INVISIBLE);
                    }else if (position == 1){
                        pagerAdapter.getRegisteredFragment(0);
                        getSupportActionBar().show();
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        textView.setText(R.string.exploreSection);

                        add_prefer.setClickable(true);
                        add_prefer.setEnabled(true);
                        add_prefer.setVisibility(View.VISIBLE);
                        add.setClickable(false);
                        add.setEnabled(false);
                        add.setVisibility(View.INVISIBLE);
                        search.setClickable(false);
                        search.setEnabled(false);
                        search.setVisibility(View.INVISIBLE);
                    }else if (position == 2){
                        pagerAdapter.getRegisteredFragment(2);
                        getSupportActionBar().show();
                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                        textView.setText(R.string.librarySection);

                        add.setClickable(true);
                        add.setEnabled(true);
                        add.setVisibility(View.VISIBLE);
                        search.setClickable(true);
                        search.setEnabled(true);
                        search.setVisibility(View.VISIBLE);
                        add_prefer.setClickable(false);
                        add_prefer.setEnabled(false);
                        add_prefer.setVisibility(View.INVISIBLE);
                    }else if (position == 3){
                        pagerAdapter.getRegisteredFragment(3);
                        getSupportActionBar().hide();
                    }
                }
                return true;
            }
        });

    }





                                   /*      on create finish       */
//=================================================================================================
                                  /*      on create finish       */




    //============================================================================================//
    //                                      methods_main                                          //
    //============================================================================================//


    public void onCreateItem(){

        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
        Resources resources = getResources();


        //==========================
        //          Color
        //==========================

        int accentColor = resources.getColor(R.color.accent_color);
        int inactiveColor = resources.getColor(R.color.inactive_color);
        int intestattionColor = resources.getColor(R.color.intestazioni);

        //==========================
        //        drawable
        //==========================

        Drawable home = resources.getDrawable(R.drawable.icona_home);
        Drawable explore = resources.getDrawable(R.drawable.icona_esplora_piena);
        Drawable library = resources.getDrawable(R.drawable.icona_segnalibro);
        Drawable profile = resources.getDrawable(R.drawable.icona_profilo);


        //==========================
        //        bottomItem
        //==========================

        AHBottomNavigationItem homeItem = new AHBottomNavigationItem(resources.getString(R.string.tab1), home);
        AHBottomNavigationItem exploreItem = new AHBottomNavigationItem(resources.getString(R.string.tab2), explore);
        AHBottomNavigationItem libraryItem = new AHBottomNavigationItem(resources.getString(R.string.tab3), library);
        AHBottomNavigationItem profileItem = new AHBottomNavigationItem(resources.getString(R.string.tab4), profile);

        //==========================
        //        addItem
        //==========================

        bottomNavigation.addItem(homeItem);
        bottomNavigation.addItem(exploreItem);
        bottomNavigation.addItem(libraryItem);
        bottomNavigation.addItem(profileItem);


        //==========================
        //      ColorBottom
        //==========================

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));


        //==========================
        //        ColorIcon
        //==========================

        bottomNavigation.setInactiveColor(inactiveColor);
        bottomNavigation.setAccentColor(accentColor);


        //==========================
        //        iconLabel
        //==========================

        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);


        //==========================
        // BottomBar customization
        //==========================

        bottomNavigation.setUseElevation(false);
        bottomNavigation.setColored(false);
        bottomNavigation.setSoundEffectsEnabled(true);
        bottomNavigation.setCurrentItem(0);
    }

    @SuppressLint("RestrictedApi")
    @NonNull
    private ProfileFragment createProfileFragment() {
        ProfileFragment fragment = new ProfileFragment();

        return fragment;
    }

    @NonNull
    private HomeFragment createHomeFragment() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @NonNull
    private LibraryFragment createLibraryFragment() {
        LibraryFragment fragment = new LibraryFragment();

        return fragment;
    }

    @NonNull
    private ExploreFragment createExploreFragment() {
        ExploreFragment fragment = new ExploreFragment();

        return fragment;
    }

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.noSwiperPage);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(createHomeFragment());
        pagerAdapter.addFragments(createExploreFragment());
        pagerAdapter.addFragments(createLibraryFragment());
        pagerAdapter.addFragments(createProfileFragment());

        viewPager.setAdapter(pagerAdapter);
    }

    @NonNull
    private Bundle passFragmentArguments(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        return bundle;
    }
}













//============================================================================================//
//                                      FolioReader *Help*                                    //
//============================================================================================//



/*  =================== istanziare FolioReader nel metodo onCreate =================== */


// instanza di folioReader
//uncomment per lettura epub

        /*
        FolioReader folioReader = new FolioReader(this);
        folioReader.registerHighlightListener(this);
        folioReader.openBook(R.raw.canzone);
        getHighlightsAndSave();                          */




/*  ========================= fuori dal metodo onCreate ============================ */



//uncomment solo quando è possibile leggere epub
   /* private void getHighlightsAndSave() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<HighLight> highlightList = null;
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    highlightList = objectMapper.readValue(
                            loadAssetTextAsString("highlights/highlights_data.json"),
                            new TypeReference<List<HighlightData>>() {
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (highlightList == null) {
                    folioReader.saveReceivedHighLights(highlightList, new OnSaveHighlight() {
                        @Override
                        public void onFinished() {
                            //You can do anything on successful saving highlight list
                        }
                    });
                }
            }
        }).start();
    }*/
    /*private String loadAssetTextAsString(String name) {
        BufferedReader in = null;
        try {
            StringBuilder buf = new StringBuilder();
            InputStream is = getAssets().open(name);
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ((str = in.readLine()) != null) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                buf.append(str);
            }
            return buf.toString();
        } catch (IOException e) {
            Log.e("HomeActivity", "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.e("HomeActivity", "Error closing asset " + name);
                }
            }
        }
        return null;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        folioReader.unregisterHighlightListener();
    }*/
   /* @Override
    public void onHighlight(HighLight highlight, HighLight.HighLightAction type) {
        Toast.makeText(this,
                "highlight id = " + highlight.getUUID() + " type = " + type,
                Toast.LENGTH_SHORT).show();
    }*/
