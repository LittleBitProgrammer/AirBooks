package it.corelab.studios.airbooks.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.model.API.remote.ApiUtils
import it.corelab.studios.airbooks.model.data.USER.ACTIVE.GetActiveUser

class ViewModelProfile: ViewModel() {

    private val mAPIService = ApiUtils.getAPIService()

    private var name: MutableLiveData<String>? = MutableLiveData()

    fun loadProfile(url: String, lang: String, os: String, token: String){
        loadActiveProfile(url, lang, os, token)
    }

    fun getName():MutableLiveData<String>{
        //Log.i("ASDRUBALE", name?.value)
        return name!!
    }

    private fun loadActiveProfile(url: String, lang: String, os: String, token: String){
        mAPIService.getActiveUser(url,lang,os,token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<GetActiveUser>{

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(getActiveUser: GetActiveUser) {
                name?.value = "${getActiveUser.result.firstName} ${getActiveUser.result.lastName}"
                Log.i("ASDRUBALE", getActiveUser.result.firstName)
            }

            override fun onError(e: Throwable) {
                Log.i("ERROR", e.toString())
            }

            override fun onComplete() {

            }
        })
    }
}