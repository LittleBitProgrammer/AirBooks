package it.corelab.airbooks.Intro

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class OnboardingAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val mObservers = FragmentObserver()

    override fun getItem(position: Int): Fragment {
        mObservers.deleteObservers() // Clear existing observers.
        val fragment = OnboardingFragment()
        mObservers.addObserver(fragment as Observer)
        return OnboardingFragment.newInstance(position)
    }

    fun updateFragments() {
        mObservers.notifyObservers()
    }

    override fun getCount(): Int {
        return 4
    }
}