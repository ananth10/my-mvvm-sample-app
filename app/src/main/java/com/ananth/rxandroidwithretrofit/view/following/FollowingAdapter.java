package com.ananth.rxandroidwithretrofit.view.following;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ananth.rxandroidwithretrofit.R;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.databinding.FollowingItemsBinding;
import com.ananth.rxandroidwithretrofit.view.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Babu on 2/21/2018.
 */

public class FollowingAdapter extends BaseAdapter<FollowingAdapter.RecyclerViewHolder,FollowingEntity> {

    private final TypedValue mTypedValue = new TypedValue();
    private List<FollowingEntity> followingList;
    public Context mContext;
    private int mBackground;


    public FollowingAdapter(Context context)
    {
        mContext = context;
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        followingList = new ArrayList<>();
        setHasStableIds(true);
    }

    @Override
    public void setData(List<FollowingEntity> followingEntities) {
        followingList=followingEntities;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FollowingItemsBinding followingItemsBinding= DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.following_items,parent,false);
        return new RecyclerViewHolder(followingItemsBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        FollowingEntity followingEntity=followingList.get(position);
        holder.followingItemsBinding.setFollowing(followingEntity);
    }

    @Override
    public int getItemCount() {
        return followingList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        FollowingItemsBinding followingItemsBinding;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            followingItemsBinding=DataBindingUtil.bind(itemView);
        }
    }
}
