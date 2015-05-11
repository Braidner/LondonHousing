package org.braidner.londonhousing;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.braidner.londonhousing.api.MapItApi;
import org.braidner.londonhousing.utils.ApiUtils;

/**
 * Created by smith / 06.05.2015.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
    }
}
