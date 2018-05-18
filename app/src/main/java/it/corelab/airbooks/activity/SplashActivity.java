package it.corelab.airbooks.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import it.corelab.airbooks.IntroActivity;
import it.corelab.airbooks.R;
import it.corelab.airbooks.data.model.AutomaticSignInResponse;
import it.corelab.airbooks.data.model.PostSignIn;
import it.corelab.airbooks.data.model.PostSignInResponse;
import it.corelab.airbooks.data.model.remote.APIService;
import it.corelab.airbooks.data.model.remote.ApiUtils;

import static android.content.ContentValues.TAG;

public class SplashActivity extends AppCompatActivity {
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIService = ApiUtils.getAPIService();

        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

        Log.i("SPLASH AVVIATO", "TRUE");

        if (isFirstRun){
            //show start activity

            startActivity(new Intent(SplashActivity.this, IntroActivity.class));
            Log.i("PRIMA RUN", "TRUE");


        }else {

            SharedPreferences sharedPreferences = this.getSharedPreferences(this.getPackageName(), this.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", "");

            Log.i("PRIMA RUN", "FALSE");

            if (token.equalsIgnoreCase("")) {
                // Show a log in dialog and/or other stuff to let user log in
                Log.i("TOKEN VUOTO", "TRUE");
                doIntentToLogin();
            }else {
                Log.i("TOKEN VUOTO", "FALSE");
                automaticSignInPost("http://airbooks.altervista.org/API/v2/auth/", Locale.getDefault().getLanguage(),"android",token);
                Log.i("TOKEN" , token);
            }
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
    }


    public void doIntentToLogin(){
        Intent loginIntent = new Intent(SplashActivity.this, Login.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(loginIntent);
    }

    public void automaticSignInPost( String url, String lang, String os, String token){

        mAPIService.automaticSignin( url, lang, os, token).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<AutomaticSignInResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AutomaticSignInResponse automaticSignInResponse) {
                if (automaticSignInResponse.getError() != null) {
                    Log.i("ERRORE", "post submitted to API. " + automaticSignInResponse.getError().getCode().toString());
                    doIntentToLogin();
                }else {
                    Log.i("NESSUN ERROR START HOME", "TRUE");
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
