package it.corelab.airbooks.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.corelab.airbooks.R;
import it.corelab.airbooks.activity.MainActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static it.corelab.airbooks.activity.Login.leftArrow;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    protected View view;
    private FragmentManager fragmentManager;
    private Button forgotPsw;
    private Button loginBtn;
    private Button signUp;
    protected TextInputEditText email;
    protected TextInputEditText password;
    private int errorString;
    private String urlAddress = "http://airbooks.altervista.org/API/v2/auth/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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
               verifyCredentials();
               if (isCredentialValid()){
                   try {
                       postRequest(urlAddress, createjson().toString());
                   }catch (IOException e){
                       e.printStackTrace();
                   }
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

    public void verifyCredentials(){

        if (!isEmailValid(getString(email))){
            email.setError("you've to insert an email");
        }
        if(isEditTextEmpty(password)){
            //passwordLayout.setPasswordVisibilityToggleEnabled(false);
            password.setError("Please insert a password");
        }
    }

    public boolean isCredentialValid(){
        return isEmailValid(getString(email)) && !isEditTextEmpty(password);
    }

    public void doIntentToHome(){
        Intent homeIntent = new Intent(getActivity(), MainActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(homeIntent);
        getActivity().overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }

    void postRequest(final String postUrl, String postBody)throws IOException {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(JSON,postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(requestBody)
                .header("Lang", Locale.getDefault().getLanguage())
                .header("Os", "android")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    String jsonData = response.body().string();
                    JSONObject object = new JSONObject(jsonData);


                    JSONObject userObj = object.getJSONObject("error");
                    errorString = userObj.getInt("code");




                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            verifyTypeAnimation();
                        }
                    });






                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void verifyTypeAnimation(){
        if (errorString == 701) {

            showErrorDialog();

        } else {

            doIntentToHome();
        }
    }

    private JSONObject createjson(){

        JSONObject object = new JSONObject();
        try {

            object.put("email", email.getText().toString());
            object.put("password", password.getText().toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return object;
    }

    public void showErrorDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Credenziali sbagliate")
                .show();
    }
}
