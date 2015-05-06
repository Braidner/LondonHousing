package org.braidner.londonhousing;

import android.app.Application;

import org.braidner.londonhousing.api.MapItApi;
import org.braidner.londonhousing.utils.ApiUtils;

/**
 * Created by smith / 06.05.2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final MapItApi mapItApi = ApiUtils.createMapItApi();
//        mapItApi.findLocationByCode();
    }
}
