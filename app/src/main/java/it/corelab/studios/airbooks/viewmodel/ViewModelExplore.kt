package it.corelab.studios.airbooks.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.model.data.EXPLORE.GetExplore
import it.corelab.studios.airbooks.model.data.EXPLORE.Item
import it.corelab.studios.airbooks.model.API.remote.ApiUtils

class ViewModelExplore: ViewModel(){

    private val mAPIService = ApiUtils.getAPIService()

    private var exploreItems: MutableLiveData<List<Item>>? = null

    fun getExplore(url: String, lang: String, os: String, token: String): LiveData<List<Item>>?{
        if (exploreItems == null){
            exploreItems = MutableLiveData()
            loadExplore(url, lang, os, token)
        }
        return exploreItems
    }

    private fun loadExplore(url: String, lang: String, os: String, token: String){
        mAPIService!!.getExploreBook(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetExplore> {

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getExploreResponse: GetExplore) {

                exploreItems?.value = getExploreResponse.result.recents.items
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }
}