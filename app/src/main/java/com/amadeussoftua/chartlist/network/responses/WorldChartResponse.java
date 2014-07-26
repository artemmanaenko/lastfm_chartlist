package com.amadeussoftua.chartlist.network.responses;

import com.amadeussoftua.chartlist.network.data.TopArtists;

/**
 * Created by Artem on 26.07.2014.
 */
public class WorldChartResponse extends BaseServerResponse {

    private TopArtists artists;

    public TopArtists getArtists() {
        return artists;
    }
}
