package it.corelab.airbooks.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.internal.Utils;
import it.corelab.airbooks.R;

import static it.corelab.airbooks.activity.Login.leftArrow;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    protected View view;
    private FragmentManager fragmentManager;
    private Button forgotPsw;
    private Button loginBtn;
    private Button signUp;
    protected TextInputEditText email;
    protected TextInputEditText password;

    public  Login_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.signin, container, false);
        initViews();
        setListeners();
        return view;
    }
    private void initViews(){

        fragmentManager = getActivity().getSupportFragmentManager();

        forgotPsw = view.findViewById(R.id.forgot_psw);
        loginBtn = view.findViewById(R.id.login_btn);
        signUp = view.findViewById(R.id.sign_up);
        email = view.findViewById(R.id.edit_text);
        password = view.findViewById(R.id.password_edit_password);
    }

    private void setListeners(){

        forgotPsw.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_up:

                // Replace signup frgament with animation// Replace signup frgament with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter_animation,R.anim.left_exit_animation)
                        .replace(R.id.frameContainer,new SignUp_Fragment(), "SignUp_Fragment")
                        .commit();
                setOnLeftArrow();
                break;

            case R.id.login_btn:

                //login action
                if (!isEmailValid(getString(email))){
                    email.setError("you've to insert an email");
                }
                if(isEditTextEmpty(password)){
                    //passwordLayout.setPasswordVisibilityToggleEnabled(false);
                    password.setError("Please insert a password");
                }
                break;

            case R.id.forgot_psw:

                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                        .replace(R.id.frameContainer, new RecoverPassword_Fragment(), "RecoverPassword_Fragment")
                        .commit();
                setOnLeftArrow();
                break;
        }
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

    public void setOnLeftArrow(){
        leftArrow.setEnabled(true);
        leftArrow.setVisibility(View.VISIBLE);
    }
}
