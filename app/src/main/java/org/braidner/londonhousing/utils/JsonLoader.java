package org.braidner.londonhousing.utils;

import android.content.Context;
import com.google.gson.Gson;

import org.braidner.londonhousing.model.Location;
import org.braidner.londonhousing.model.WardsHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by smith / 01.05.2015.
 */
public class JsonLoader {

    private static Context ctx;

    public static Location loadLondonPolygon(Context context, int resource) throws IOException {
        ctx = context;
        String json = loadResource(resource);
        return new Gson().fromJson(json, Location.class);
    }

    public static WardsHolder loadWardsHolder(Context context, int resource) throws IOException {
        ctx = context;
        String json = loadResource(resource);
        return new Gson().fromJson(json, WardsHolder.class);
    }

    private static String loadResource(int resource) throws IOException {
        InputStream is = ctx.getResources().openRawResource(resource);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        return writer.toString();
    }
}
