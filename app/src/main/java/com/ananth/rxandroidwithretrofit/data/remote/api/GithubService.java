package com.ananth.rxandroidwithretrofit.data.remote.api;

import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.remote.response.ProfileResponse;

import org.json.JSONObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Babu on 9/29/2016.
 */
public interface GithubService {

    /*Get profile service*/
    @GET("users/{username}")
    Call<ProfileResponse> getGithubUserInfo(@Path("username") String username);


    /*Get profile service*/
    @GET("users/{username}")
    Call<ProfileEntity> getGithubUserInfo1(@Path("username") String username);


}
