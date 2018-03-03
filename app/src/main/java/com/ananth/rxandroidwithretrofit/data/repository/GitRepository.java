package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
                if (item != null)
                    githubDao.saveProfile(item);

            }

            @Override
            protected boolean shouldFetch(@Nullable ProfileEntity data) {
                return data == null;
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

    public void deleteAllTables() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                githubDao.deleteProfile();
                githubDao.deleteRepos();
                githubDao.deleteFollowers();
                githubDao.deleteFollowing();
                return null;
            }
        }.execute();


    }
}
