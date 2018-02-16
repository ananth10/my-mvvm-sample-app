package com.ananth.rxandroidwithretrofit.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;

/**
 * Created by Babu on 2/13/2018.
 */

@Dao
public interface ProfileDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void saveProfile(ProfileEntity profileEntity);

    @Query("SELECT * from profile")
    LiveData<ProfileEntity> getProfile();
}
