package it.corelab.studios.airbooks.view.fragment.profile.ACTIVE;

public interface ProfileController {
    void getName(String url,String lang,String os,String token);
    void getProfileImage();
    void getFollowers();
    void getNation();
    void getBooksWritten();
    void getReview();
    void getProfile(String url,String lang,String os,String token);
}
