package com.amadeussoftua.chartlist.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem on 26.07.2014.
 */
public class Image {

    @SerializedName("#text")
    private String url;

    public String getUrl() {
        return url;
    }
}
