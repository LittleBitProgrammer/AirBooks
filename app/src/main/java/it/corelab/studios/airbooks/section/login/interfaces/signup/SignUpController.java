package it.corelab.studios.airbooks.section.login.interfaces.signup;

import android.widget.EditText;

import it.corelab.studios.airbooks.data.model.PostSignUp;

public interface SignUpController {

    void initViews();

    void doAnimationToLogin();

    String takeIsoNation(EditText editText);

    void signUpPost(PostSignUp postSignUp, String url,String lang, String os);

    void setListeners();

    void verifyCredential();
}
