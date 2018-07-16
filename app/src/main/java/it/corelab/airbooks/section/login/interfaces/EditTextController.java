package it.corelab.airbooks.section.login.interfaces;

import android.widget.EditText;

public interface EditTextController {

    boolean isEmailValid(CharSequence email);

    String getString(EditText editText);

    boolean isEditTextEmpty(EditText editText);

}
