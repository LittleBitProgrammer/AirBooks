package it.corelab.studios.airbooks.view.fragment.explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.adapters.explore.ExploreRecentsAdapter
import it.corelab.studios.airbooks.model.data.EXPLORE.GetExplore
import it.corelab.studios.airbooks.model.data.EXPLORE.Item
import it.corelab.studios.airbooks.model.data.remote.APIService
import it.corelab.studios.airbooks.model.data.remote.ApiUtils
import it.corelab.studios.airbooks.model.interfaces.main.OnReselectedDelegate
import it.corelab.studios.airbooks.model.General.Main.isSectionVisible
import it.corelab.studios.airbooks.model.General.Main.setupActionBar
import kotlinx.android.synthetic.main.explore_fragment.*
import kotlinx.android.synthetic.main.explore_fragment.view.*
import org.jetbrains.anko.support.v4.toast
import java.util.*

class ExploreFragment: Fragment(), OnReselectedDelegate, ExploreController{

    private var mAPIService: APIService? = null

    private var firstColor: String? = null
    private var secondColor: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            View.inflate(activity, R.layout.explore_fragment,null).apply {
                Log.d("ExploreFragment", "onCreateView")

                mAPIService = ApiUtils.getAPIService()

                val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, android.content.Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "")
                firstColor = sharedPreferences.getString("firstColor", "")
                secondColor = sharedPreferences.getString("secondColor", "")

                ViewCompat.setNestedScrollingEnabled(rv_recents, false)

                rv_recents.setItemViewCacheSize(20)

                rv_recents.layoutManager = object : GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                rv_recents.setHasFixedSize(true)

                getExploreBook("http://airbooks.altervista.org/API/v2/explore/",Locale.getDefault().language, "android",token)
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        Log.d("ExploreFragment", "OnViewCreated")
        if (isSectionVisible()) setupActionBar()
    }

    private fun setupActionBar() = setupActionBar("Explore",false,1,firstColor,secondColor)

    override fun onReselected() = setupActionBar()

    override fun getExploreBook(url: String?, lang: String?, os: String?, token: String?) {
        mAPIService!!.getExploreBook(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetExplore> {

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getExploreResponse: GetExplore) {
                Log.i("Explore Response", "${GsonBuilder().setPrettyPrinting().create().toJson(getExploreResponse.result.recents.items)}")

                val recentsItems: MutableList<Item> = getExploreResponse.result.recents.items

                if (getExploreResponse.error == null) {


                    if (getExploreResponse.result.recents.items != null) {
                        val recentsAdapter = ExploreRecentsAdapter(recentsItems)
                        rv_recents.adapter = recentsAdapter
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