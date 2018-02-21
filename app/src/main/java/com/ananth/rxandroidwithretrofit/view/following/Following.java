package com.ananth.rxandroidwithretrofit.view.following;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.databinding.FollowersListBinding;
import com.ananth.rxandroidwithretrofit.databinding.FollowingsListBinding;
import com.ananth.rxandroidwithretrofit.utils.Constants;
import com.ananth.rxandroidwithretrofit.view.DividerItemDecoration;
import com.ananth.rxandroidwithretrofit.view.followers.FollowersAdapter;
import com.ananth.rxandroidwithretrofit.view.followers.FollowersViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Following extends AppCompatActivity {

    @Inject
    FollowingViewModel followingViewModel;

    FollowingsListBinding followersListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        followersListBinding = DataBindingUtil.setContentView(this, R.layout.followings_list);
        setSupportActionBar(followersListBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        if (getIntent() != null) {
            Constants.mUserName = getIntent().getStringExtra("username");
        }
        setupRecyclerView();
        followingViewModel.getFollowing().observe(this, listResource -> followersListBinding.setResource(listResource));
    }

    private void setupRecyclerView() {
        followersListBinding.followingList.setLayoutManager(new LinearLayoutManager(followersListBinding.followingList.getContext()));
        followersListBinding.followingList.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        followersListBinding.followingList.addItemDecoration(itemDecoration);
        followersListBinding.followingList.setItemAnimator(new SlideInUpAnimator());

        followersListBinding.followingList.setAdapter(new FollowingAdapter(Following.this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

}
