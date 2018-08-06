package it.corelab.studios.airbooks.model.data.LOGIN.SIGNIN;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.corelab.studios.airbooks.model.data.Error;

public class AutomaticSignInResponse {

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
    private Object result;

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}