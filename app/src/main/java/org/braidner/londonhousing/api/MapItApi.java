package org.braidner.londonhousing.api;

import org.braidner.londonhousing.response.GeometryResponse;
import org.braidner.londonhousing.response.MapItResponse;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by KuznetsovNE on 06.05.2015.
 */
public interface MapItApi {

    String MAP_IT_URL = "http://mapit.mysociety.org/";

    @GET("/area/{locationCode}")
    void findLocationByCode(@Path("locationCode") String locationCode, Callback<MapItResponse> callback);

    @GET("/area/{locationCode}/children")
    void findBoroughChildes(@Path("locationCode") String locationCode, Callback<Response> callback);

    @GET("/area/{locationCode}/geometry")
    void loadGeometry(@Path("locationCode") String locationCode, Callback<GeometryResponse> callback);

}
