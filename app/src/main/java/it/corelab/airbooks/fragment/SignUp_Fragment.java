package it.corelab.airbooks.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import it.corelab.airbooks.CountryDialog;
import it.corelab.airbooks.R;

import static it.corelab.airbooks.activity.Login.leftArrow;


public class SignUp_Fragment extends Fragment implements View.OnClickListener{

    private View view;
    private FragmentManager fragmentManager;
    private TextInputEditText name;
    private TextInputEditText surname;
    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText confirmPsw;
    public static CountryDialog countryDialog;
    public static TextInputEditText nation;
    private Button signUp;

    public SignUp_Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signup,container,false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews(){

        fragmentManager = getActivity().getSupportFragmentManager();
        name = view.findViewById(R.id.password_edit_name);
        surname = view.findViewById(R.id.password_edit_surname);
        nation = view.findViewById(R.id.password_edit_nation);
        email = view.findViewById(R.id.password_edit_email);
        password = view.findViewById(R.id.editPasswordText);
        confirmPsw = view.findViewById(R.id.editPasswordTextConfirm);
        signUp = view.findViewById(R.id.button_signup);

    }

    private void setListeners(){

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                        .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                        .commit();
                setOffLeftArrow();
            }
        });

        nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryDialog = new CountryDialog();
                countryDialog.show(fragmentManager,"Choose your country");
            }
        });

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_signup:
                verifyCredential();
                if (isCredentialValid()){
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                            .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                            .commit();
                    setOffLeftArrow();
                }
                break;
        }
    }

    public void setOffLeftArrow(){
        leftArrow.setEnabled(false);
        leftArrow.setVisibility(View.INVISIBLE);
    }

    //utility

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getString(TextInputEditText textInputEditText){
        String stringa = textInputEditText.getText().toString();
        return stringa;
    }

    public boolean isEditTextEmpty(EditText editText){
        return  editText.length() == 0;
    }

    public boolean isPswTooShort(EditText editText){
        return editText.length() < 6;
    }

    public boolean isPasswordConfirmed(EditText editText, EditText secondEdit){
        return editText.getText().toString().equals(secondEdit.getText().toString());
    }

    public void verifyCredential(){
        if (isEditTextEmpty(name)){
            name.setError("Insert your name");
        }if (isEditTextEmpty(surname)){
            surname.setError("insert your surname");
        }if (!isEmailValid(getString(email))){
            email.setError("you've to insert an email");
        }if (isPswTooShort(password)){
            password.setError("you've to insert a minum 6 character password");
        }if (!isPasswordConfirmed(password,confirmPsw)){
            confirmPsw.setError("the password doesn't correspond");
        }
    }

    public boolean isCredentialValid(){
        return !isEditTextEmpty(name) && !isEditTextEmpty(surname) && isEmailValid(getString(email)) && !isPswTooShort(password) && isPasswordConfirmed(password,confirmPsw);
    }
}
