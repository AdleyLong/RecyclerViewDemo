package com.huaqin.recyclerviewdemo.retrofit;

import com.google.gson.JsonObject;
import com.huaqin.recyclerviewdemo.core.bean.NewsItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ubuntu on 17-4-13.
 */

public interface INewsApi {

    @GET("/nc/article/list/{explore_id}/{offset}-{limit}.html")
    Observable<Map<String,List<NewsItem>>> getNewsList(
            @Path("explore_id") String explore_id,
            @Path("offset") int page,
            @Path("limit") int limit

    );
}
