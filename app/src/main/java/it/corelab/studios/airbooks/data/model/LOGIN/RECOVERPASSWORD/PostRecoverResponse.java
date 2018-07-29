package it.corelab.studios.airbooks.data.model.LOGIN.RECOVERPASSWORD;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.corelab.studios.airbooks.data.model.Error;
import it.corelab.studios.airbooks.data.model.LOGIN.Result;

public class PostRecoverResponse {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("result")
    @Expose
    private Result result;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}