package it.corelab.airbooks.splash.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.View
import android.widget.ImageView

import java.util.Locale

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.airbooks.R
import it.corelab.airbooks.activity.Login
import it.corelab.airbooks.activity.MainActivity
import it.corelab.airbooks.intro.activity.IntroActivity
import it.corelab.airbooks.data.model.AutomaticSignInResponse
import it.corelab.airbooks.data.model.remote.APIService
import it.corelab.airbooks.data.model.remote.ApiUtils
import it.corelab.airbooks.splash.layout.SplashScreenLayout
import org.jetbrains.anko.setContentView
import android.support.v4.view.ViewCompat.animate
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import java.lang.reflect.Array


class SplashActivity : AppCompatActivity() {
    val STARTUP_DELAY = 300
    val ANIM_ITEM_DURATION = 1000
    val ITEM_DELAY = 300

    private val animationStarted = false

    private var mAPIService: APIService? = null
    private lateinit var centralLogo: ImageView
    private lateinit var container: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        SplashScreenLayout().setContentView(this)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        if (!hasFocus || animationStarted) {
            return
        }

        animate()

        super.onWindowFocusChanged(hasFocus)
    }


    fun doIntentToLogin() {
        val loginIntent = Intent(this@SplashActivity, Login::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(loginIntent)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
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

    fun animate(){
        centralLogo = findViewById(R.id.CENTRAL_LOGO)
        container = findViewById(R.id.LINEAR_INTERNAL_SPLASH_SCREEN)

        val elements = intArrayOf(0,1)

        ViewCompat.animate(centralLogo)
                .translationY(-250F)
                .setStartDelay(STARTUP_DELAY.toLong())
                .setDuration(ANIM_ITEM_DURATION.toLong())
                .setInterpolator(DecelerateInterpolator(1.2F))
                .start()

        for (element: Int in elements){
             val v: View = container.getChildAt(element)
             var viewAnimator: ViewPropertyAnimatorCompat

             viewAnimator = ViewCompat.animate(v)
                    .translationY(50F)
                    .alpha(1F)
                    .setStartDelay((STARTUP_DELAY * element).toLong() + 500)
                    .setDuration(1000)

            viewAnimator.setInterpolator(DecelerateInterpolator())
                    .withEndAction { mAPIService = ApiUtils.getAPIService()

                        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

                        Log.i("SPLASH AVVIATO", "TRUE")

                        if (isFirstRun) {
                            //show start activity

                            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
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

                        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply() }
                    .start()

        }
    }
}
