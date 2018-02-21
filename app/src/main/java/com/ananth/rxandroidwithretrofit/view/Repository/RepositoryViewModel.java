package com.ananth.rxandroidwithretrofit.view.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;
import com.ananth.rxandroidwithretrofit.data.repository.RepoRepository;
import com.ananth.rxandroidwithretrofit.data.Resource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Babu on 2/20/2018.
 */

public class RepositoryViewModel extends ViewModel {

    private final LiveData<Resource<List<RepositoryEntity>>> listLiveData;

    @Inject
    public RepositoryViewModel(RepoRepository repoRepository) {
        listLiveData = repoRepository.loadRepository();
    }

    LiveData<Resource<List<RepositoryEntity>>> getRepoList() {
        return listLiveData;
    }

}
