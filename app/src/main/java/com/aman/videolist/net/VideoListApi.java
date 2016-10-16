package com.aman.videolist.net;

import com.aman.videolist.model.CategoryList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface representing HTTP API used by Retrofit library
 */
public interface VideoListApi {

    /**
     * Load CategoryList object from the provided URL
     * @return Retrofit Call object which can be run synchronously or asynchronously
     */
    @GET("videos-enhanced-c.json")
    Call<CategoryList> loadCategoryList();
}
