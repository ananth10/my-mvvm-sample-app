package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ananth.rxandroidwithretrofit.data.local.dao.ProfileDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.data.remote.response.ProfileResponse;
import javax.inject.Inject;
import retrofit2.Call;

/**
 * Created by Babu on 2/13/2018.
 */

public class GitRepository {

    private final ProfileDao profileDao;
    private final GithubService githubService;


    @Inject
    public GitRepository(ProfileDao profileDao, GithubService githubService) {
        this.profileDao = profileDao;
        this.githubService = githubService;
    }


    public LiveData<Resource<ProfileEntity>> loadProfileData(String userName) {
        return new NetworkBoundResource<ProfileEntity, ProfileResponse>() {

            @Override
            protected void saveCallResult(@NonNull ProfileResponse item) {
                System.out.println("Profile response:" + item.getResults());
//                profileDao.saveProfile(item.getResults());

            }


            @NonNull
            @Override
            protected LiveData<ProfileEntity> loadFromDb() {
                return profileDao.getProfile();
            }
            @NonNull
            @Override
            protected Call<ProfileResponse> createCall() {
                System.out.println("test12:" + "create call");
                return githubService.getGithubUserInfo(userName);
            }


        }.getAsLiveData();
    }

}
