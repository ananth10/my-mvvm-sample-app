package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ananth.rxandroidwithretrofit.data.NetworkBoundResource;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowingRepository {

    private final GithubDao githubDao;

    private final GithubService githubService;


    @Inject
    public FollowingRepository(GithubDao githubDao,GithubService githubService)
    {
        this.githubDao=githubDao;
        this.githubService=githubService;
    }

   public LiveData<Resource<List<FollowingEntity>>> loadFollowing()
    {
        return new NetworkBoundResource<List<FollowingEntity>, List<FollowingEntity>>() {
            @Override
            protected void saveCallResult(@NonNull List<FollowingEntity> item) {
                githubDao.saveFollowing(item);
            }

            @NonNull
            @Override
            protected LiveData<List<FollowingEntity>> loadFromDb() {
                return githubDao.getFollowing();
            }

            @NonNull
            @Override
            protected Call<List<FollowingEntity>> createCall() {
                return githubService.getGithubUserFollowing(Constants.mUserName);
            }
        }.getAsLiveData();
    }
}
