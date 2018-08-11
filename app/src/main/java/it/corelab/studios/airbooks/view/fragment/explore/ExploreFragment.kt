package it.corelab.studios.airbooks.view.fragment.explore

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.explore.ExploreRecentsAdapter
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import it.corelab.studios.airbooks.viewmodel.ViewModelExplore
import kotlinx.android.synthetic.main.explore_fragment.*
import org.jetbrains.anko.support.v4.act
import java.util.*

class ExploreFragment: Fragment(), OnReselectedDelegate, ExploreController{

    private lateinit var viewModel: ViewModelExplore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(act, R.layout.explore_fragment,null).apply {

                viewModel = ViewModelProviders.of(activity!!).get(ViewModelExplore::class.java)

                if (isSectionVisible())setupActionBar()

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")


                getExploreBook("http://airbooks.altervista.org/API/v2/explore/",Locale.getDefault().language, "android",token)

            }

    //HANDLER METHODS

    private fun setupActionBar() = setupActionBar("Explore",1,null)

    override fun onReselected() = setupActionBar()

    //  HOME CONTROLLER METHODS

    override fun getExploreBook(url: String?, lang: String?, os: String?, token: String?) {
        viewModel.getExplore(url!!,lang!!,os!!,token!!)
                ?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { exploreItems->
                    if (exploreItems != null){

                        ViewCompat.setNestedScrollingEnabled(rv_recents, false)

                        rv_recents.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                            override fun canScrollVertically(): Boolean {
                                return false
                            }
                        }
                        rv_recents.setHasFixedSize(true)

                        val recentsAdapter = ExploreRecentsAdapter(exploreItems)
                        rv_recents.adapter = recentsAdapter
                    }
                })
    }
}