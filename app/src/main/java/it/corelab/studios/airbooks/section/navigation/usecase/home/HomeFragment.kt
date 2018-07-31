package it.corelab.studios.airbooks.section.navigation.usecase.home

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
import it.corelab.studios.airbooks.adapters.InfiniteRotationAdapter
import it.corelab.studios.airbooks.data.model.HOME.GetHome
import it.corelab.studios.airbooks.data.model.remote.APIService
import it.corelab.studios.airbooks.data.model.remote.ApiUtils
import it.corelab.studios.airbooks.recyclerViewExtension.InfiniteRotationView
import it.corelab.studios.airbooks.section.navigation.common.OnReselectedDelegate
import it.corelab.studios.airbooks.section.navigation.common.isSectionVisible
import it.corelab.studios.airbooks.section.navigation.common.setupActionBar
import java.util.*
import com.google.gson.GsonBuilder
import it.corelab.studios.airbooks.CustomNested
import it.corelab.studios.airbooks.adapters.SnapBestOfWeek
import it.corelab.studios.airbooks.adapters.SnapCategoriesAdapter
import it.corelab.studios.airbooks.adapters.SnapContinueReadAdapter
import it.corelab.studios.airbooks.data.model.HOME.Genre
import it.corelab.studios.airbooks.data.model.HOME.ItemBest
import it.corelab.studios.airbooks.data.model.HOME.ItemReading
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


class HomeFragment: Fragment(), OnReselectedDelegate, HomeController{

    private var mAPIService: APIService? = null

    private lateinit var rotationView: InfiniteRotationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(ctx, R.layout.home_fragment,null).apply {
                Log.d("HomeFragment", "onCreateView")

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")

                mAPIService = ApiUtils.getAPIService()

                rotationView = findViewById(R.id.rv_showcase)

                ViewCompat.setNestedScrollingEnabled(rotationView, false)
                ViewCompat.setNestedScrollingEnabled(rv_continue_reading, false)
                ViewCompat.setNestedScrollingEnabled(rv_bestweek, false)
                ViewCompat.setNestedScrollingEnabled(rv_categories, false)


                rv_continue_reading.setItemViewCacheSize(20)
                rv_continue_reading.isDrawingCacheEnabled = true
                rv_continue_reading.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

                rv_bestweek.setItemViewCacheSize(20)
                rv_bestweek.isDrawingCacheEnabled = true
                rv_bestweek.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH

                rv_categories.setItemViewCacheSize(20)
                rv_categories.isDrawingCacheEnabled = true
                rv_categories.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH


                rv_continue_reading.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                rv_continue_reading.setHasFixedSize(true)

                rv_bestweek.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                rv_bestweek.setHasFixedSize(true)

                rv_categories.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.HORIZONTAL, false)
                rv_categories.setHasFixedSize(true)

                getShowcase("http://airbooks.altervista.org/API/v2/feed/", Locale.getDefault().language, "android",token)

                Log.i("asrubale", "$token ${Locale.getDefault().language}")
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("HomeFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Home")
    override fun onReselected() = setupActionBar()

    override fun onDestroy() {
        super.onDestroy()

        rotationView.stopAutoScroll()
    }

    override fun getShowcase(url: String, lang: String, os: String, token: String) {

        mAPIService!!.getHomeFeed(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetHome> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getHomeResponse: GetHome) {
                Log.i("Home Responsed", "${GsonBuilder().setPrettyPrinting().create().toJson(getHomeResponse)}")

                val showCaseItem: MutableList<it.corelab.studios.airbooks.data.model.HOME.Showcase> = getHomeResponse.result.showcase
                val readingItems: MutableList<ItemReading> = getHomeResponse.result.reading.items
                val bestOfWeekItems: MutableList<ItemBest> = getHomeResponse.result.best.items
                val categoriesItems: MutableList<Genre> = getHomeResponse.result.genres

                if (getHomeResponse.error == null) {

                    if (getHomeResponse.result.showcase != null) {
                        rotationView.setAdapter(InfiniteRotationAdapter(showCaseItem))
                    }

                    if (getHomeResponse.result.reading != null) {
                        val snapContinueReadAdapter = SnapContinueReadAdapter(activity, readingItems)
                        rv_continue_reading.adapter = snapContinueReadAdapter
                    }

                    if (getHomeResponse.result.best != null) {
                        val snapBestOfWeek = SnapBestOfWeek(activity, bestOfWeekItems)
                        rv_bestweek.adapter = snapBestOfWeek
                    }

                    if (getHomeResponse.result.genres != null){
                        val snapCategoriesAdapter = SnapCategoriesAdapter(activity, categoriesItems)
                        rv_categories.adapter = snapCategoriesAdapter
                    }

                }else{
                    toast("Sorry, we have a problem. Try later.")
                }

            }

            override fun onError(e: Throwable) {

                Log.i("erfinelgildo", e.toString())
            }

            override fun onComplete() {

            }
        })
    }
}