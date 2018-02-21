package com.ananth.rxandroidwithretrofit.di;

import com.ananth.rxandroidwithretrofit.view.Repository.RepositoryList;
import com.ananth.rxandroidwithretrofit.view.followers.Followers;
import com.ananth.rxandroidwithretrofit.view.following.Following;
import com.ananth.rxandroidwithretrofit.view.profile.ProfileActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Babu on 2/13/2018.
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileActivity profileActivity();

    @ContributesAndroidInjector
    abstract RepositoryList repositoryList();

    @ContributesAndroidInjector
    abstract Followers followersActivity();

    @ContributesAndroidInjector
    abstract Following followingActivity();


}
