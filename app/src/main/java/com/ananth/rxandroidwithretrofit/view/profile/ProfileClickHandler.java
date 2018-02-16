package com.ananth.rxandroidwithretrofit.view.profile;

/**
 * Created by Babu on 2/14/2018.
 */

public interface ProfileClickHandler {

    void onRepoClick(String userName);

    void onGistClick(String userName);

    void onFollowerClick(String userName);

    void onFollowingClick(String userName);
}
