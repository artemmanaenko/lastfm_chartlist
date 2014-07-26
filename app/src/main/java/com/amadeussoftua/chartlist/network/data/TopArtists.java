package com.amadeussoftua.chartlist.network.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Artem on 26.07.2014.
 */
public class TopArtists {

    @Expose
    @SerializedName("artist")
    private List<Artist> artists;

    public List<Artist> getArtists() {
        return artists;
    }
}
