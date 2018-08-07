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
import android.widget.Button
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.home.InfiniteRotationAdapter
import it.corelab.studios.airbooks.model.data.remote.APIService
import it.corelab.studios.airbooks.model.data.remote.ApiUtils
import it.corelab.studios.airbooks.view.widget.InfiniteRotationView
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import java.util.*
import it.corelab.studios.airbooks.view.adapters.home.BestOfWeekAdapter
import it.corelab.studios.airbooks.view.adapters.home.CategoriesAdapter
import it.corelab.studios.airbooks.view.adapters.home.ContinueReadAdapter
import it.corelab.studios.airbooks.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.home_fragment.view.*
import mehdi.sakout.fancybuttons.FancyButton
import org.jetbrains.anko.support.v4.ctx


class HomeFragment: Fragment(), OnReselectedDelegate, HomeController{

    private lateinit var viewModel: ViewModelHome

    private lateinit var rotationView: InfiniteRotationView
    private lateinit var continueReading: RecyclerView
    private lateinit var bestBook: RecyclerView
    private lateinit var rvCategories: RecyclerView

    private var button: FancyButton? = null

    private var firstColor: String? = null
    private var secondColor: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.home_fragment,null).apply {

                viewModel = ViewModelProviders.of(activity!!).get(ViewModelHome::class.java)

                rotationView = findViewById(R.id.rv_showcase)
                continueReading = findViewById(R.id.rv_continue_reading)
                bestBook = findViewById(R.id.rv_bestweek)
                rvCategories = findViewById(R.id.rv_categories)

                button = activity?.findViewById(R.id.color_button_read_now)


                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)

                val token = sharedPreferences.getString("token", "")
                firstColor = sharedPreferences.getString("firstColor", "")
                secondColor = sharedPreferences.getString("secondColor", "")

                getHomeFeed("http://airbooks.altervista.org/API/v2/feed/", Locale.getDefault().language, "android",token!!)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("HomeFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }


    //HANDLER METHODS

    private fun setupActionBar() = setupActionBar("Home",false,0, firstColor, secondColor)
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
                        rotationView.setAdapter(InfiniteRotationAdapter(showcaseItem))

                    }
                })
    }

    override fun getReadings() {
        viewModel.getReadingBooks()?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { readingItems->

            if (readingItems != null){

                ViewCompat.setNestedScrollingEnabled(continueReading, false)
                continueReading.setItemViewCacheSize(20)
                continueReading.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                continueReading.setHasFixedSize(true)

                val snapContinueReadAdapter = ContinueReadAdapter(readingItems)
                continueReading.adapter = snapContinueReadAdapter

            }
        })
    }

    override fun getBestBook() {
        viewModel.getBestOfWeekBooks()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { bestWeekItem->

            if (bestWeekItem != null){

                ViewCompat.setNestedScrollingEnabled(bestBook, false)
                bestBook.setItemViewCacheSize(20)
                bestBook.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                bestBook.setHasFixedSize(true)

                val snapBestOfWeek = BestOfWeekAdapter(bestWeekItem)
                bestBook.adapter = snapBestOfWeek

            }
        })
    }

    override fun getCategories() {
        viewModel.getCategories()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { categories->

            if ( categories != null){

                ViewCompat.setNestedScrollingEnabled(rvCategories, false)
                rvCategories.setItemViewCacheSize(20)
                rvCategories.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
                rvCategories.setHasFixedSize(true)

                val snapCategoriesAdapter = CategoriesAdapter(categories)
                rvCategories.adapter = snapCategoriesAdapter

            }
        })
    }

}