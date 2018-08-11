package it.corelab.studios.airbooks.view.activity.splash

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.View
import android.widget.ImageView

import java.util.Locale

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.view.activity.login.Login
import it.corelab.studios.airbooks.view.activity.main.MainActivity
import it.corelab.studios.airbooks.view.activity.intro.IntroActivity
import it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN.AutomaticSignInResponse
import it.corelab.studios.airbooks.model.API.remote.APIService
import it.corelab.studios.airbooks.model.API.remote.ApiUtils
import it.corelab.studios.airbooks.view.anko.layout.splash.SplashScreenLayout
import org.jetbrains.anko.setContentView
import android.support.v4.view.ViewPropertyAnimatorCompat
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import it.corelab.studios.airbooks.model.interfaces.splash.AnimationControllerSpalshScreen
import it.corelab.studios.airbooks.model.interfaces.splash.AnimationControllerSpalshScreen.ANIM_ITEM_DURATION
import it.corelab.studios.airbooks.model.interfaces.splash.AnimationControllerSpalshScreen.STARTUP_DELAY
import it.corelab.studios.airbooks.model.interfaces.splash.AutomaticSignInController


class SplashActivity : AppCompatActivity(), AnimationControllerSpalshScreen, AutomaticSignInController {

    //BOOLEAN VARIABLES
    private val animationStarted = false

    //LOGIN VARIABLES
    private var mAPIService: APIService? = null

    //LAYOUT VARIABLES
    private lateinit var centralLogo: ImageView
    private lateinit var container: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setTheme(R.style.SplashTheme)
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


    override fun doIntentToLogin() {
        val loginIntent = Intent(this@SplashActivity, Login::class.java)
        loginIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(loginIntent)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

    override fun automaticSignIn(url: String, lang: String, os: String, token: String) {

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


    //METHOD FOR THE ANIMATION OF THE INTRO SCREEN
    override fun animate(){
        centralLogo = findViewById(R.id.CENTRAL_LOGO)
        container = findViewById(R.id.LINEAR_INTERNAL_SPLASH_SCREEN)

        val sharedPreferences = getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.putString("firstColor", "586BA4")?.apply()
        sharedPreferences?.edit()?.putString("secondColor", "4299B0")?.apply()

        val elements = intArrayOf(0,1)

        ViewCompat.animate(centralLogo)
                .translationY(-250F)
                .setStartDelay(STARTUP_DELAY.toLong())
                .setDuration(ANIM_ITEM_DURATION.toLong())
                .setInterpolator(DecelerateInterpolator(1.2F))
                .withEndAction {

                    val handler = Handler()
                    handler.postDelayed({
                        mAPIService = ApiUtils.getAPIService()

                        val isFirstRun = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

                        Log.i("SPLASH AVVIATO", "TRUE")

                        if (isFirstRun) {
                            //show start activity

                            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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
                                automaticSignIn("http://airbooks.altervista.org/API/v2/auth/", Locale.getDefault().language, "android", token)
                                Log.i("TOKEN", token)
                            }
                        }

                        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply()
                    }, 300)
                }.start()

        for (element: Int in elements){
             val v: View = container.getChildAt(element)
             var viewAnimator: ViewPropertyAnimatorCompat

             viewAnimator = ViewCompat.animate(v)
                    .translationY(50F)
                    .alpha(1F)
                    .setStartDelay((STARTUP_DELAY * element).toLong() + 500)
                    .setDuration(1000)
                     .setInterpolator(DecelerateInterpolator())

            viewAnimator.start()

        }
    }
}
