package it.corelab.airbooks.section.login.interfaces.signin;

import it.corelab.airbooks.data.model.PostSignIn;

public interface SignInController {

    void initViews();

    void doIntentToHome();

    void setListeners();

    void verifyCredentials();

    void signInPost(PostSignIn postSignIn,String url,String lang,String os);
}
