package it.corelab.studios.airbooks.model.API.remote;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    //private static int cacheSize = 10 * 1024 * 1024; // 10 MB
    //private static Cache cache = new Cache(CacheControl.Builder(). , cacheSize);


    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(6000, TimeUnit.MILLISECONDS)
            .readTimeout(6000, TimeUnit.MILLISECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();

                    // try the request
                    Response response = chain.proceed(request);

                    int tryCount = 0;
                    while (!response.isSuccessful() && tryCount < 3) {

                        Log.d("intercept", "Request is not successful - " + tryCount);

                        tryCount++;

                        // retry the request
                        response = chain.proceed(request);
                    }

                    // otherwise just pass the original response on
                    return response;
                }
            })
            .build();

    private static Retrofit retrofit = null;

    static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
