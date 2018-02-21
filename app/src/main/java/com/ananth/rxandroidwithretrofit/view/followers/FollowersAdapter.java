package com.ananth.rxandroidwithretrofit.view.followers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.databinding.FollowerItemsBinding;
import com.ananth.rxandroidwithretrofit.view.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowersAdapter extends BaseAdapter<FollowersAdapter.RecyclerViewHolder, FollowersEntity> {
    private final TypedValue mTypedValue = new TypedValue();
    private List<FollowersEntity> followersList;
    public Context mContext;
    private int mBackground;
    public FollowersAdapter(Context context) {
        mContext = context;
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        followersList = new ArrayList<>();
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void setData(List<FollowersEntity> followersEntities) {
        followersList = followersEntities;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FollowerItemsBinding followerItemsBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.follower_items, parent, false);
        return new RecyclerViewHolder(followerItemsBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        FollowersEntity followersEntity = followersList.get(position);
        holder.followerItemsBinding.setFollowers(followersEntity);
    }

    @Override
    public int getItemCount() {
        return followersList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        FollowerItemsBinding followerItemsBinding;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            followerItemsBinding = DataBindingUtil.bind(itemView);
        }
    }
}
