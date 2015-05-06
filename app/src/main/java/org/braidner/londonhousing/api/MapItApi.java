package org.braidner.londonhousing.api;

import org.braidner.londonhousing.model.Location;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by KuznetsovNE on 06.05.2015.
 */
public interface MapItApi {

    String MAP_IT_URL = "http://mapit.mysociety.org/";

    @GET("/area/{locationCode}")
    void findLocationByCode(@Path("locationCode") String locationCode, Callback<Location> callback);

    @GET("/area/{locationCode}/children")
    void findBoroughChildrends(@Path("locationCode") String locationCode, Callback<Location> callback);

}
