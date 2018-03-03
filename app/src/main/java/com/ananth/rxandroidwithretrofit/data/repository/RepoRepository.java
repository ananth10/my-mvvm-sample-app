package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ananth.rxandroidwithretrofit.data.NetworkBoundResource;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.utils.Constants;
import com.ananth.rxandroidwithretrofit.utils.RateLimiter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Babu on 2/20/2018.
 */

public class RepoRepository {
    private final GithubDao githubDao;
    private final GithubService githubService;

    private RateLimiter<String> repoListRateLimit = new RateLimiter<>(10, TimeUnit.MINUTES);
    @Inject
    public RepoRepository(GithubDao githubDao, GithubService githubService) {
        this.githubDao = githubDao;
        this.githubService = githubService;
    }

    public LiveData<Resource<List<RepositoryEntity>>> loadRepository() {
        return new NetworkBoundResource<List<RepositoryEntity>, List<RepositoryEntity>>() {

            @Override
            protected void saveCallResult(@NonNull List<RepositoryEntity> item) {
                githubDao.saveRepositories(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<RepositoryEntity> data) {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(Constants.mUserName);
            }

            @NonNull
            @Override
            protected LiveData<List<RepositoryEntity>> loadFromDb() {
                return githubDao.getRepositories();
            }

            @NonNull
            @Override
            protected Call<List<RepositoryEntity>> createCall() {
                return githubService.getRepository(Constants.mUserName);
            }
        }.getAsLiveData();
    }

}
