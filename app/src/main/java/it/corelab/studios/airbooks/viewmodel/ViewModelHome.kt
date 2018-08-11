package it.corelab.studios.airbooks.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.model.data.HOME.*
import it.corelab.studios.airbooks.model.API.remote.ApiUtils

class ViewModelHome : ViewModel() {

    private val mAPIService = ApiUtils.getAPIService()

    private var error: Any? = null

    private var showcaseItem: MutableLiveData<List<Showcase>>? = null
    private var readingItem: MutableLiveData<List<ItemReading>>? = MutableLiveData()
    private var bestWeekItem: MutableLiveData<List<ItemBest>>? = MutableLiveData()
    private var categories: MutableLiveData<List<Genre>>? = MutableLiveData()


    fun getShowcase(url: String, lang: String, os: String, token: String): LiveData<List<Showcase>>? {
        if (showcaseItem == null) {
            showcaseItem = MutableLiveData()
            loadShowcase(url, lang, os, token)
        }

        return showcaseItem
    }

    fun getReadingBooks(): LiveData<List<ItemReading>>? {

        return readingItem
    }

    fun getBestOfWeekBooks(): LiveData<List<ItemBest>>? {

        return bestWeekItem
    }

    fun getCategories():LiveData<List<Genre>>? {

        return categories
    }

    private fun loadShowcase(url: String, lang: String, os: String, token: String) {
        mAPIService!!.getHomeFeed(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetHome> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getHomeResponse: GetHome) {

                error = getHomeResponse.error
                showcaseItem?.value = getHomeResponse.result.showcase
                readingItem?.value = getHomeResponse.result.reading.items
                bestWeekItem?.value = getHomeResponse.result.best.items
                categories?.value = getHomeResponse.result.genres

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }
}
