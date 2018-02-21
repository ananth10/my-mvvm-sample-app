package com.ananth.rxandroidwithretrofit.data.remote.api;

import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;
import com.ananth.rxandroidwithretrofit.data.remote.response.ProfileResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Babu on 9/29/2016.
 */
public interface GithubService {

    /*Get profile service*/
    @GET("users/{username}")
    Call<ProfileEntity> getGithubUserInfo(@Path("username") String username);



    @GET("users/{username}/followers")
    Call<List<FollowersEntity>> getGithubUserFollower(@Path("username") String username);


    @GET("users/{username}/repos")
    Call<List<RepositoryEntity>> getRepository(@Path("username") String username);


    @GET("users/{username}/following")
    Call<List<FollowingEntity>> getGithubUserFollowing(@Path("username") String username);
}
