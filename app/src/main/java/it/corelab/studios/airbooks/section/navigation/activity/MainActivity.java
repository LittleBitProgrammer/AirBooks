package it.corelab.studios.airbooks.section.navigation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;


import cn.pedant.SweetAlert.SweetAlertDialog;
import it.corelab.studios.airbooks.fragment.FadeFragment;
import it.corelab.studios.airbooks.R;
import it.corelab.studios.airbooks.adapters.ViewPagerAdapter;

import static it.corelab.studios.airbooks.CustomNested.yHomePosition;
import static it.corelab.studios.airbooks.fragment.FadeFragment.angleVariation;
import static it.corelab.studios.airbooks.fragment.FadeFragment.yPosition;

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

        bottomNavigation.setElevation(0);

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
                    switch (currentFragment.getArguments().getInt("index", 0)){
                        case 0:
                            //takeYPosition(diagonalView);
                            break;
                        case 1:
                            //takeYPosition(exploreDiagonal);
                            break;
                        case 2:
                            //takeYPosition(libDiagonal);
                            break;
                        case 3:
                            //takeYPosition(profileDiagonal);
                            break;
                    }
                    Log.i("FRAGMENT", "fragment will be hidden");
                }

                viewPager.setCurrentItem(position, false);

                if (currentFragment == null) {
                    return true;
                }

                currentFragment = adapter.getCurrentFragment();
                currentFragment.willBeDisplayed();



                switch (currentFragment.getArguments().getInt("index", 0)){
                    case 0:

                        if ( yHomePosition < 0.0 ){
                            //FadeFragment.diagonalView.setY(- yHomePosition);
                            //diagonalView.animate().translationY(6).setInterpolator(new DecelerateInterpolator());
                        }

                        break;
                    case 1:

                        if (angleVariation <= 16.0){
                            for ( float i = angleVariation; i <= 16.0f; i += 0.1){
                                if (i <= 16.0) {
                                    //exploreDiagonal.setAngle(i);
                                    Log.i("ANGLE", "" + i );
                                }
                            }
                        }

                        if (yPosition <= 0) {
                            //exploreDiagonal.setY(yPosition);
                            //exploreDiagonal.animate().translationY(0).setInterpolator(new DecelerateInterpolator());
                        }



                        break;
                    case 2:
                        if (angleVariation <= 16.0){
                            for ( float i = angleVariation; i <= 16.0f; i += 0.1){
                                if (i <= 16.0) {
                                    //libDiagonal.setAngle(i);
                                    Log.i("ANGLE", "" + i );
                                }
                            }
                        }

                        if (yPosition <= 0) {
                            //libDiagonal.setY(yPosition);
                            //libDiagonal.animate().translationY(0).setInterpolator(new DecelerateInterpolator());
                        }

                        //takeYPosition(libDiagonal);
                        break;
                    case 3:
                        if (angleVariation <= 16.0){
                            for ( float i = angleVariation; i <= 16.0f; i += 0.1){
                                if (i <= 16.0) {
                                    //profileDiagonal.setAngle(i);
                                    Log.i("ANGLE", "" + i );
                                }
                            }
                        }

                        if (yPosition <= 0) {
                            //profileDiagonal.setY(yPosition);
                            //profileDiagonal.animate().translationY(0).setInterpolator(new DecelerateInterpolator());
                        }

                        //takeYPosition(profileDiagonal);
                        break;
                }



                return true;
            }
        });

        viewPager.setOffscreenPageLimit(3);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        currentFragment = adapter.getCurrentFragment();
    }

    public void takeYPosition(final View view) {
        int[] xy = new int[2];
        view.getLocationOnScreen(xy);
        yPosition = xy[1];
        Log.i("YPOSITION: ", "" + yPosition);
    }

    public void showErrorDialog(){
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Credenziali sbagliate")
                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof TextInputEditText) {
                if (!isPointInsideView(event.getRawX(), event.getRawY(), v)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    v.clearFocus();
                }
            }
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

    /**
     * Determines if given points are inside view
     * @param x - x coordinate of point
     * @param y - y coordinate of point
     * @param view - view object to compare
     * @return true if the points are within view bounds, false otherwise
     */
    private boolean isPointInsideView(float x, float y, View view) {
        int location[] = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];

        // point is inside view bounds
        return ((x > viewX && x < (viewX + view.getWidth())) &&
                (y > viewY && y < (viewY + view.getHeight())));
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        final View v = getCurrentFocus();
        if (keyCode == KeyEvent.KEYCODE_ENTER && v instanceof EditText) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.postDelayed(new Runnable() {

                @Override
                public void run() {
                    v.clearFocus();
                }

            }, 100);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}