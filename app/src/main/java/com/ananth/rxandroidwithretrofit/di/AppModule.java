package com.ananth.rxandroidwithretrofit.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ananth.rxandroidwithretrofit.data.local.GitDataBase;
import com.ananth.rxandroidwithretrofit.data.local.dao.GithubDao;
import com.ananth.rxandroidwithretrofit.data.remote.ApiConstants;
import com.ananth.rxandroidwithretrofit.data.remote.api.GithubService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Babu on 2/12/2018.
 */

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.readTimeout(ApiConstants.TIMEOUT_IN_SEC, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(logging);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    GithubService hitRemoteServer(OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    GitDataBase provideGitDatabase(Application application) {
        return Room.databaseBuilder(application, GitDataBase.class, "git.db").build();
    }

    @Provides
    @Singleton
    GithubDao provideProfileDao(GitDataBase gitDataBase) {
        return gitDataBase.profileDao();
    }

}
