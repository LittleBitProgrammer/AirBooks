package it.corelab.airbooks.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import java.util.Locale

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.airbooks.Intro.IntroActivity
import it.corelab.airbooks.data.model.AutomaticSignInResponse
import it.corelab.airbooks.data.model.remote.APIService
import it.corelab.airbooks.data.model.remote.ApiUtils

class SplashActivity : AppCompatActivity() {
    private var mAPIService: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAPIService = ApiUtils.getAPIService()

        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

        Log.i("SPLASH AVVIATO", "TRUE")

        if (isFirstRun) {
            //show start activity

            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            Log.i("PRIMA RUN", "TRUE")


        } else {

            val sharedPreferences = this.getSharedPreferences(this.packageName, android.content.Context.MODE_PRIVATE)
            val token = sharedPreferences.getString("token", "")

            Log.i("PRIMA RUN", "FALSE")

            if (token!!.equals("", ignoreCase = true)) {
                // Show a log in dialog and/or other stuff to let user log in
                Log.i("TOKEN VUOTO", "TRUE")
                doIntentToLogin()
            } else {
                Log.i("TOKEN VUOTO", "FALSE")
                automaticSignInPost("http://airbooks.altervista.org/API/v2/auth/", Locale.getDefault().language, "android", token)
                Log.i("TOKEN", token)
            }
        }

        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply()
    }


    fun doIntentToLogin() {
        val loginIntent = Intent(this@SplashActivity, Login::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(loginIntent)
    }

    fun automaticSignInPost(url: String, lang: String, os: String, token: String) {

        mAPIService!!.automaticSignin(url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<AutomaticSignInResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(automaticSignInResponse: AutomaticSignInResponse) {
                if (automaticSignInResponse.error != null) {
                    Log.i("ERRORE", "post submitted to API. " + automaticSignInResponse.error.code!!.toString())
                    doIntentToLogin()
                } else {
                    Log.i("NESSUN ERROR START HOME", "TRUE")
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }
}
