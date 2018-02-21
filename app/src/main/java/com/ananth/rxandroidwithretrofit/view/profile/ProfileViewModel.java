package com.ananth.rxandroidwithretrofit.view.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.repository.GitRepository;
import com.ananth.rxandroidwithretrofit.data.Resource;

import javax.inject.Inject;

/**
 * Created by Babu on 2/14/2018.
 */

public class ProfileViewModel extends ViewModel {

    private final LiveData<Resource<ProfileEntity>> profileData;
//    private final LiveData<Resource<List<FollowersEntity>>> follResourceLiveData;

    @Inject
    ProfileViewModel(GitRepository gitRepository) {
        profileData = gitRepository.loadProfileData();
//        follResourceLiveData = gitRepository.loadFollowers();
    }

    LiveData<Resource<ProfileEntity>> getProfileData() {
        return profileData;
    }

//    LiveData<Resource<List<FollowersEntity>>> getFollResourceLiveData() {
//        return follResourceLiveData;
//    }
}
