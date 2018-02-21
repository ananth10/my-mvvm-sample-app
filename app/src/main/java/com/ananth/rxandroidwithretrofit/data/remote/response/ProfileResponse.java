package com.ananth.rxandroidwithretrofit.data.remote.response;

import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;

import java.util.List;

/**
 * Created by Babu on 2/13/2018.
 */

public class ProfileResponse<T> {

    private ProfileEntity results;

    private List<FollowersEntity> followEntities;

    public List<FollowersEntity> getFollowers() {

        return followEntities;
    }

    public void setFollowEntities(List<FollowersEntity> followEntities) {
        this.followEntities = followEntities;
    }

    public ProfileEntity getResults()

    {

        return results;
    }


    public void setResults(ProfileEntity results) {
        this.results = results;
    }
}
