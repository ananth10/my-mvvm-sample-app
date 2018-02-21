package com.ananth.rxandroidwithretrofit.view.followers;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.repository.FollowersRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowersViewModel extends ViewModel {

    private final LiveData<Resource<List<FollowersEntity>>> resourceLiveData;

    @Inject
    public FollowersViewModel(FollowersRepository followersRepository) {
        resourceLiveData = followersRepository.loadFollowers();
    }

    LiveData<Resource<List<FollowersEntity>>> getFollowers() {
        return resourceLiveData;
    }
}
