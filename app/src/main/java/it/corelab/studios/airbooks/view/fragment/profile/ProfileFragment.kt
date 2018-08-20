package it.corelab.studios.airbooks.view.fragment.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.general.main.isSectionVisible
import it.corelab.studios.airbooks.model.general.main.setupActionBar
import org.jetbrains.anko.support.v4.act

class ProfileFragment: Fragment(), OnReselectedDelegate {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(act, R.layout.profile_fragment,null).apply {
                Log.d("SECTION", "onCreateViewProfile")
                if (isSectionVisible()) setupActionBar()
                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreatedProfile")
        //if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("profile",3, null,null)

    override fun onReselected() = setupActionBar()
}