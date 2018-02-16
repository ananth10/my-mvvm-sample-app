package com.ananth.rxandroidwithretrofit.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Babu on 2/13/2018.
 */

public class RequestIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest=chain.request();
        HttpUrl originalHttpUrl=originalRequest.url();
        HttpUrl url=originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key",ApiConstants.API_KEY)
                     .build();

        Request request=originalRequest.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
