package com.huaqin.recyclerviewdemo.retrofit;

import android.util.Log;

import com.huaqin.recyclerviewdemo.App;
import com.huaqin.recyclerviewdemo.util.FileUtil;
import com.huaqin.recyclerviewdemo.util.InternetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ubuntu on 17-4-13.
 */

public class RetrofitClient {
    private final String TAG = "RetrofitClient";
    private static RetrofitClient sNewInstance;
    private OkHttpClient okHttpClient;
    private int mTimeOut = 60;

    private RetrofitClient() {
        initClient(false);
    }

    public static RetrofitClient getInstance() {
        if (sNewInstance == null) {
            sNewInstance = new RetrofitClient();
        }
        return sNewInstance;
    }


    private void initClient(boolean addHead) {
        //缓存目录
        File httpCacheDirectory = new File(FileUtil.getAvailableCacheDir(), "responses");
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
//                if(message.contains("UnknownHostException")){
//                    LogCat.i("NO network");
//                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (addHead) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor(loggingInterceptor)
                    .cache(cache)
                    .connectTimeout(mTimeOut, TimeUnit.SECONDS)
                    .readTimeout(mTimeOut, TimeUnit.SECONDS)
                    .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                    .build();

        } else {
            okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(mTimeOut, TimeUnit.SECONDS)
                    .readTimeout(mTimeOut, TimeUnit.SECONDS)
                    .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                    .build();
        }
    }

    //设置网络请求缓存
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);

            if (InternetUtil.isNetworkConnected(App.singleton)) {//网络正常时
                int maxAge = 0 * 60; // 有网络时 设置缓存超时时间0个小时
                Log.i(TAG, "has network maxAge=" + maxAge);
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                Log.i(TAG, "no network");
                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
                Log.i(TAG, "has maxStale=" + maxStale);
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
                Log.i(TAG, "response build maxStale=" + maxStale);
            }
            return response;
        }
    };


    public <T> T create(String baseUrl, Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }

//    public <T> T createByXML(String baseUrl, Class<T> service){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .client(okHttpClient)
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//        return retrofit.create(service);
//    }
}
