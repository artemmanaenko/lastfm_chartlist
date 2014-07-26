package com.amadeussoftua.chartlist.network.actions;

import com.amadeussoftua.chartlist.network.responses.CountryChartResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Artem on 26.07.2014.
 */
public class CountryChartAction extends BaseServerAction {

    private String country;

    @Override
    public void fillRequestParams(RequestParams params) {
        super.fillRequestParams(params);
        params.put("country", country);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getAction() {
        return "?method=geo.gettopartists";
    }

    @Override
    public Class getResponseParseClass() {
        return CountryChartResponse.class;
    }
}
