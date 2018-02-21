package com.ananth.rxandroidwithretrofit.view.followers;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.databinding.FollowersListBinding;
import com.ananth.rxandroidwithretrofit.utils.Constants;
import com.ananth.rxandroidwithretrofit.view.DividerItemDecoration;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Followers extends AppCompatActivity {

    @Inject
    FollowersViewModel followersViewModel;

    FollowersListBinding followersListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        followersListBinding = DataBindingUtil.setContentView(this, R.layout.followers_list);
        setSupportActionBar(followersListBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        if (getIntent() != null) {
            Constants.mUserName = getIntent().getStringExtra("username");
        }
        setupRecyclerView();
        followersViewModel.getFollowers().observe(this, listResource -> followersListBinding.setResource(listResource));
    }

    private void setupRecyclerView() {
        followersListBinding.followersList.setLayoutManager(new LinearLayoutManager(followersListBinding.followersList.getContext()));
        followersListBinding.followersList.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        followersListBinding.followersList.addItemDecoration(itemDecoration);
        followersListBinding.followersList.setItemAnimator(new SlideInUpAnimator());

        followersListBinding.followersList.setAdapter(new FollowersAdapter(Followers.this));
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
