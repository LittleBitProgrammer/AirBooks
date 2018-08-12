package it.corelab.studios.airbooks.view.fragment.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import it.corelab.studios.airbooks.view.adapters.home.InfiniteRotationAdapter
import it.corelab.studios.airbooks.view.widget.InfiniteRotationView
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import java.util.*
import it.corelab.studios.airbooks.view.adapters.home.BestOfWeekAdapter
import it.corelab.studios.airbooks.view.adapters.home.CategoriesAdapter
import it.corelab.studios.airbooks.view.adapters.home.ContinueReadAdapter
import it.corelab.studios.airbooks.view.anko.layout.home.Home
import it.corelab.studios.airbooks.viewmodel.ViewModelHome
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx


class HomeFragment: Fragment(), OnReselectedDelegate, HomeController{

    private lateinit var viewModel: ViewModelHome

    private lateinit var mainUI: Home

    private lateinit var viewUI: View

    private lateinit var rotationView: InfiniteRotationView
    private lateinit var continueReading: RecyclerView
    private lateinit var bestBook: RecyclerView
    private lateinit var rvCategories: RecyclerView

    private lateinit var continueReadText: TextView
    private lateinit var bestBookLabel: TextView
    private lateinit var categoriesLabel: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        mainUI = Home()
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        viewModel = ViewModelProviders.of(activity!!).get(ViewModelHome::class.java)

        setupActionBar()

        rotationView = mainUI.infiniteRotationView
        continueReading = mainUI.continueReading
        bestBook = mainUI.bestBookRV
        rvCategories = mainUI.categoriesRV

        continueReadText = mainUI.continueReadLabel
        bestBookLabel = mainUI.bestBookLabel
        categoriesLabel = mainUI.categoriesLabel

        val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)

        val token = sharedPreferences.getString("token", "")

        getHomeFeed("http://airbooks.altervista.org/API/v2/feed/", Locale.getDefault().language, "android",token!!)
        Log.i("SECTION","Home Created")

        return viewUI
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("SECTION", "OnViewCreated")
       // if (isSectionVisible()) setupActionBar()
    }


    //HANDLER METHODS

    private fun setupActionBar() = setupActionBar("Home", 0, null)
    override fun onReselected() = setupActionBar()

    override fun onDestroy() {
        super.onDestroy()

        rotationView.stopAutoScroll()
    }

    //  HOME CONTROLLER METHODS

    override fun getHomeFeed(url: String?, lang: String?, os: String?, token: String?) {
        getShowcase(url, lang, os, token)
        getReadings()
        getBestBook()
        getCategories()
    }

    override fun getShowcase(url: String?, lang: String?, os: String?, token: String?) {
        viewModel.getShowcase(url!!,lang!!,os!!,token!!)
                ?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { showcaseItem->

                    if (showcaseItem != null){

                        Log.i("roberto","$showcaseItem")
                        ViewCompat.setNestedScrollingEnabled(rotationView, false)
                        rotationView.setAdapter(InfiniteRotationAdapter(showcaseItem, ctx))

                    }
                })
    }

    override fun getReadings() {
        viewModel.getReadingBooks()?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { readingItems->

            if (readingItems != null){

                continueReadText.visibility = View.VISIBLE
                continueReadText.isEnabled = true

                ViewCompat.setNestedScrollingEnabled(continueReading, false)
                continueReading.setItemViewCacheSize(20)
                continueReading.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                continueReading.setHasFixedSize(true)

                val snapContinueReadAdapter = ContinueReadAdapter(readingItems,ctx)
                continueReading.adapter = snapContinueReadAdapter

            }else{
                continueReadText.visibility = View.INVISIBLE
                continueReadText.isEnabled = false
            }
        })
    }

    override fun getBestBook() {
        viewModel.getBestOfWeekBooks()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { bestWeekItem->

            if (bestWeekItem != null){

                bestBookLabel.visibility = View.VISIBLE
                bestBookLabel.isEnabled = true

                ViewCompat.setNestedScrollingEnabled(bestBook, false)
                bestBook.setItemViewCacheSize(20)
                bestBook.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                bestBook.setHasFixedSize(true)

                val snapBestOfWeek = BestOfWeekAdapter(bestWeekItem, ctx)
                bestBook.adapter = snapBestOfWeek

            }else{
                bestBookLabel.visibility = View.INVISIBLE
                bestBookLabel.isEnabled = false
            }
        })
    }

    override fun getCategories() {
        viewModel.getCategories()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { categories->

            if ( categories != null){

                categoriesLabel.visibility = View.VISIBLE
                categoriesLabel.isEnabled = true

                ViewCompat.setNestedScrollingEnabled(rvCategories, false)
                rvCategories.setItemViewCacheSize(20)
                rvCategories.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
                rvCategories.setHasFixedSize(true)

                val snapCategoriesAdapter = CategoriesAdapter(categories)
                rvCategories.adapter = snapCategoriesAdapter

            }else{
                categoriesLabel.visibility = View.INVISIBLE
                categoriesLabel.isEnabled = false
            }
        })
    }

}