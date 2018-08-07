package it.corelab.studios.airbooks.view.fragment.library

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar

class LibraryFragment: Fragment(), OnReselectedDelegate {

    private var firstColor: String? = null
    private var secondColor: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(activity, R.layout.library_fragment,null).apply {
                Log.d("LibraryFragment", "onCreateView")

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")
                firstColor = sharedPreferences.getString("firstColor", "")
                secondColor = sharedPreferences.getString("secondColor", "")

                Log.i("COLOR", "$firstColor")
                Log.i("COLOR", "$secondColor")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("LibraryFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Library",false,2,firstColor,secondColor)

    override fun onReselected() = setupActionBar()

}