package it.corelab.airbooks;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.folioreader.util.FolioReader;

import it.corelab.airbooks.mFragments.ExploreFragment;
import it.corelab.airbooks.mFragments.HomeFragment;
import it.corelab.airbooks.mFragments.LibraryFragment;
import it.corelab.airbooks.mFragments.ProfileFragment;
//serve a implementare lettore epub

public class MainActivity extends AppCompatActivity /*implements AHBottomNavigation.OnTabSelectedListener*//*implements OnHighlightListener */{


    //=============================
    //  class variable declaration
    //=============================

    private FolioReader folioReader;

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


        //==========================
        //        drawable
        //==========================

        Drawable home = resources.getDrawable(R.drawable.icona_home);
        Drawable explore = resources.getDrawable(R.drawable.icona_esplora_piena);
        Drawable library = resources.getDrawable(R.drawable.icona_segnalibro);
        Drawable profile = resources.getDrawable(R.drawable.icona_profilo);

        //==========================
        //          Color
        //==========================

        int accentColor = resources.getColor(R.color.accent_color);
        int inactiveColor = resources.getColor(R.color.inactive_color);
        int intestattionColor = resources.getColor(R.color.intestazioni);


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
        //  ActionBar customization
        //==========================

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        getSupportActionBar().setElevation(0);

        //==========================
        // BottomBar customization
        //==========================

        bottomNavigation.setUseElevation(false);
        bottomNavigation.setColored(false);
        bottomNavigation.setSoundEffectsEnabled(true);
        bottomNavigation.setCurrentItem(0);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                // Do something cool here...
                if (position == 0){
                    HomeFragment homeFragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();


                    //==========================
                    //     Home ActionBar
                    //==========================

                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(R.layout.abs_layout);
                    getSupportActionBar().setElevation(0);
                }else if (position == 1){
                    ExploreFragment exploreFragment = new ExploreFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, exploreFragment).commit();


                    //==========================
                    //    Explore ActionBar
                    //==========================

                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(R.layout.explore_action_bar_layout);
                    getSupportActionBar().setElevation(0);
                }else if (position == 2){
                    LibraryFragment libraryFragment = new LibraryFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, libraryFragment).commit();


                    //==========================
                    //     Library ActionBar
                    //==========================

                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(R.layout.library_action_bar_layout);
                    getSupportActionBar().setElevation(0);
                }else if (position == 3){
                    ProfileFragment profileFragment = new ProfileFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, profileFragment).commit();


                    //==========================
                    //     Profile ActionBar
                    //==========================

                    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    getSupportActionBar().setCustomView(R.layout.profile_action_bar_layout);
                    getSupportActionBar().setElevation(0);
                }
                return true;
            }
        });




        // instanza di folioReader
        //uncomment per lettura epub

        /*
        FolioReader folioReader = new FolioReader(this);
        folioReader.registerHighlightListener(this);
        folioReader.openBook(R.raw.canzone);
        getHighlightsAndSave();
        */
    }


    //=============================
    //            methods
    //=============================


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
}
