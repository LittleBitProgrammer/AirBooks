package it.corelab.studios.airbooks.section.navigation.usecase.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.isSectionVisible
import it.corelab.studios.airbooks.section.navigation.common.setupActionBar

class HomeFragment: Fragment(), OnReselectedDelegate{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(activity, R.layout.home_fragment,null).apply {
                Log.d("HomeFragment", "onCreateView")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("HomeFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Home")
    override fun onReselected() = setupActionBar()
}