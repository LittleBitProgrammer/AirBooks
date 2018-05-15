package it.corelab.airbooks.data.model.remote;

import io.reactivex.Observable;
import it.corelab.airbooks.data.model.PostRecoverResponse;
import it.corelab.airbooks.data.model.PostSignInResponse;
import it.corelab.airbooks.data.model.PostSignUpResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @FormUrlEncoded
    Observable<PostSignUpResponse> signUpPost(@Field("email") String email,
                                            @Field("password") String password,
                                            @Field("first_name") String first_name,
                                            @Field("last_name") String last_name,
                                            @Field("nationality") String nationality,
                                            @Url String url,
                                            @Header("Lang") String lang,
                                            @Header("Os") String os);

    @POST
    @FormUrlEncoded
    Observable<PostSignInResponse> signInPost(@Field("email") String email,
                                              @Field("password") String password,
                                              @Url String url,
                                              @Header("Lang") String lang,
                                              @Header("Os") String os);
}
