package it.corelab.airbooks.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.MainActivity;
import it.corelab.airbooks.data.model.PostRecoverResponse;
import it.corelab.airbooks.data.model.PostSignIn;
import it.corelab.airbooks.data.model.PostSignInResponse;
import it.corelab.airbooks.data.model.remote.APIService;
import it.corelab.airbooks.data.model.remote.ApiUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static it.corelab.airbooks.activity.Login.leftArrow;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    protected View view;
    private FragmentManager fragmentManager;
    private Button forgotPsw;
    private Button loginBtn;
    private Button signUp;
    protected TextInputEditText email;
    protected TextInputEditText password;
    private APIService mAPIService;

    public  Login_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signin, container, false);
        mAPIService = ApiUtils.getAPIService();
        initViews();
        setListeners();
        return view;
    }
    private void initViews(){

        fragmentManager = getActivity().getSupportFragmentManager();

        forgotPsw = view.findViewById(R.id.forgot_psw);
        loginBtn = view.findViewById(R.id.login_btn);
        signUp = view.findViewById(R.id.sign_up);
        email = view.findViewById(R.id.edit_text);
        password = view.findViewById(R.id.password_edit_password);
    }

    private void setListeners(){

        forgotPsw.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up:

                // Replace signup frgament with animation// Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter_animation,R.anim.left_exit_animation)
                        .replace(R.id.frameContainer,new SignUp_Fragment(), "SignUp_Fragment")
                        .commit();
                setOnLeftArrow();
                break;

            case R.id.login_btn:

                //login action
               verifyCredentials();
               if (isCredentialValid()){
                   PostSignIn postSignIn = new PostSignIn(email.getText().toString(), password.getText().toString());
                   signInPost(postSignIn,"http://airbooks.altervista.org/API/v2/auth/",Locale.getDefault().getLanguage(),"android");
               }

                break;

            case R.id.forgot_psw:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                        .replace(R.id.frameContainer, new RecoverPassword_Fragment(), "RecoverPassword_Fragment")
                        .commit();
                setOnLeftArrow();
                break;
        }
    }


    //utility

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getString(TextInputEditText textInputEditText){
        String stringa = textInputEditText.getText().toString();
        return stringa;
    }

    public boolean isEditTextEmpty(EditText editText){
        return  editText.length() == 0;
    }

    public void setOnLeftArrow(){
        leftArrow.setEnabled(true);
        leftArrow.setVisibility(View.VISIBLE);
    }

    public void verifyCredentials(){

        if (!isEmailValid(getString(email))){
            email.setError("you've to insert an email");
        }
        if(isEditTextEmpty(password)){
            //passwordLayout.setPasswordVisibilityToggleEnabled(false);
            password.setError("Please insert a password");
        }
    }

    public boolean isCredentialValid(){
        return isEmailValid(getString(email)) && !isEditTextEmpty(password);
    }

    public void doIntentToHome(){
        Intent homeIntent = new Intent(getActivity(), MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
        getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    public void signInPost(final PostSignIn postSignIn, String url, String lang, String os){

        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#4990e2"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        mAPIService.signInPost(postSignIn,url, lang, os).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PostSignInResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PostSignInResponse postSignInResponse) {
                pDialog.dismiss();
                if (postSignInResponse.getError() != null){
                    Log.i(TAG, "post submitted to API. " + postSignInResponse.getError().getCode().toString());
                    showErrorDialog();
                }else {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), getActivity().MODE_PRIVATE);
                    sharedPreferences.edit().putString("token",postSignInResponse.getResult().getValue()).apply();
                    doIntentToHome();
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
                .setContentText("Credenziali sbagliate")
                .show();
    }
}
