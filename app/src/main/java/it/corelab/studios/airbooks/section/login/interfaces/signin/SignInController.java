package it.corelab.studios.airbooks.section.login.interfaces.signin;

import it.corelab.studios.airbooks.data.model.PostSignIn;

public interface SignInController {

    void initViews();

    void doIntentToHome();

    void setListeners();

    void verifyCredentials();

    void signInPost(PostSignIn postSignIn,String url,String lang,String os);
}
