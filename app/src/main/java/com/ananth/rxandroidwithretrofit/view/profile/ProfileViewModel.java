package com.ananth.rxandroidwithretrofit.view.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.VisibleForTesting;
import android.arch.lifecycle.ViewModel;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.repository.GitRepository;
import com.ananth.rxandroidwithretrofit.data.repository.Resource;
import com.google.gson.JsonObject;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by Babu on 2/14/2018.
 */

public class ProfileViewModel extends ViewModel {

    private final LiveData<Resource<ProfileEntity>> profileData;

    String userName="";


    @Inject
    ProfileViewModel(GitRepository gitRepository) {
        profileData = gitRepository.loadProfileData("ananth10");
//        gitRepository.callApi();
    }

    LiveData<Resource<ProfileEntity>> getProfileData() {
        return profileData;
    }

    @VisibleForTesting
    public void setUserName(String userName) {
        this.userName=userName;
    }
}
