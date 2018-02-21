package com.ananth.rxandroidwithretrofit.view.Repository;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.data.local.entity.OwnerEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;
import com.ananth.rxandroidwithretrofit.databinding.ReposItemBinding;
import com.ananth.rxandroidwithretrofit.view.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu on 10/3/2016.
 */
public class ReposAdaptor extends BaseAdapter<ReposAdaptor.ViewHolder,RepositoryEntity>{

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<RepositoryEntity> mRepoList;
    public Context mContext;


    public ReposAdaptor(Context context) {
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mRepoList=new ArrayList<>();
        mContext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReposItemBinding reposItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.repos_item, parent, false);
        return new ViewHolder(reposItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        RepositoryEntity repositoryEntity = mRepoList.get(position);
        holder.reposItemBinding.setRepository(repositoryEntity);
//
//        OwnerEntity ownerEntity = repositoryEntity.getOwner();
//        holder.reposItemBinding.setOwner(ownerEntity);

    }

    @Override
    public void setData(List<RepositoryEntity> repositoryEntities) {
        mRepoList=repositoryEntities;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ReposItemBinding reposItemBinding;

        public ViewHolder(View view) {
            super(view);
            reposItemBinding = DataBindingUtil.bind(view);
        }

    }

    @Override
    public int getItemCount() {
        return mRepoList.size();
    }
}
