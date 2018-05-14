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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.corelab.airbooks.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static it.corelab.airbooks.activity.Login.leftArrow;


public class RecoverPassword_Fragment extends Fragment implements View.OnClickListener{

    protected View view;
    private FragmentManager fragmentManager;
    private TextInputEditText email;
    private Button recover;
    private String urlString;
    private int errorString;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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

                    Log.i("Credentials", "OK");

                    try {
                        postRequest(getUrlAddress(email), createjson().toString());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
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

    public void createDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Recupero password")
                .setContentText("È stata inviata una mail per il recupero password al tuo indirizzo di posta elettronica")
                .setConfirmText("Ho capito")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {

                        fragmentManager
                                .beginTransaction()
                                .setCustomAnimations(R.anim.right_enter_animation, R.anim.left_exit_animation)
                                .replace(R.id.frameContainer, new Login_Fragment(),"Login_fragment")
                                .commit();
                        setOffLeftArrow();

                        sweetAlertDialog.dismiss();
                    }
                })
                .show();
    }

    void postRequest(final String postUrl, String postBody)throws IOException{

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
                Log.i("RESPONSE", response.code() + "");
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

    private JSONObject createjson(){

        JSONObject object = new JSONObject();

        try {

            object.put("email", email.getText().toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return object;
    }

    public String getUrlAddress(EditText editText){

        urlString = "http://airbooks.altervista.org/API/v2/recover.php?email=" + editText.getText().toString();

        return urlString;
    }

    public void showErrorDialog(){
        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Errore")
                .setContentText("Mail non esistente")
                .show();
    }

    private void verifyTypeAnimation(){
        if (errorString != 714) {

            createDialog();

        } else {
            showErrorDialog();
        }
    }

}
