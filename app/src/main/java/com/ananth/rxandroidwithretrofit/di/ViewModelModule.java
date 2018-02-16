package com.ananth.rxandroidwithretrofit.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.ananth.rxandroidwithretrofit.view.profile.ProfileViewModel;
import com.ananth.rxandroidwithretrofit.viewmodel.GitViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Babu on 2/13/2018.
 */

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(GitViewModelFactory gitViewModelFactory);
}
