package org.braidner.londonhousing.api;

import org.braidner.londonhousing.response.StatisticsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by smith / 03.05.2015.
 */
public interface StatisticsApi {

    String STATISTICS_URL = "http://london-housing.appspot.com/";

    @GET("/statistics")
    void loadStatistics(@Query("code") String code, Callback<StatisticsResponse> callback);
}
