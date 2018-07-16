package it.corelab.airbooks.section.login.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import it.corelab.airbooks.R;
import it.corelab.airbooks.data.model.PostRecoverResponse;
import it.corelab.airbooks.data.model.remote.APIService;
import it.corelab.airbooks.data.model.remote.ApiUtils;
import it.corelab.airbooks.section.login.activity.Login;
import it.corelab.airbooks.section.login.fragment.LoginFragment;

import static android.content.ContentValues.TAG;


public class RecoverPassword_Fragment extends Fragment implements View.OnClickListener {

    protected View view;
    private FragmentManager fragmentManager;
    private TextInputEditText email;
    private Button recover;
    public static String emailAddress;
    private APIService mAPIService;

    public RecoverPassword_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recover_password, container, false);
        mAPIService = ApiUtils.getAPIService();
        initViews();
        setListeners();
        emailAddress = email.getText().toString();
        return view;
    }

    public void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        email = view.findViewById(R.id.edit_text);
        recover = view.findViewById(R.id.recover_btn);
    }

    public void setListeners() {
        Login.Companion.getLeftArrow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                        .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, new LoginFragment(), "Login_fragment")
                        .commit();
                setOffLeftArrow();
            }
        });
        recover.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recover_btn:

                verifyCredential();

                if (isValidCredential()) {

                    Log.i("Credentials", "OK");
                    sendPost(email.getText().toString(), "http://airbooks.altervista.org/API/v2/recover.php?email=" + email.getText().toString(), Locale.getDefault().getLanguage(), "android");

                }
        }
    }

    public void setOffLeftArrow() {
        Login.Companion.getLeftArrow().setEnabled(false);
        Login.Companion.getLeftArrow().setVisibility(View.INVISIBLE);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getString(TextInputEditText textInputEditText) {
        String stringa = textInputEditText.getText().toString();
        return stringa;
    }

    public void verifyCredential() {
        if (!isEmailValid(getString(email))) {
            email.setError("you've to insert an email");
        }
    }

    public boolean isValidCredential() {
        return isEmailValid(getString(email));
    }

    public void createDialog() {
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Recupero password")
                .setContentText("È stata inviata una mail per il recupero password al tuo indirizzo di posta elettronica")
                .setConfirmText("Ho capito")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                                .replace(R.id.FRAME_CONTAINER_LOGIN_ACTIVITY, new LoginFragment(), "Login_fragment")
                                .commit();
                        setOffLeftArrow();

                        sweetAlertDialog.dismiss();
                    }
                })
                .show();
    }

    public void sendPost(String emailText, String url, String lang, String os) {


        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#4990e2"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        mAPIService.savePost(emailText,url, lang, os).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PostRecoverResponse>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PostRecoverResponse postRecoverResponse) {
                pDialog.dismiss();
                    if (postRecoverResponse.getError() != null){
                        Log.i(TAG, "post submitted to API. " + postRecoverResponse.getError().getCode().toString());
                        showErrorDialog();
                    }else {
                        createDialog();
                    }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void showErrorDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Mail non esistente")
                .show();
    }

}
