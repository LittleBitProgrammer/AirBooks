package it.corelab.studios.airbooks.view.fragment.login

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import java.util.Locale

import cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.corelab.studios.airbooks.R
import it.corelab.studios.airbooks.model.data.LOGIN.RECOVERPASSWORD.PostRecoverResponse
import it.corelab.studios.airbooks.model.API.remote.APIService
import it.corelab.studios.airbooks.model.API.remote.ApiUtils
import android.content.ContentValues.TAG
import android.widget.EditText
import it.corelab.studios.airbooks.model.interfaces.login.recover.RecoverPasswordController
import it.corelab.studios.airbooks.model.interfaces.login.recover.TransactionController
import it.corelab.studios.airbooks.model.interfaces.login.signin.ErrorDialogController
import it.corelab.studios.airbooks.view.anko.layout.login.pages.RecoverPasswordUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx


class RecoverPasswordFragment : Fragment(), RecoverPasswordController, ErrorDialogController, TransactionController {


    //VIEW
    private lateinit var viewUI: View


    //FRAGMENT MANAGER
    private var fragmentManagerRecover: FragmentManager? = null


    //VIEW VARIABLES
    private lateinit var email: EditText
    private lateinit var recoverButton: Button
    private lateinit var backButton: Button
    private val isValidCredential: Boolean get() = isEmailValid(getString(email))


    //API
    private var mAPIService: APIService? = null


    //UI INITIALIZATION
    private lateinit var mainUI: RecoverPasswordUI

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainUI = RecoverPasswordUI()
        viewUI = mainUI.createView(AnkoContext.create(ctx, this))

        mAPIService = ApiUtils.getAPIService()

        initViews()

        setListeners()

        return viewUI
    }

    //==============================================================================================================================================================
    //==============================================================================================================================================================


    //RECOVER PASSWORD CONTROLLER
    override fun initViews() {

        fragmentManagerRecover = activity!!.supportFragmentManager

        email = mainUI.email
        recoverButton = mainUI.recoverButton
        backButton = mainUI.backButton
    }

    override fun setListeners() {

        backButton.setOnClickListener {
            fragmentManager!!
                    .beginTransaction()
                    .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                    .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, LoginFragment(), "Login_fragment")
                    .commit()
        }

        recoverButton.setOnClickListener{

            verifyCredential()

            if (isValidCredential) {

                Log.i("Credentials", "OK")
                sendPost(email.text.toString(), "http://airbooks.altervista.org/API/v2/recover.php?email=" + email.text.toString(), Locale.getDefault().language, "android")

            }

        }
    }

    override fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun getString(textInputEditText: EditText?): String {
        return textInputEditText!!.text.toString()
    }

    override fun verifyCredential() {
        if (!isEmailValid(getString(email))) {
            email.error = "you've to insert an email"
        }
    }

    override fun sendPost(emailText: String, url: String, lang: String, os: String) {


        val pDialog = SweetAlertDialog(activity!!, SweetAlertDialog.PROGRESS_TYPE)
        showProgressDialog(pDialog)

        mAPIService!!.savePost(emailText, url, lang, os).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<PostRecoverResponse> {

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(postRecoverResponse: PostRecoverResponse) {
                pDialog.dismiss()
                if (postRecoverResponse.error != null) {
                    Log.i(TAG, "post submitted to API. " + postRecoverResponse.error.code!!.toString())
                    showErrorDialog()
                } else {
                    createDialog()
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


    //ERROR DIALOG CONTROLLER
    override fun showErrorDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Mail non esistente")
                .show()
    }

    override fun showProgressDialog(dialog: SweetAlertDialog?) {
        dialog!!.progressHelper.barColor = Color.parseColor("#4990e2")
        dialog.titleText = "Loading"
        dialog.setCancelable(false)
        dialog.show()
    }

    //==============================================================================================================================================================
    //==============================================================================================================================================================



    //TRANSACTION CONTROLLER
    override fun createDialog() {
        SweetAlertDialog(activity!!, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Recupero password")
                .setContentText("È stata inviata una mail per il recupero password al tuo indirizzo di posta elettronica")
                .setConfirmText("Ho capito")
                .setConfirmClickListener { sweetAlertDialog ->
                    fragmentManager!!
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                            .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, LoginFragment(), "Login_fragment")
                            .commit()

                    sweetAlertDialog.dismiss()
                }
                .show()
    }

    //==============================================================================================================================================================
    //==============================================================================================================================================================


}
