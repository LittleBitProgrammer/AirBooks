package it.corelab.studios.airbooks.model.interfaces.login.signin;

import it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN.PostSignIn;

public interface SignInController {

    void initViews();

    void doIntentToHome();

    void setListeners();

    void verifyCredentials();

    void signInPost(PostSignIn postSignIn,String url,String lang,String os);
}
