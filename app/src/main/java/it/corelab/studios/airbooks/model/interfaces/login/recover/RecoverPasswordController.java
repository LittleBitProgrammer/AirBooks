package it.corelab.studios.airbooks.model.interfaces.login.recover;

import android.widget.EditText;

public interface RecoverPasswordController {

    void initViews();

    void setListeners();

    boolean isEmailValid(CharSequence email);

    String getString(EditText editText);

    void verifyCredential();

    void sendPost(String emailText,String url,String lang,String os);
}
