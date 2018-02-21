package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ananth.rxandroidwithretrofit.data.NetworkBoundResource;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.data.remote.response.ProfileResponse;
import com.ananth.rxandroidwithretrofit.utils.Constants;

import java.util.List;

import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by Babu on 2/13/2018.
 */

public class GitRepository {

    private final GithubDao githubDao;
    private final GithubService githubService;


    @Inject
    public GitRepository(GithubDao githubDao, GithubService githubService) {
        this.githubDao = githubDao;
        this.githubService = githubService;
    }


    public LiveData<Resource<ProfileEntity>> loadProfileData() {
        return new NetworkBoundResource<ProfileEntity, ProfileEntity>() {

            @Override
            protected void saveCallResult(@NonNull ProfileEntity item) {
//                System.out.println("Profile response:" + item.getName());
                githubDao.saveProfile(item);

            }

            @NonNull
            @Override
            protected LiveData<ProfileEntity> loadFromDb() {
                return githubDao.getProfile();
            }
            @NonNull
            @Override
            protected Call<ProfileEntity> createCall() {
                return githubService.getGithubUserInfo(Constants.mUserName);
            }


        }.getAsLiveData();
    }
}
