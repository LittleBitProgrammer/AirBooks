package it.corelab.studios.airbooks.data.model.remote;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

   // private static int cacheSize = 10 * 1024 * 1024; // 10 MB
   // private static Cache cache = new Cache(getCacheDir(), cacheSize);

    /*private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cache(cache)
            .build();*/

    private static Retrofit retrofit = null;

    static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiUtils.BASE_URL)
                    //.client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
