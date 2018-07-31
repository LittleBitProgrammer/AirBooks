package it.corelab.studios.airbooks.section.login.fragment

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

import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.section.navigation.activity.MainActivity
import it.corelab.studios.airbooks.data.model.LOGIN.SIGNIN.PostSignIn
import it.corelab.studios.airbooks.data.model.LOGIN.SIGNIN.PostSignInResponse
import it.corelab.studios.airbooks.data.model.remote.APIService
import it.corelab.studios.airbooks.data.model.remote.ApiUtils

import android.content.ContentValues.TAG
import android.content.Context
import it.corelab.studios.airbooks.section.login.interfaces.signin.EditTextController
import it.corelab.studios.airbooks.section.login.interfaces.signin.ErrorDialogController
import it.corelab.studios.airbooks.section.login.interfaces.signin.SignInController
import it.corelab.studios.airbooks.section.login.pages.layout.SignInLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import java.util.*

class LoginFragment : Fragment(), SignInController, EditTextController, ErrorDialogController {


    //VIEW
    private lateinit var viewUI: View


    //FRAGMENT MANAGER
    private var fragmentManagerLogin: FragmentManager? = null


    //VIEW VARIABLES
    private lateinit var forgotPsw: Button
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var facebookButton: Button
    private lateinit var email: EditText
    private lateinit var password: EditText
    private val isCredentialValid: Boolean get() = isEmailValid(getString(email)) && !isEditTextEmpty(password)


    //API
    private var mAPIService: APIService? = null


    //UI INITIALIZATION
    private lateinit var mainUI: SignInLayout



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = SignInLayout()
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        mAPIService = ApiUtils.getAPIService()

        initViews()

        setListeners()

        return viewUI
    }



    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //SIGN IN CONTROLLER
    override fun initViews() {

        fragmentManagerLogin = activity!!.supportFragmentManager

        forgotPsw = mainUI.forgotButton
        loginButton = mainUI.loginButton
        signUpButton = mainUI.signUpButton
        facebookButton = mainUI.facebookButton
        email = mainUI.emailEditText
        password = mainUI.passwordEditText
    }

    override fun setListeners() {

        forgotPsw.setOnClickListener {

            fragmentManagerLogin!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, RecoverPasswordFragment(), "RecoverPasswordFragment")
                    .commit()

        }

        signUpButton.setOnClickListener{

            fragmentManagerLogin!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, SignUpFragment(), "SignUpFragment")
                    .commit()

        }

        loginButton.setOnClickListener {
            verifyCredentials()
            if (isCredentialValid) {
                val postSignIn = PostSignIn(email.text.toString(), password.text.toString())
                signInPost(postSignIn, "http://airbooks.altervista.org/API/v2/auth/", Locale.getDefault().language, "android")
            }
        }

    }

    override fun doIntentToHome() {
        val homeIntent = Intent(activity, MainActivity::class.java)
        homeIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(homeIntent)
        activity!!.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
    }

    override fun verifyCredentials() {

        if (!isEmailValid(getString(email))) {
            email.error = "you've to insert an email"
        }
        if (isEditTextEmpty(password)) {
            password.error = "Please insert a password"
        }
    }

    override fun signInPost(postSignIn: PostSignIn, url: String, lang: String, os: String) {

        val pDialog = SweetAlertDialog(activity!!, SweetAlertDialog.PROGRESS_TYPE)
        showProgressDialog(pDialog)

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


    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //EDIT TEXT CONTROLLER
    /**
     *
     * This function work to verify the reliability of the email inserted
     *
     * @author Roberto Vecchio
     * @param email email string text
     *
     * @version 1.0
     *
     * @return return a boolean value (is VALID or NOT)
     *
     */
    override fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    /**
     *
     * This function to get the string of a editText
     *
     * @author Roberto Vecchio
     * @param editText editText that you want to take the string
     *
     * @version 1.0
     *
     * @return the string of a editText
     *
     */
    override fun getString(editText: EditText): String {
        return editText.text.toString()
    }

    /**
     *
     * This function work to see if a editText is empty
     *
     * @author Roberto Vecchio
     * @param editText editText that you want to verify if is empty
     *
     * @version 1.0
     *
     * @return if is EMPTY or NOT
     *
     */
    override fun isEditTextEmpty(editText: EditText): Boolean {
        return editText.length() == 0
    }

    //==============================================================================================================================================================
    //==============================================================================================================================================================

    //ERROR DIALOG CONTROLLER
    override fun showErrorDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Credenziali sbagliate")
                .show()
    }

    override fun showProgressDialog(dialog: SweetAlertDialog) {

        dialog.progressHelper.barColor = Color.parseColor("#4990e2")
        dialog.titleText = "Loading"
        dialog.setCancelable(false)
        dialog.show()
    }

    //==============================================================================================================================================================
    //==============================================================================================================================================================
}
