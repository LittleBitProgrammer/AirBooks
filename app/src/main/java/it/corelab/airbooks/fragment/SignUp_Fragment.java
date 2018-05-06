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
import android.widget.TextView;

import it.corelab.airbooks.CountryDialog;
import it.corelab.airbooks.R;

import static it.corelab.airbooks.activity.Login.leftArrow;


public class SignUp_Fragment extends Fragment implements View.OnClickListener{

    private View view;
    private FragmentManager fragmentManager;
    public static CountryDialog countryDialog;
    public static TextInputEditText nation;

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
        nation =view.findViewById(R.id.password_edit_nation);
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
    }

    @Override
    public void onClick(View v) {

    }

    public void setOffLeftArrow(){
        leftArrow.setEnabled(false);
        leftArrow.setVisibility(View.INVISIBLE);
    }
}
