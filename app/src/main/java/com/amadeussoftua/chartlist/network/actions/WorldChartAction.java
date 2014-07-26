package com.amadeussoftua.chartlist.network.actions;

import com.amadeussoftua.chartlist.network.responses.WorldChartResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Artem on 26.07.2014.
 */
public class WorldChartAction extends BaseServerAction<WorldChartResponse> {

    @Override
    public void fillRequestParams(RequestParams params) {
        super.fillRequestParams(params);
    }

    @Override
    public String getAction() {
        return "?method=chart.gettopartists";
    }

    @Override
    public Class getResponseParseClass() {
        return WorldChartResponse.class;
    }
}
