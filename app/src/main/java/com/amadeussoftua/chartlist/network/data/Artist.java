package com.amadeussoftua.chartlist.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Artem on 26.07.2014.
 */
public class Artist {

    @SerializedName("image")
    private List<Image> images;
    private int listeners;
    private String url;
    private String name;
    @SerializedName("@attr")
    private ArtistAttribute rank;

    public List<Image> getImages() {
        return images;
    }

    public int getListeners() {
        return listeners;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public ArtistAttribute getRank() {
        return rank;
    }
}