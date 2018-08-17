package it.corelab.studios.airbooks.view.fragment.library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx

class LibraryFragment: Fragment(), OnReselectedDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(act, R.layout.library_fragment,null).apply {
                Log.d("SECTION", "onCreateViewLibrary")
                if (isSectionVisible()) setupActionBar()

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")

            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreatedLibrary")
        //if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Library", 2,null,null)

    override fun onReselected() = setupActionBar()

}