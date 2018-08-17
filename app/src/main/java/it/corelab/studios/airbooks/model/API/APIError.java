package it.corelab.studios.airbooks.model.API;

import android.util.Log;

import java.util.HashMap;

public class APIError {

    private int code;
    private String description;

    private HashMap<Integer, String> errors = new HashMap<>();
    {
        errors.put(800, "Unable to perform request");
        errors.put(801,"Unable to convert JSON data");
        errors.put(802,"Access token required to perform request");
        errors.put(803,"Unable to encode roundedImage data");
        errors.put(804,"Atomatic authentication failed");
    }

    private APIError(int code, String description) {
        this.code = code;
        this.description = description;

        this.print();
    }

    public APIError(int code){
        new APIError(code, errors.get(code));
    }

    public APIError(HashMap<String, Object> error) {
        int code = (int) error.get("code");
        String desc = error.get("description").toString();

        new APIError(code, desc);
    }

    private void print() {
        Log.e("API ERROR", "**** ERROR" + this.code + "-" + this.description + "****");
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<Integer, String> getErrors() {
        return errors;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setErrors(HashMap<Integer, String> errors) {
        this.errors = errors;
    }
}