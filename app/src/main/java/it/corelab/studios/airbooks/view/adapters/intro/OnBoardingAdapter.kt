package it.corelab.studios.airbooks.view.adapters.intro

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import it.corelab.studios.airbooks.model.intro.FragmentObserver
import it.corelab.studios.airbooks.view.fragment.intro.OnBoardingFragment
import java.util.*

class OnBoardingAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mObservers = FragmentObserver()

    override fun getItem(position: Int): Fragment {
        mObservers.deleteObservers() // Clear existing observers.
        val fragment = OnBoardingFragment()
        mObservers.addObserver(fragment as Observer)
        return OnBoardingFragment.newInstance(position)
    }

    /*fun updateFragments() {
        mObservers.notifyObservers()
    }*/

    override fun getCount(): Int {
        return 4
    }
}