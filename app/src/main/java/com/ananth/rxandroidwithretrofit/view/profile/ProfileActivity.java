package com.ananth.rxandroidwithretrofit.view.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;
import com.ananth.rxandroidwithretrofit.data.remote.response.ProfileResponse;
import com.ananth.rxandroidwithretrofit.databinding.ProfileBinding;
import com.ananth.rxandroidwithretrofit.view.login.LoginActivity;

import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileActivity extends AppCompatActivity implements ProfileClickHandler {

    private String mGitUserName = "";
    ProfileBinding profileBinding;

    @Inject
    ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.profile);
        profileBinding.setHandler(this);
        AndroidInjection.inject(this);
        setSupportActionBar(profileBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        if (getIntent() != null) {
            mGitUserName = getIntent().getStringExtra("username");
        }
//        getUserInfo();
//        getUserInfo1();
        System.out.println("mGitUserName:"+mGitUserName);
//        profileViewModel.setUserName(mGitUserName);
        profileViewModel
                .getProfileData()
                .observe(this, profileData -> profileBinding.setProfile(profileData.data));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        if (id == R.id.logout) {
            SharedPreferences preferences = getSharedPreferences("RxAndroid", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
            return true;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

//    private void getUserInfo() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://api.github.com/")
//                .build();
//
//        GithubService githubService = retrofit.create(GithubService.class);
//        Call<ProfileResponse> call = githubService.getGithubUserInfo("ananth10");
//        call.enqueue(new Callback<ProfileResponse>() {
//            @Override
//            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
//                System.out.println("JsonRes123 profile:"+response.body().getResults());
//
//            }
//
//            @Override
//            public void onFailure(Call<ProfileResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void getUserInfo1() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://api.github.com/")
//                .build();
//
//        GithubService githubService = retrofit.create(GithubService.class);
//        Observable<ProfileEntity> call = githubService.getGithubUserInfo2("ananth10");
//        call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ProfileEntity>() {
//
//            @Override
//            public void onNext(ProfileEntity github) {
//
//                System.out.println("JsonRes12 profile:"+github.getName());
//            }
//
//            @Override
//            public void onCompleted() {
//                // Nothing to do here
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                // cast to retrofit.HttpException to get the response code
//
//            }
//
//
//        });
//
//
//    }

//    private void bindData(final ProfileEntity profileEntity) {
//        String name = profileEntity.getLogin();
//        System.out.println("name :" + name);
//        if (profileEntity.getName() != null) {
//            mContentLay.setVisibility(View.VISIBLE);
//            mHappyCoding.setVisibility(View.VISIBLE);
//            mNoResultLay.setVisibility(View.GONE);
//            mProgressLay.setVisibility(View.GONE);
//            if(PrefUtils.mSave) {
//                PrefUtils.saveData("username", name, ProfileActivity.this);
//            }
//            mUserName.setText(profileEntity.getName());
//            if (!TextUtils.isEmpty(profileEntity.getCompany())) {
//                mCompany.setText(profileEntity.getCompany());
//            } else {
//                mCompany.setVisibility(View.GONE);
//            }
//            mLocation.setText(profileEntity.getLocation());
//            mReposCount.setText("" + profileEntity.getPublicRepos());
//            mGistsCount.setText("" + profileEntity.getPublicGists());
//            mFollowersCount.setText("" + profileEntity.getFollowers());
//            mFollowingsCount.setText("" + profileEntity.getFollowing());
//            Picasso.with(ProfileActivity.this)
//                    .load(profileEntity.getAvatarUrl()) // thumbnail url goes here
//                    .placeholder(R.mipmap.ic_launcher)
//                    .transform(new BlurTransformation(ProfileActivity.this, 40))
//                    .into(mBackGroundImage, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            Picasso.with(ProfileActivity.this)
//                                    .load(profileEntity.getAvatarUrl()) // image url goes here
//                                    .placeholder(mUserImage.getDrawable())
//                                    .into(mUserImage);
//                        }
//
//                        @Override
//                        public void onError() {
//                        }
//                    });
//        } else {
//            mContentLay.setVisibility(View.GONE);
//            mNoResultLay.setVisibility(View.VISIBLE);
//            mProgressLay.setVisibility(View.GONE);
//            mNoResult.setText("No User Found");
//        }
//    }

    @Override
    public void onRepoClick(String userName) {
//        startActivity(new Intent(ProfileActivity.this, RepositaryList.class).putExtra("username", userName));
    }

    @Override
    public void onGistClick(String userName) {
//        startActivity(new Intent(ProfileActivity.this, GistsList.class).putExtra("username", userName));
    }

    @Override
    public void onFollowerClick(String userName) {
//        startActivity(new Intent(ProfileActivity.this, FollowersList.class).putExtra("username", userName));
    }

    @Override
    public void onFollowingClick(String userName) {
//        startActivity(new Intent(ProfileActivity.this, FollowingsList.class).putExtra("username", userName));
    }

}
