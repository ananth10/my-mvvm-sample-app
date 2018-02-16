package com.ananth.rxandroidwithretrofit.di;

import android.app.Application;

import com.ananth.rxandroidwithretrofit.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Babu on 2/12/2018.
 */

@Singleton
@Component(modules = {AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})

//public interface AppComponent extends AndroidInjector<MyApplication> {
//    @Component.Builder
//    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
//    }
//}
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MyApplication aaApp);
}