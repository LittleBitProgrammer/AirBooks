package it.corelab.airbooks.fragment;

import android.app.Notification;
import android.app.NotificationManager;
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
import android.widget.TextView;

import it.corelab.airbooks.R;

import static it.corelab.airbooks.activity.Login.leftArrow;


public class RecoverPassword_Fragment extends Fragment implements View.OnClickListener{

    protected View view;
    private FragmentManager fragmentManager;
    private TextInputEditText email;
    private Button recover;

    public RecoverPassword_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recover_password, container, false);
        initViews();
        setListeners();
        return view;
    }

    public void initViews(){
        fragmentManager = getActivity().getSupportFragmentManager();
        email = view.findViewById(R.id.edit_text);
        recover = view.findViewById(R.id.recover_btn);
    }

    public void setListeners(){
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                        .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                        .commit();
                setOffLeftArrow();
            }
        });
        recover.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recover_btn:
                verifyCredential();
                if (isValidCredential()){
                    //TODO://notification
                }
        }
    }

    public void setOffLeftArrow(){
        leftArrow.setEnabled(false);
        leftArrow.setVisibility(View.INVISIBLE);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public String getString(TextInputEditText textInputEditText){
        String stringa = textInputEditText.getText().toString();
        return stringa;
    }

    public void verifyCredential(){
        if (!isEmailValid(getString(email))){
            email.setError("you've to insert an email");
        }
    }
    public boolean isValidCredential(){
        return isEmailValid(getString(email));
    }
}
