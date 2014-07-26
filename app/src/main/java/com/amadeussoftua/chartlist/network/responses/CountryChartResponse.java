package com.amadeussoftua.chartlist.network.responses;

import com.amadeussoftua.chartlist.network.data.TopArtists;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem on 26.07.2014.
 */
public class CountryChartResponse extends BaseServerResponse{

    @SerializedName("topartists")
    private TopArtists topArtists;

    public TopArtists getArtists() {
        return topArtists;
    }
}
