package it.corelab.airbooks.API;

import java.util.HashMap;

import it.corelab.airbooks.object.Entity;

public class Response<T extends Entity> {

    private APIError error;
    private T result;
    
    public Response(){
        //default constructor
    }

    @SuppressWarnings (value="unchecked")
    public Response(HashMap<String, Object> response){

        if ((this.error = (APIError) response.get("error")) != null){
            this.error = (APIError) response.get("error");
        }

        if ((this.result = (T) response.get("result")) != null){
            this.result = (T) response.get("result");
        }
    }

    public APIError getError() {
        return error;
    }

    public T getResult() {
        return result;
    }

    public void setError(APIError error) {
        this.error = error;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
