package it.corelab.studios.airbooks.data.model.remote;

import io.reactivex.Observable;
import it.corelab.studios.airbooks.data.model.AutomaticSignInResponse;
import it.corelab.studios.airbooks.data.model.PostRecoverResponse;
import it.corelab.studios.airbooks.data.model.PostSignIn;
import it.corelab.studios.airbooks.data.model.PostSignInResponse;
import it.corelab.studios.airbooks.data.model.PostSignUp;
import it.corelab.studios.airbooks.data.model.PostSignUpResponse;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIService {


    @POST
    @FormUrlEncoded
    Observable<PostRecoverResponse> savePost(@Field("email") String email,
                                             @Url String url,
                                             @Header("Lang") String lang,
                                             @Header("Os") String os);
    @POST
    Observable<PostSignUpResponse> signUpPost(@Body PostSignUp postSignUp,
                                            @Url String url,
                                            @Header("Lang") String lang,
                                            @Header("Os") String os);

    @POST
    Observable<PostSignInResponse> signInPost(@Body PostSignIn postSignIn,
                                              @Url String url,
                                              @Header("Lang") String lang,
                                              @Header("Os") String os);
    @GET
    Observable<AutomaticSignInResponse> automaticSignin(@Url String url,
                                                        @Header("Lang") String lang,
                                                        @Header("Os") String os,
                                                        @Header("Token") String token);
}
