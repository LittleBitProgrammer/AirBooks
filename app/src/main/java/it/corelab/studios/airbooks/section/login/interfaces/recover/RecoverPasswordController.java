package it.corelab.studios.airbooks.section.login.interfaces.recover;

import android.widget.EditText;

public interface RecoverPasswordController {

    void initViews();

    void setListeners();

    boolean isEmailValid(CharSequence email);

    String getString(EditText editText);

    void verifyCredential();

    void sendPost(String emailText,String url,String lang,String os);
}
