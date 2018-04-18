package it.corelab.airbooks.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import it.corelab.airbooks.IntroActivity;
import it.corelab.airbooks.fragment.FadeFragment;
import it.corelab.airbooks.R;
import it.corelab.airbooks.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private FadeFragment currentFragment;
    private ViewPagerAdapter adapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private Handler handler = new Handler();

    // UI

    private AHBottomNavigationViewPager viewPager;
    public static AHBottomNavigation bottomNavigation;


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

        //if is the first time launch app initialize tutorial view
       boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun){
            //show start activity

            startActivity(new Intent(MainActivity.this, IntroActivity.class));
            Toast.makeText(this, "First Run", Toast.LENGTH_SHORT).show();
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        boolean enabledTranslucentNavigation = getSharedPreferences("shared", Context.MODE_PRIVATE)
                .getBoolean("translucentNavigation", false);
        setTheme(enabledTranslucentNavigation ? R.style.AppTheme_TranslucentNavigation : R.style.AppTheme);
        setContentView(R.layout.activity_main);
        initUI();


        //==========================
        //      hide status bar
        //==========================

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //nothing
        Log.i("BACK", "you have pressed back, i'm minimazing AirBooks");
        minimizeApp();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public void minimizeApp(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startActivity(startMain);
        this.finish();
    }



                                   /*      on create finish       */
//=================================================================================================
                                  /*      on create finish       */




    //============================================================================================//
    //                                      methods_main                                          //
    //============================================================================================//


    /*
    init UI
     */

    private void initUI(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        }

        bottomNavigation= findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);


        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1, R.drawable.icona_home, R.color.accent_color);

        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2, R.drawable.icona_esplora_piena, R.color.colorPrimary);

        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3, R.drawable.icona_segnalibro, R.color.inactive_color);

        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab4, R.drawable.icona_profilo,R.color.blu_airbooks);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);

        bottomNavigation.addItems(bottomNavigationItems);

        bottomNavigation.setTranslucentNavigationEnabled(true);

        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#4A88AC"));
        bottomNavigation.setInactiveColor(Color.parseColor("#E1E4E9"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomNavigation.setElevation(0);
        }

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = adapter.getCurrentFragment();
                }

                if (wasSelected) {
                    currentFragment.refresh();
                    return true;
                }

                if (currentFragment != null) {
                    //currentFragment.willBeHidden();
                    Log.i("FRAGMENT", "fragment will be hidden");
                }

                viewPager.setCurrentItem(position, false);

                if (currentFragment == null) {
                    return true;
                }

                currentFragment = adapter.getCurrentFragment();
                //currentFragment.willBeDisplayed();

                return true;
            }
        });

        viewPager.setOffscreenPageLimit(3);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
    }
}