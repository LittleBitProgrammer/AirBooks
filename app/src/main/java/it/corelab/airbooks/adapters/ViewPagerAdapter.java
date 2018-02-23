package it.corelab.airbooks.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.corelab.airbooks.fragment.FadeFragment;

/**
 * Created by Roberto_Vecchio on 11/02/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<FadeFragment> fragments = new ArrayList<>();
    private FadeFragment currentFragment;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);

        fragments.clear();
        fragments.add(FadeFragment.newInstance(0));
        fragments.add(FadeFragment.newInstance(1));
        fragments.add(FadeFragment.newInstance(2));
        fragments.add(FadeFragment.newInstance(3));
    }

    @Override
    public FadeFragment getItem(int position){
        return fragments.get(position);
    }

    @Override
    public int getCount(){
        return fragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object){
        if(getCurrentFragment() != object){
            currentFragment =((FadeFragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    /*
    get the current fragment
     */

    public FadeFragment getCurrentFragment(){
        return currentFragment;
    }

}
