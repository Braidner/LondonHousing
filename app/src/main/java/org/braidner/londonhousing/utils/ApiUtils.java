package org.braidner.londonhousing.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.braidner.londonhousing.api.MapItApi;
import org.braidner.londonhousing.api.StatisticsApi;
import org.braidner.londonhousing.response.MapItResponse;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by KuznetsovNE on 06.05.2015.
 */
public class ApiUtils {

    public final static Gson GSON = new Gson();

    public static MapItApi createMapItApi() {
        return createApi(MapItApi.class, MapItApi.MAP_IT_URL);
    }

    public static StatisticsApi createStatisticsApi() {
        return createApi(StatisticsApi.class, StatisticsApi.STATISTICS_URL);
    }

    private static <T> T createApi(Class<T> clazz, String url) {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
            }
        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .setConverter(new GsonConverter(builder.create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return adapter.create(clazz);
    }
}
