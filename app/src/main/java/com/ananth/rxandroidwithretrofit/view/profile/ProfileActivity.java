package com.ananth.rxandroidwithretrofit.view.profile;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.data.Resource;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.databinding.ProfileBinding;
import com.ananth.rxandroidwithretrofit.utils.Constants;
import com.ananth.rxandroidwithretrofit.view.Repository.RepositoryList;
import com.ananth.rxandroidwithretrofit.view.followers.Followers;
import com.ananth.rxandroidwithretrofit.view.following.Following;
import com.ananth.rxandroidwithretrofit.view.login.LoginActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


public class ProfileActivity extends AppCompatActivity implements ProfileClickHandler {

    ProfileBinding profileBinding;
    @Inject
    ProfileViewModel profileViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        profileBinding = DataBindingUtil.setContentView(this, R.layout.profile);
        profileBinding.setHandler(this);
        setSupportActionBar(profileBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        if (getIntent() != null) {
            Constants.mUserName = getIntent().getStringExtra("username");
        }

        profileViewModel
                .getProfileData()
                .observe(this, profileData ->
                        profileBinding.setProfile(profileData.data));
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
            profileViewModel.deleteAll();
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


    @Override
    public void onRepoClick(String userName) {
        startActivity(new Intent(ProfileActivity.this, RepositoryList.class).putExtra("username", Constants.mUserName));
    }

    @Override
    public void onGistClick(String userName) {
//        startActivity(new Intent(ProfileActivity.this, GistsList.class).putExtra("username", userName));
        Toast.makeText(this, "Coming soon", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFollowerClick(String userName) {
        startActivity(new Intent(ProfileActivity.this, Followers.class).putExtra("username", Constants.mUserName));
    }

    @Override
    public void onFollowingClick(String userName) {
        startActivity(new Intent(ProfileActivity.this, Following.class).putExtra("username", Constants.mUserName));
    }

}
