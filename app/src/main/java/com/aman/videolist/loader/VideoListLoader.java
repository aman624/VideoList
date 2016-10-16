package com.aman.videolist.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.aman.videolist.model.Category;
import com.aman.videolist.model.CategoryList;
import com.aman.videolist.model.Video;
import com.aman.videolist.net.VideoListApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Custom Loader to fetch a list if Video items from the network
 * Loader fetches the data in background thread
 * Loader persists data across orientation change
 */
public class VideoListLoader extends AsyncTaskLoader<List<Video>> {
    private static final String TAG = VideoListLoader.class.getName();
    public static final String BASE_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/";

    private List<Video> mVideoList;

    /**
     * Constructor of the Loader
     * @param context Caller Context
     */
    public VideoListLoader(Context context) {
        super(context);
    }

    @Override
    public List<Video> loadInBackground() {
        // Load the data in background thread and populate the video list
        mVideoList = new ArrayList<>();
        try {
            Response<CategoryList> response = fetchVideoList();
            if (response.isSuccessful()) {
                List<Category> categories = response.body().getCategories();
                if (categories != null && categories.size() > 0) {
                    mVideoList.clear();
                    mVideoList.addAll(categories.get(0).getVideos());
                }
            }
        } catch(IOException e) {
            Log.e(TAG, e.toString());
        }

        return mVideoList;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(mVideoList != null && mVideoList.size() > 0) {
            // Send the result if list is already populated
            deliverResult(mVideoList);
        }

        if(takeContentChanged() || mVideoList == null || mVideoList.size() == 0) {
            // Fetch the data if list is empty
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        super.onReset();
        mVideoList.clear();
    }

    /**
     * Fetch a list of Video data from the provided URL
     * @return HTTP response
     * @throws IOException
     */
    private Response<CategoryList> fetchVideoList() throws IOException {
        // Using Retrofit third party library to load the data
        // Retrofit is a REST Client for Android and Java
        // It makes it easy to retrieve and upload JSON (or other formats) from the network
        // Retrofit can be configured with converter for data serialization (using Gson converter here)
        // Retrofit uses the OkHttp library for HTTP requests
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VideoListApi apiService = retrofit.create(VideoListApi.class);
        Call<CategoryList> call = apiService.loadCategoryList();
        return call.execute();
    }
}
