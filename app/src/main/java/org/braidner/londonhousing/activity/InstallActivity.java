package org.braidner.londonhousing.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.api.MapItApi;
import org.braidner.londonhousing.api.StatisticsApi;
import org.braidner.londonhousing.dao.BoroughDao;
import org.braidner.londonhousing.dao.CommonDao;
import org.braidner.londonhousing.entity.Borough;
import org.braidner.londonhousing.entity.Point;
import org.braidner.londonhousing.entity.Ward;
import org.braidner.londonhousing.model.Location;
import org.braidner.londonhousing.response.MapItResponse;
import org.braidner.londonhousing.response.StatisticsResponse;
import org.braidner.londonhousing.utils.ApiUtils;
import org.braidner.londonhousing.utils.JsonLoader;
import org.braidner.londonhousing.utils.OrmUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class InstallActivity extends Activity {

    private List<Location> locations = new ArrayList<>();
    private boolean isNeedUpdate = true;
    private MapItApi mapItApi;
    private StatisticsApi statisticsApi;
    private OrmUtils ormUtils;
    private List<Borough> boroughs = new ArrayList<>();

    private Callback<MapItResponse> locationCallback = new Callback<MapItResponse>() {
        @Override
        public void success(MapItResponse mapIt, Response response) {
            Borough borough = new Borough();
            borough.setMapItId(mapIt.getId());
            borough.setName(mapIt.getName());
            borough.setCode(mapIt.getCodes().getGss());
            borough.setUnitId(mapIt.getCodes().getUnitId());

            for (Location location : locations) {
                if (location.getProperties().getCode().equals(borough.getCode())) {
                    final List<List<List<Float>>> coordinates = location.getGeometry().getCoordinates();

                    for (List<List<Float>> coordinate : coordinates) {
                        for (int i = 0; i < coordinate.size(); i++) {
                            borough.getPolygon().add(new Point(coordinate.get(i).get(1), coordinate.get(i).get(0), i, borough));
                        }
                    }
                    break;
                }
            }
            try {
                saveBorough(borough);
                saveWard(borough);
                boroughs.add(borough);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    private Callback<StatisticsResponse> statisticCallback = new Callback<StatisticsResponse>() {
        @Override
        public void success(StatisticsResponse statisticsResponse, Response response) {

        }

        @Override
        public void failure(RetrofitError error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        long update = sharedPref.getInt("update", 1);
        isNeedUpdate = update != 0;

        if (isNeedUpdate) {

            mapItApi = ApiUtils.createMapItApi();
            statisticsApi = ApiUtils.createStatisticsApi();

            initDB();
            loadLocations();
            loadBoroughs();

            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("update", 0);
            editor.apply();
        }

        Intent intent = new Intent(this, LondonActivity.class);
        startActivity(intent);
    }


    private void saveWard(final Borough borough) throws SQLException {
        final BoroughDao boroughDao = (BoroughDao) ormUtils.getDaoByClass(Borough.class);
        final CommonDao wardDao = ormUtils.getDaoByClass(Ward.class);
        mapItApi.findBoroughChildes(String.valueOf(borough.getMapItId()), new Callback<Response>() {
            @Override
            public void success(Response response, Response response2) {
                String s = new String(((TypedByteArray) response.getBody()).getBytes());
                final Map<String, MapItResponse> resp = ApiUtils.GSON.fromJson(s, new TypeToken<Map<String, MapItResponse>>() {
                }.getType());
                for (Map.Entry<String, MapItResponse> entry : resp.entrySet()) {
                    final MapItResponse value = entry.getValue();

                    Ward ward = new Ward();
                    ward.setId(value.getId());
                    ward.setCode(value.getCodes().getGss());
                    ward.setBorough(borough);
                    ward.setName(value.getName());

                    boroughDao.saveWard(ward, wardDao);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }


    private void initDB() {
        ormUtils = OpenHelperManager.getHelper(this.getApplicationContext(), OrmUtils.class);
        ormUtils.clearDatabase();
    }

    private void loadBoroughs() {
        for (Location location : locations) {
            final String code = location.getProperties().getCode();
            mapItApi.findLocationByCode(code, locationCallback);
        }
    }

    public void saveBorough(Borough borough) throws SQLException {
        final BoroughDao boroughDao = (BoroughDao) ormUtils.getDaoByClass(Borough.class);
        final CommonDao pointDao = ormUtils.getDaoByClass(Point.class);
        final CommonDao wardDao = ormUtils.getDaoByClass(Ward.class);
        boroughDao.create(borough, wardDao, pointDao);
    }

    private void loadLocations() {
        try {
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.barking_and_dagenham));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.barnet));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.bexley));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.brent));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.bromley));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.camden));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.city_of_london));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.city_of_westminster));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.croydon));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.ealing));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.enfield));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.greenwich));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hackney));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hammersmith_and_fulham));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.haringey));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.harrow));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.havering));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hillingdon));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hounslow));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.islington));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.kensington_and_chelsea));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.kingston_upon_thames));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.lambeth));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.lewisham));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.merton));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.newham));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.redbridge));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.richmond_upon_thames));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.southwark));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.sutton));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.tower_hamlets));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.waltham_forest));
            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.wandsworth));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
