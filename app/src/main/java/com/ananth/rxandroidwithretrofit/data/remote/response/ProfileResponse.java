package com.ananth.rxandroidwithretrofit.data.remote.response;

import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;

/**
 * Created by Babu on 2/13/2018.
 */

public class ProfileResponse {

    private ProfileEntity results;


    public ProfileEntity getResults()

    {

        return results;
    }

    public void setResults(ProfileEntity results) {
        this.results = results;
    }
}
