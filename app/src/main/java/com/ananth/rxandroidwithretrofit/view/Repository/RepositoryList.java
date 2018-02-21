package com.ananth.rxandroidwithretrofit.view.Repository;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.databinding.RepositoryListBinding;
import com.ananth.rxandroidwithretrofit.utils.Constants;


import javax.inject.Inject;

import dagger.android.AndroidInjection;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;


public class RepositoryList extends AppCompatActivity {

    RepositoryListBinding repositoryListBinding;

    @Inject
    RepositoryViewModel repositoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        repositoryListBinding= DataBindingUtil.setContentView(this,R.layout.repository_list);
        setSupportActionBar(repositoryListBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        if (getIntent() != null) {
            Constants.mUserName = getIntent().getStringExtra("username");
        }

        setupRecyclerView();
        repositoryViewModel.getRepoList().observe(this, listResource ->repositoryListBinding.setResource(listResource));
    }

    private void setupRecyclerView() {
        repositoryListBinding.repoList.setLayoutManager(new LinearLayoutManager(repositoryListBinding.repoList.getContext()));
        repositoryListBinding.repoList.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        repositoryListBinding.repoList.addItemDecoration(itemDecoration);
        repositoryListBinding.repoList.setItemAnimator(new SlideInUpAnimator());


        repositoryListBinding.repoList.setAdapter(new ReposAdaptor(RepositoryList.this));
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
