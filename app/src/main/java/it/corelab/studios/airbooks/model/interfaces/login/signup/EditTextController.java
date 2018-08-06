package it.corelab.studios.airbooks.model.interfaces.login.signup;

import android.widget.EditText;

public interface EditTextController {

    String getString(EditText editText);

    boolean isEditTextEmpty(EditText editText);

    boolean isPswTooShort(EditText editText);

    boolean isEmailValid(CharSequence email);

    boolean isPasswordConfirmed(EditText editText, EditText secondEditText);
}
