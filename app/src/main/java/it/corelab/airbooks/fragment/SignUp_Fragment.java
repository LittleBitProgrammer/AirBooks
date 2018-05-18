package it.corelab.airbooks.fragment;

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

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import it.corelab.airbooks.CountryDialog;
import it.corelab.airbooks.R;
import it.corelab.airbooks.data.model.PostSignUp;
import it.corelab.airbooks.data.model.PostSignUpResponse;
import it.corelab.airbooks.data.model.remote.APIService;
import it.corelab.airbooks.data.model.remote.ApiUtils;

import static android.content.ContentValues.TAG;
import static it.corelab.airbooks.activity.Login.leftArrow;


public class SignUp_Fragment extends Fragment implements View.OnClickListener{

    private View view;
    private FragmentManager fragmentManager;
    private TextInputEditText name;
    private TextInputEditText surname;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText confirmPsw;
    public static CountryDialog countryDialog;
    public static TextInputEditText nation;
    private Button signUp;
    private APIService mAPIService;

    public SignUp_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup,container,false);
        mAPIService = ApiUtils.getAPIService();
        initViews();
        setListeners();
        return view;
    }

    private void initViews(){

        fragmentManager = getActivity().getSupportFragmentManager();
        name = view.findViewById(R.id.password_edit_name);
        surname = view.findViewById(R.id.password_edit_surname);
        nation = view.findViewById(R.id.password_edit_nation);
        email = view.findViewById(R.id.password_edit_email);
        password = view.findViewById(R.id.editPasswordText);
        confirmPsw = view.findViewById(R.id.editPasswordTextConfirm);
        signUp = view.findViewById(R.id.button_signup);

    }

    private void setListeners(){

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                        .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                        .commit();
                setOffLeftArrow();
            }
        });

        nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryDialog = new CountryDialog();
                countryDialog.show(fragmentManager,"Choose your country");
            }
        });

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:

                verifyCredential();

                //IF CREDENTIALS ARE OK
                //1. SEND INFORMATION TO SIGN UP
                //2. GO TO LOGIN

                if (isCredentialValid()){

                    Log.i("FEED:", email.getText().toString() + " " +
                            password.getText().toString() + " " +
                            name.getText().toString()+ " " +
                            surname.getText().toString()+ " " +
                            takeIsoNation(nation));

                    PostSignUp postSignUp = new PostSignUp(email.getText().toString(), password.getText().toString(), name.getText().toString(), surname.getText().toString(), takeIsoNation(nation));

                    signUpPost(postSignUp,
                            "http://airbooks.altervista.org/API/v2/users/",
                            Locale.getDefault().getLanguage(),
                            "android");
                }
                break;
        }
    }

    public void setOffLeftArrow(){
        leftArrow.setEnabled(false);
        leftArrow.setVisibility(View.INVISIBLE);
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

    public boolean isPswTooShort(EditText editText){
        return editText.length() < 6;
    }

    public boolean isPasswordConfirmed(EditText editText, EditText secondEdit){
        return editText.getText().toString().equals(secondEdit.getText().toString());
    }

    public void verifyCredential(){
        if (isEditTextEmpty(name)){
            name.setError("Insert your name");
        }if (isEditTextEmpty(surname)){
            surname.setError("insert your surname");
        }if (!isEmailValid(getString(email))){
            email.setError("you've to insert an email");
        }if (isPswTooShort(password)){
            password.setError("you've to insert a minum 6 character password");
        }if (!isPasswordConfirmed(password,confirmPsw)){
            confirmPsw.setError("the password doesn't correspond");
        }
    }

    public boolean isCredentialValid(){
        return !isEditTextEmpty(name) && !isEditTextEmpty(surname) && isEmailValid(getString(email)) && !isEditTextEmpty(nation) && !isPswTooShort(password) && isPasswordConfirmed(password,confirmPsw);
    }

    public void signUpPost(PostSignUp postSignUp, String url, String lang, String os){

        final SweetAlertDialog pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#4990e2"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();

        mAPIService.signUpPost( postSignUp, url, lang, os ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PostSignUpResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PostSignUpResponse postSignUpResponse) {

                pDialog.dismiss();

                if (postSignUpResponse.getError() != null){
                    Log.i(TAG, "post submitted to API. " + postSignUpResponse.getError().getCode().toString());
                    Log.i(TAG, "post submitted to API. " + postSignUpResponse.getError().getDescription().toString());
                    Log.w("2.0 getFeed > retrofi", new Gson().toJson(postSignUpResponse));//DONT WORK
                    showErrorDialog();
                }else {
                    showSuccessDialog();
                    doAnimationToLogin();
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

    private void doAnimationToLogin(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                .commit();
        setOffLeftArrow();
    }

    private String takeIsoNation(EditText editText){
        Map<String, String> countries = new HashMap<>();
        for (String iso : Locale.getISOCountries()) {
            Locale l = new Locale("", iso);
            countries.put(l.getDisplayCountry(), iso);
        }

        return (countries.get(editText.getText().toString()));
    }

    public void showSuccessDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Benvenuto in airbooks")
                .setContentText("Riceverai una mail di conferma al tuo indirizzo di posta")
                .show();
    }

    public void showErrorDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Email esistente")
                .setContentText("hai inserito una mail già esistente")
                .show();
    }
}
