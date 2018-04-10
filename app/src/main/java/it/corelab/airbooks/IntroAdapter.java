package it.corelab.airbooks;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        /*switch (position){
            case 0:
                return IntroFragment.newInstance(Color.parseColor("#03A9F4"), position); //blue
            default:
                return IntroFragment.newInstance(Color.parseColor("#4CAF50"), position); //blue
        }*/
        return IntroFragment.newInstance(Color.parseColor("#00000000"), position); //transparent
    }

    @Override
    public int getCount(){
        return 5;
    }
}
