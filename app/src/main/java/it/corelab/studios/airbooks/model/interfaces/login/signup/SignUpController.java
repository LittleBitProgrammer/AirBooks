package it.corelab.studios.airbooks.model.interfaces.login.signup;

import android.widget.EditText;

import it.corelab.studios.airbooks.model.data.LOGIN.SIGNUP.PostSignUp;

public interface SignUpController {

    void initViews();

    void doAnimationToLogin();

    String takeIsoNation(EditText editText);

    void signUpPost(PostSignUp postSignUp, String url,String lang, String os);

    void setListeners();

    void verifyCredential();
}
