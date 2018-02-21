package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ananth.rxandroidwithretrofit.data.NetworkBoundResource;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowersRepository {

    private final GithubDao githubDao;

    private final GithubService githubService;

    @Inject
    FollowersRepository(GithubDao githubDao,GithubService githubService)
    {
        this.githubDao=githubDao;
        this.githubService=githubService;
    }

    public LiveData<Resource<List<FollowersEntity>>> loadFollowers()
    {
        return new NetworkBoundResource<List<FollowersEntity>, List<FollowersEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<FollowersEntity> item) {
                githubDao.saveFollowers(item);
            }

            @NonNull
            @Override
            protected LiveData<List<FollowersEntity>> loadFromDb() {
                return githubDao.getFollowers();
            }

            @NonNull
            @Override
            protected Call<List<FollowersEntity>> createCall() {
                return githubService.getGithubUserFollower(Constants.mUserName);
            }
        }.getAsLiveData();
    }
}
