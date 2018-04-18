package com.bj.basic.data.net;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory INSTANCE;
    private Retrofit retrofit;

    private RetrofitFactory() {
    }

    public static RetrofitFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    public <T> T create(Class<T> tClass) {
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://114.251.168.252:10690")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.client(initClient())
                    .build();
        }
        return retrofit.create(tClass);
    }

    private OkHttpClient initClient() {
        return new OkHttpClient.Builder()
                //.addInterceptor(initCommonInterceptor())
                //.addInterceptor(initLogInterceptor())
                .build();
    }


    private Interceptor initCommonInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request();
                        request.newBuilder()
                        .addHeader("Content_Type","application/json")
                        .addHeader("charset","UTF-8")
                        .build();
                return chain.proceed(request);
            }
        };
    }
}