package org.braidner.londonhousing.utils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.braidner.londonhousing.api.MapItApi;

import java.lang.reflect.Type;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by KuznetsovNE on 06.05.2015.
 */
public class ApiUtils {

    public static MapItApi createMapItApi() {
        GsonBuilder builder = new GsonBuilder();

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong() * 1000);
            }
        });

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(MapItApi.MAP_IT_URL)
                .setConverter(new GsonConverter(builder.create()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        return adapter.create(MapItApi.class);
    }
}
