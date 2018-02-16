package com.ananth.rxandroidwithretrofit.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ananth.rxandroidwithretrofit.data.local.dao.ProfileDao;
import com.ananth.rxandroidwithretrofit.data.local.entity.ProfileEntity;

/**
 * Created by Babu on 2/13/2018.
 */

@Database(entities = {ProfileEntity.class},version = 1,exportSchema = false)
public abstract class GitDataBase extends RoomDatabase {

    public abstract ProfileDao profileDao();
}
