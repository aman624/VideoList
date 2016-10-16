package com.aman.videolist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Category data
 * Contains name and a list of videos
 */
public class Category {

    @SerializedName("name")
    private String name;

    @SerializedName("videos")
    private List<Video> videos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
