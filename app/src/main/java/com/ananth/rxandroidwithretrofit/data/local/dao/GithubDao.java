package com.ananth.rxandroidwithretrofit.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ananth.rxandroidwithretrofit.data.local.entity.FollowersEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.FollowingEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;
import com.ananth.rxandroidwithretrofit.data.local.entity.RepositoryEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Babu on 2/13/2018.
 */

@Dao
public interface GithubDao {

    @Insert(onConflict =  REPLACE)
    void saveProfile(ProfileEntity profileEntity);

    @Query("SELECT * from profile")
    LiveData<ProfileEntity> getProfile();

    @Query("DELETE FROM profile")
    void deleteProfile();

    @Insert(onConflict =  REPLACE)
    void saveFollowers(List<FollowersEntity> followEntities);

    @Query("SELECT * from followers")
    LiveData<List<FollowersEntity>> getFollowers();

    @Query("DELETE FROM followers")
    void deleteFollowers();

    @Insert(onConflict = REPLACE)
    void saveFollowing(List<FollowingEntity> followingEntities);

    @Query("SELECT * from following")
    LiveData<List<FollowingEntity>> getFollowing();

    @Query("DELETE FROM following")
    void deleteFollowing();

    @Insert(onConflict = REPLACE)
    void saveRepositories(List<RepositoryEntity> repositoryEntities);

    @Query("SELECT * from repository")
    LiveData<List<RepositoryEntity>> getRepositories();

    @Query("DELETE FROM repository")
    void deleteRepos();


}
