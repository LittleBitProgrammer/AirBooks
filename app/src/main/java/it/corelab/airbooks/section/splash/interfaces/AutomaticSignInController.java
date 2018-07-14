package it.corelab.airbooks.section.splash.interfaces;

public interface AutomaticSignInController {

    void automaticSignIn(String url,String lang,String os,String token);
    void doIntentToLogin();

}
