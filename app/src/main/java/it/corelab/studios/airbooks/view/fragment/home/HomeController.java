package it.corelab.studios.airbooks.view.fragment.home;

public interface HomeController {

    void getHomeFeed(String url, String lang, String os, String token);
    void getShowcase(String url, String lang, String os, String token);
    void getReadings();
    void getBestBook();
    void getCategories();

}
