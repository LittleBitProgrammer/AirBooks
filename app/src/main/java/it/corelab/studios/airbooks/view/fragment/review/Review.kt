package it.corelab.studios.airbooks.view.fragment.review

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.view.anko.layout.review.ReviewView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class Review : Fragment(), OnReselectedDelegate {

    private lateinit var mainUI: ReviewView

    private lateinit var viewUI: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = ReviewView()

        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        return viewUI
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreatedProfile")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("profile",5, null)

    override fun onReselected() = setupActionBar()
}