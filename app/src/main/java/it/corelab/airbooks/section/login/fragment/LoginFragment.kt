package it.corelab.airbooks.section.login.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import java.util.Locale

import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.airbooks.R
import it.corelab.airbooks.fragment.RecoverPassword_Fragment
import it.corelab.airbooks.fragment.SignUp_Fragment
import it.corelab.airbooks.section.MainActivity
import it.corelab.airbooks.data.model.PostSignIn
import it.corelab.airbooks.data.model.PostSignInResponse
import it.corelab.airbooks.data.model.remote.APIService
import it.corelab.airbooks.data.model.remote.ApiUtils
import it.corelab.airbooks.section.login.activity.Login

import android.content.ContentValues.TAG
import android.content.Context
import it.corelab.airbooks.section.login.pages.layout.SignInLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class LoginFragment : Fragment(), View.OnClickListener {

    //VIEW
    private lateinit var viewUI: View


    //FRAGMENT MANAGER
    private var fragmentManagerLogin: FragmentManager? = null


    //VIEW VARIABLES
    private lateinit var forgotPsw: Button
    //private var loginBtn: Button? = null
    //private var signUp: Button? = null
    private lateinit var email: EditText
    private lateinit var password: EditText

    //API
    private var mAPIService: APIService? = null

    //UI INITIALIZATION
    private lateinit var mainUI: SignInLayout

    private val isCredentialValid: Boolean
        get() = isEmailValid(getString(email)) && !isEditTextEmpty(password as EditText)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = SignInLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        mAPIService = ApiUtils.getAPIService()

        initViews()

        setListeners()

        return viewUI
    }

    private fun initViews() {

        fragmentManagerLogin = activity!!.supportFragmentManager

        forgotPsw = mainUI.forgotButton
        //loginBtn = viewLogin.findViewById(R.id.login_btn)
        //signUp = viewLogin.findViewById(R.id.sign_up)
        //email = viewLogin.findViewById(R.id.edit_text)
        //password = viewLogin.findViewById(R.id.password_edit_password)
    }

    private fun setListeners() {

        forgotPsw.setOnClickListener {
            fragmentManagerLogin!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, RecoverPassword_Fragment(), "RecoverPassword_Fragment")
                    .commit()
            setOnLeftArrow()
        }
        //loginBtn!!.setOnClickListener(this)
        //signUp!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (mainUI) {
        /*R.id.sign_up -> {

                // Replace signup frgament with animation// Replace signup frgament with animation
                fragmentManagerLogin!!
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                        .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, SignUp_Fragment(), "SignUp_Fragment")
                        .commit()
                setOnLeftArrow()
            }*/

        /*R.id.login_btn -> {

                //login action
                verifyCredentials()
                if (isCredentialValid) {
                    val postSignIn = PostSignIn(email.text.toString(), password.text.toString())
                    signInPost(postSignIn, "http://airbooks.altervista.org/API/v2/auth/", Locale.getDefault().language, "android")
                }
            }*/
        }
    }


    //utility

    private fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun getString(textInputEditText: EditText): String {
        return textInputEditText.text.toString()
    }

    private fun isEditTextEmpty(editText: EditText): Boolean {
        return editText.length() == 0
    }

    private fun setOnLeftArrow() {
        Login.leftArrow.isEnabled = true
        Login.leftArrow.visibility = View.VISIBLE
    }

    private fun verifyCredentials() {

        if (!isEmailValid(getString(email))) {
            email.error = "you've to insert an email"
        }
        if (isEditTextEmpty(password)) {
            //passwordLayout.setPasswordVisibilityToggleEnabled(false);
            password.error = "Please insert a password"
        }
    }

    fun doIntentToHome() {
        val homeIntent = Intent(activity, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(homeIntent)
        activity!!.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    private fun signInPost(postSignIn: PostSignIn, url: String, lang: String, os: String) {

        val pDialog = SweetAlertDialog(activity!!, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#4990e2")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()

        mAPIService!!.signInPost(postSignIn, url, lang, os).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PostSignInResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(postSignInResponse: PostSignInResponse) {
                pDialog.dismiss()
                if (postSignInResponse.error != null) {
                    Log.i(TAG, "post submitted to API. " + postSignInResponse.error.code!!.toString())
                    showErrorDialog()
                } else {
                    val sharedPreferences = activity!!.getSharedPreferences(activity!!.packageName, Context.MODE_PRIVATE)
                    sharedPreferences.edit().putString("token", postSignInResponse.result.value).apply()
                    doIntentToHome()
                }

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }

    fun showErrorDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Credenziali sbagliate")
                .show()
    }
}
