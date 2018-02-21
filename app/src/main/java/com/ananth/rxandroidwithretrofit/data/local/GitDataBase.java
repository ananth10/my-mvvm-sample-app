package com.ananth.rxandroidwithretrofit.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;

/**
 * Created by Babu on 2/13/2018.
 */

@Database(entities = {ProfileEntity.class,FollowersEntity.class, RepositoryEntity.class, FollowingEntity.class},version = 1,exportSchema = false)
public abstract class GitDataBase extends RoomDatabase {

    public abstract GithubDao profileDao();

}
