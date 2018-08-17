package it.corelab.studios.airbooks.view.fragment.showcase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import org.jetbrains.anko.support.v4.act

class ShowcaseDetail: Fragment(), OnReselectedDelegate {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(act, R.layout.profile_fragment,null).apply {
                if (isSectionVisible()) setupActionBar()
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        //if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("profile",3, null,null)

    override fun onReselected() = setupActionBar()
}