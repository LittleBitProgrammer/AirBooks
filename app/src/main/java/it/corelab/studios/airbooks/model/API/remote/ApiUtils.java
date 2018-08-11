package it.corelab.studios.airbooks.model.API.remote;

public class ApiUtils {

    private ApiUtils(){}

    static final String BASE_URL = "http://airbooks.altervista.org/API/v2/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient().create(APIService.class);
    }
}
