package com.ananth.rxandroidwithretrofit.data.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ananth.rxandroidwithretrofit.data.NetworkBoundResource;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Babu on 2/20/2018.
 */

public class RepoRepository {
    private final GithubDao githubDao;
    private final GithubService githubService;


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

            @NonNull
            @Override
            protected LiveData<List<RepositoryEntity>> loadFromDb() {
                return githubDao.getRepositories();
            }

            @NonNull
            @Override
            protected Call<List<RepositoryEntity>> createCall() {
                return githubService.getRepository("ananth10");
            }
        }.getAsLiveData();
    }
}
