package it.corelab.studios.airbooks.section.login.fragment

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

import com.google.gson.Gson

import java.util.HashMap
import java.util.Locale

import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.CountryDialog
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.data.model.LOGIN.SIGNUP.PostSignUp
import it.corelab.studios.airbooks.data.model.LOGIN.SIGNUP.PostSignUpResponse
import it.corelab.studios.airbooks.data.model.remote.APIService
import it.corelab.studios.airbooks.data.model.remote.ApiUtils

import android.content.ContentValues.TAG
import it.corelab.studios.airbooks.section.login.interfaces.signup.DialogController
import it.corelab.studios.airbooks.section.login.interfaces.signup.EditTextController
import it.corelab.studios.airbooks.section.login.interfaces.signup.SignUpController
import it.corelab.studios.airbooks.section.login.pages.layout.SignUpUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx


class SignUpFragment : Fragment(), SignUpController, EditTextController, DialogController {

    //VIEW
    private lateinit var viewUI: View


    //FRAGMENT MANAGER
    private var fragmentManagerSignUp: FragmentManager? = null


    //VIEW VARIABLES
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPsw: EditText
    private lateinit var signUp: Button
    private lateinit var backToLogin: Button
    private val isCredentialValid: Boolean get() = !isEditTextEmpty(firstName) && !isEditTextEmpty(lastName) && isEmailValid(getString(email)) && !isEditTextEmpty(nation) && !isPswTooShort(password) && isPasswordConfirmed(password, confirmPsw)


    //API
    private var mAPIService: APIService? = null

    //UI INITIALIZATION
    private lateinit var mainUI: SignUpUI


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = SignUpUI()
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        mAPIService = ApiUtils.getAPIService()

        initViews()
        setListeners()

        return viewUI
    }




    companion object{
        lateinit var countryDialog: CountryDialog
        lateinit var nation: EditText
    }


    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //SIGN UP CONTROLLER
    override fun initViews() {

        fragmentManagerSignUp = activity!!.supportFragmentManager

        firstName = mainUI.firstName
        lastName = mainUI.lastName
        nation = mainUI.nationality
        email = mainUI.email
        password = mainUI.password
        confirmPsw = mainUI.confirmPassword
        signUp = mainUI.signUpButton
        backToLogin = mainUI.backButton

    }

    override fun doAnimationToLogin() {
        fragmentManager!!
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, LoginFragment(), "Login_fragment")
                .commit()
    }

    override fun takeIsoNation(editText: EditText): String? {
        val countries = HashMap<String, String>()
        for (iso in Locale.getISOCountries()) {
            val l = Locale("", iso)
            countries[l.displayCountry] = iso
        }

        return countries[editText.text.toString()]
    }

    override fun signUpPost(postSignUp: PostSignUp, url: String, lang: String, os: String) {

        val pDialog = SweetAlertDialog(activity!!, SweetAlertDialog.PROGRESS_TYPE)
        pDialog.progressHelper.barColor = Color.parseColor("#4990e2")
        pDialog.titleText = "Loading"
        pDialog.setCancelable(false)
        pDialog.show()

        mAPIService!!.signUpPost(postSignUp, url, lang, os).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PostSignUpResponse> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(postSignUpResponse: PostSignUpResponse) {

                pDialog.dismiss()

                if (postSignUpResponse.error != null) {
                    Log.i(TAG, "post submitted to API. " + postSignUpResponse.error.code!!.toString())
                    Log.i(TAG, "post submitted to API. " + postSignUpResponse.error.description.toString())
                    Log.w("2.0 getFeed > retrofi", Gson().toJson(postSignUpResponse))//DONT WORK
                    showErrorDialog()
                } else {
                    showSuccessDialog()
                    doAnimationToLogin()
                }
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }

    override fun setListeners() {

        backToLogin.setOnClickListener {
            fragmentManager!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, LoginFragment(), "Login_fragment")
                    .commit()
        }

        nation.setOnClickListener {
            countryDialog = CountryDialog()
            countryDialog.show(fragmentManager!!, "Choose your country")
        }

        signUp.setOnClickListener{
            verifyCredential()

            //IF CREDENTIALS ARE OK
            //1. SEND INFORMATION TO SIGN UP
            //2. GO TO LOGIN

            if (isCredentialValid) {

                Log.i("FEED:", email.text.toString() + " " +
                        password.text.toString() + " " +
                        firstName.text.toString() + " " +
                        lastName.text.toString() + " " +
                        takeIsoNation(nation))

                val postSignUp = PostSignUp(email.text.toString(), password.text.toString(), firstName.text.toString(), lastName.text.toString(), takeIsoNation(nation))

                signUpPost(postSignUp,
                        "http://airbooks.altervista.org/API/v2/users/",
                        Locale.getDefault().language,
                        "android")
            }
        }
    }

    override fun verifyCredential() {
        if (isEditTextEmpty(firstName)) {
            firstName.error = "Insert your name"
        }
        if (isEditTextEmpty(lastName)) {
            lastName.error = "insert your surname"
        }
        if (!isEmailValid(getString(email))) {
            email.error = "you've to insert an email"
        }
        if (isPswTooShort(password)) {
            password.error = "you've to insert a minum 6 character password"
        }
        if (!isPasswordConfirmed(password, confirmPsw)) {
            confirmPsw.error = "the password doesn't correspond"
        }
    }


    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //EDIT TEXT CONTROLLER
    override fun getString(textInputEditText: EditText?): String {
        return textInputEditText!!.text.toString()
    }

    override fun isEditTextEmpty(editText: EditText?): Boolean {
        return editText!!.length() == 0
    }

    override fun isPswTooShort(editText: EditText?): Boolean {
        return editText!!.length() < 6
    }

    override fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun isPasswordConfirmed(editText: EditText?, secondEdit: EditText?): Boolean {
        return editText!!.text.toString() == secondEdit!!.text.toString()
    }


    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //DIALOG CONTROLLER
    override fun showSuccessDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Benvenuto in airbooks")
                .setContentText("Riceverai una mail di conferma al tuo indirizzo di posta")
                .show()
    }

    override fun showErrorDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Email esistente")
                .setContentText("Email già esistente")
                .show()
    }

}
