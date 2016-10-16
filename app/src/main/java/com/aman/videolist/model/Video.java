package com.aman.videolist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Class representing a Video object
 * JSON video data is stored in objects of this class
 */
public class Video {
    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("sources")
    private List<String> sources;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("image-480x270")
    private String imageSmall;

    @SerializedName("image-780x1200")
    private String imageLarge;

    @SerializedName("title")
    private String title;

    @SerializedName("studio")
    private String studio;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public String getThumb() {
        return getFormattedUrl(thumb);
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImageSmall() {
        return getFormattedUrl(imageSmall);
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        return getFormattedUrl(imageLarge);
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * Utility method to format image URL
     * JSON returns only the suffix of the image URL
     * This should be appended to the base image URL
     * @param suffix Suffix of the image URL
     * @return Full URL of the image
     */
    private String getFormattedUrl(String suffix) {
        if (sources == null || sources.size() == 0) {
            return suffix;
        }
        String videoUrl = sources.get(0);
        return videoUrl.substring(0, videoUrl.lastIndexOf('/') + 1) + suffix;
    }
}
