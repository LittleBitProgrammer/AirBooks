package it.corelab.airbooks.section.intro.handler

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
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