package it.corelab.studios.airbooks.section.navigation.usecase.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.`object`.Item
import it.corelab.studios.airbooks.`object`.Showcase
import it.corelab.studios.airbooks.adapters.InfiniteRotationAdapter
import it.corelab.studios.airbooks.recyclerViewExtension.InfiniteRotationView
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.isSectionVisible
import it.corelab.studios.airbooks.section.navigation.common.setupActionBar
import java.util.ArrayList

class HomeFragment: Fragment(), OnReselectedDelegate{

    private lateinit var showcaseCardItem: ArrayList<Showcase>
    private lateinit var rvContinueReadItem: ArrayList<Item>
    private lateinit var rotationView: InfiniteRotationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(activity, R.layout.home_fragment,null).apply {
                Log.d("HomeFragment", "onCreateView")

                rotationView = findViewById(R.id.rv_showcase)

                createShowcaseCard()

                rotationView.setAdapter(InfiniteRotationAdapter(showcaseCardItem))

                ViewCompat.setNestedScrollingEnabled(rotationView, false)

                rotationView.autoScroll(3, 3000)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("HomeFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Home")
    override fun onReselected() = setupActionBar()

    private fun createShowcaseCard() {

        showcaseCardItem = ArrayList()

        showcaseCardItem.add(Showcase(R.drawable.place_holder_sinistra))
        showcaseCardItem.add(Showcase(R.drawable.place_holder_centro))
        showcaseCardItem.add(Showcase(R.drawable.place_holder_destra))

    }

    override fun onDestroy() {
        super.onDestroy()

        rotationView.stopAutoScroll()
    }
}