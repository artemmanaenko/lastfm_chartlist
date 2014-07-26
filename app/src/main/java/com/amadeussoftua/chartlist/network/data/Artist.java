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
    private ArtistAttribute rank;

    public List<Image> getImages() {
        return images;
    }

}