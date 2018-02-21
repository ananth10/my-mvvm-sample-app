package com.ananth.rxandroidwithretrofit.view.following;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.data.repository.FollowingRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowingViewModel extends ViewModel {

    private final LiveData<Resource<List<FollowingEntity>>> resourceLiveData;


    @Inject
    public FollowingViewModel(FollowingRepository followingRepository) {
        resourceLiveData = followingRepository.loadFollowing();
    }

    LiveData<Resource<List<FollowingEntity>>> getFollowing() {
        return resourceLiveData;
    }
}
