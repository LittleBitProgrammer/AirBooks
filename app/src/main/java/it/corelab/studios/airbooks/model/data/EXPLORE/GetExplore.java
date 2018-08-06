package it.corelab.studios.airbooks.model.data.EXPLORE;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetExplore {
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("os")
    @Expose
    private String os;
    @SerializedName("error")
    @Expose
    private Object error;
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

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
