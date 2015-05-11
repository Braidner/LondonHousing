package org.braidner.londonhousing.api;

import org.braidner.londonhousing.model.StatisticsResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by smith / 03.05.2015.
 */
public interface StatisticsApi {

    String STATISTICS_URL = "http://london-housing.appspot.com/";

    @GET("/statistics")
    void loadStatistics(Callback<StatisticsResponse> callback);
}
