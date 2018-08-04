package it.corelab.studios.airbooks.section.navigation.usecase.profile

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

class ProfileFragment: Fragment(), OnReselectedDelegate{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(activity, R.layout.profile_fragment,null).apply {
                Log.d("ProfileFragment", "onCreateView")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("ProfileFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Profile",false,0)

    override fun onReselected() = setupActionBar()
}