package it.corelab.airbooks.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import it.corelab.airbooks.CountryCodes;
import it.corelab.airbooks.CountryDialog;
import it.corelab.airbooks.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
    private String urlAddress = "http://airbooks.altervista.org/API/v2/users/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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

                //IF CREDENTIALS ARE OK
                //1. SEND INFORMATION TO SIGN UP
                //2. GO TO LOGIN

                if (!isEditTextEmpty(name) && !isEditTextEmpty(surname) && isEmailValid(getString(email)) && isEditTextEmpty(nation) && !isPswTooShort(password) && isPasswordConfirmed(password,confirmPsw)){
                    Toast.makeText(getActivity(),"Please insert also the nationality",Toast.LENGTH_LONG).show();
                }

                if (isCredentialValid()){
                    try {
                        postRequest(urlAddress,createjson().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    doAnimationToLogin();
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
        Log.i("NATION", nation.getText().toString());
    }

    public boolean isCredentialValid(){
        return !isEditTextEmpty(name) && !isEditTextEmpty(surname) && isEmailValid(getString(email)) && !isEditTextEmpty(nation) && !isPswTooShort(password) && isPasswordConfirmed(password,confirmPsw);
    }

    private JSONObject createjson(){

        CountryCodes countryCodes = new CountryCodes();

        JSONObject object = new JSONObject();
        try {

            object.put("email", email.getText().toString());
            object.put("password", password.getText().toString());
            object.put("first_name", name.getText().toString());
            object.put("last_name", surname.getText().toString());
            object.put("nationality", countryCodes.getCode(nation.getText().toString()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return object;
    }

    void postRequest(String postUrl, String postBody)throws IOException{

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON,postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(requestBody)
                .header("Lang", "it")
                .header("Os", "android")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("TAG",response.body().string());
            }
        });
    }

    private void doAnimationToLogin(){
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter_animation, R.anim.right_exit_animation)
                .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                .commit();
        setOffLeftArrow();
    }
}
