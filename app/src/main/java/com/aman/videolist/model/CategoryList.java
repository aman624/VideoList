package com.aman.videolist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CategoryList data
 * Contains a list of Category objects
 */
public class CategoryList {

    @SerializedName("categories")
    private List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
