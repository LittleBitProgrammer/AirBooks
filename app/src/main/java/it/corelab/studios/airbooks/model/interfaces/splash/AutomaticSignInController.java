package it.corelab.studios.airbooks.model.interfaces.splash;

public interface AutomaticSignInController {

    void automaticSignIn(String url,String lang,String os,String token);
    void doIntentToLogin();

}
