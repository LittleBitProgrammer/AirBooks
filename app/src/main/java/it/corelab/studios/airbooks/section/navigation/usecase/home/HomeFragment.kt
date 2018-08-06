package it.corelab.studios.airbooks.section.navigation.usecase.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.adapters.home.InfiniteRotationAdapter
import it.corelab.studios.airbooks.data.model.HOME.GetHome
import it.corelab.studios.airbooks.data.model.remote.APIService
import it.corelab.studios.airbooks.data.model.remote.ApiUtils
import it.corelab.studios.airbooks.recyclerViewExtension.InfiniteRotationView
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.isSectionVisible
import it.corelab.studios.airbooks.section.navigation.common.setupActionBar
import java.util.*
import com.google.gson.GsonBuilder
import it.corelab.studios.airbooks.adapters.home.BestOfWeekAdapter
import it.corelab.studios.airbooks.adapters.home.CategoriesAdapter
import it.corelab.studios.airbooks.adapters.home.ContinueReadAdapter
import it.corelab.studios.airbooks.data.model.HOME.Genre
import it.corelab.studios.airbooks.data.model.HOME.ItemBest
import it.corelab.studios.airbooks.data.model.HOME.ItemReading
import it.corelab.studios.airbooks.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


class HomeFragment: Fragment(), OnReselectedDelegate, HomeController{

    private lateinit var viewModel: ViewModelHome

    private var mAPIService: APIService? = null

    private lateinit var rotationView: InfiniteRotationView
    private var firstColor: String? = null
    private var secondColor: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.home_fragment,null).apply {
                Log.d("HomeFragment", "onCreateView")

                viewModel = ViewModelProviders.of(activity!!).get(ViewModelHome::class.java)

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")
                firstColor = sharedPreferences.getString("firstColor", "")
                secondColor = sharedPreferences.getString("secondColor", "")

                mAPIService = ApiUtils.getAPIService()

                rotationView = findViewById(R.id.rv_showcase)

                //getHomeFeed("http://airbooks.altervista.org/API/v2/feed/", Locale.getDefault().language, "android",token)

                viewModel.getShowcase("http://airbooks.altervista.org/API/v2/feed/", Locale.getDefault().language, "android",token)
                        ?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { showcaseItem->

                            Log.i("roberto","$showcaseItem")
                            ViewCompat.setNestedScrollingEnabled(rotationView, false)
                            rotationView.setAdapter(InfiniteRotationAdapter(showcaseItem!!))
                        })


                viewModel.getReadingBooks()?.observe(viewLifecycleOwner, android.arch.lifecycle.Observer { readingItems->

                    ViewCompat.setNestedScrollingEnabled(rv_continue_reading, false)
                    rv_continue_reading.setItemViewCacheSize(20)
                    rv_continue_reading.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    rv_continue_reading.setHasFixedSize(true)

                    val snapContinueReadAdapter = ContinueReadAdapter(readingItems!!)
                    rv_continue_reading.adapter = snapContinueReadAdapter
                })

                viewModel.getBestOfWeekBooks()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { bestWeekItem->

                    ViewCompat.setNestedScrollingEnabled(rv_bestweek, false)
                    rv_bestweek.setItemViewCacheSize(20)
                    rv_bestweek.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                        override fun canScrollVertically(): Boolean {
                            return false
                        }
                    }
                    rv_bestweek.setHasFixedSize(true)

                    val snapBestOfWeek = BestOfWeekAdapter(bestWeekItem!!)
                    rv_bestweek.adapter = snapBestOfWeek
                })

                viewModel.getCategories()?.observe(viewLifecycleOwner,android.arch.lifecycle.Observer { categories->

                    ViewCompat.setNestedScrollingEnabled(rv_categories, false)
                    rv_categories.setItemViewCacheSize(20)
                    rv_categories.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
                    rv_categories.setHasFixedSize(true)

                    val snapCategoriesAdapter = CategoriesAdapter(categories!!)
                    rv_categories.adapter = snapCategoriesAdapter
                })

            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("HomeFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Home",false,0, firstColor, secondColor)
    override fun onReselected() = setupActionBar()

    override fun onDestroy() {
        super.onDestroy()

        rotationView.stopAutoScroll()
    }

    override fun getHomeFeed(url: String?, lang: String?, os: String?, token: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}