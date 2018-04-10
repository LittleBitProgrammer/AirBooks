package it.corelab.airbooks;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_fragment_layout);

        mViewPager = findViewById(R.id.viewpager);

        //Set an adapter on the ViewPager
        mViewPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));

        //Set a PageTransformer
        mViewPager.setPageTransformer(false, new IntroPageTransformer());
    }
}
