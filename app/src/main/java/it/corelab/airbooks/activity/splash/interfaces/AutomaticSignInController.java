package it.corelab.airbooks.activity.splash.interfaces;

public interface AutomaticSignInController {

    void automaticSignIn(String url,String lang,String os,String token);
    void doIntentToLogin();

}
