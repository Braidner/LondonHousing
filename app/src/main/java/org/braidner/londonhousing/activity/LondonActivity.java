package org.braidner.londonhousing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.dao.BoroughDao;
import org.braidner.londonhousing.dao.CommonDao;
import org.braidner.londonhousing.entity.Borough;
import org.braidner.londonhousing.entity.Point;
import org.braidner.londonhousing.model.Geometry;
import org.braidner.londonhousing.model.Location;
import org.braidner.londonhousing.model.WardsHolder;
import org.braidner.londonhousing.utils.ApiUtils;
import org.braidner.londonhousing.utils.JsonLoader;
import org.braidner.londonhousing.utils.OrmUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LondonActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private final LatLng london = new LatLng(51.5085300, -0.1257400);
    private OrmUtils ormUtils;
    private List<Borough> boroughs;
    private List<Location> locations = new ArrayList<>();
    private Map<Float, Polygon> polygons = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_london);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initDB();

        try {
            final BoroughDao boroughDao = (BoroughDao) ormUtils.getDaoByClass(Borough.class);
            boroughs = boroughDao.searchAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initDB() {
        ormUtils = OpenHelperManager.getHelper(this.getApplicationContext(), OrmUtils.class);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(london, 9.5f));

        for (Location location : locations) {
            updatePolygons(map, location);
        }

        map.setOnMapClickListener(this);
    }

    public void updatePolygons(GoogleMap map, Location location) {
        List<PolygonOptions> lineOptions = new ArrayList<>();

        Geometry geometry = location.getGeometry();

        final List<List<List<Float>>> coordinates = geometry.getCoordinates();

        for (List<List<Float>> coordinate : coordinates) {
            final PolygonOptions polygon = new PolygonOptions().geodesic(true).visible(false);

            for (List<Float> floats : coordinate) {
                polygon.add(new LatLng(floats.get(1), floats.get(0)));
            }

            lineOptions.add(polygon);
        }
        for (PolygonOptions option : lineOptions) {
            final Polygon polygon = map.addPolygon(option);
            polygons.put(location.getProperties().getPolygonId(), polygon);
        }
    }

    public boolean isPointInPolygon(LatLng p, List<Point> points) {
        float minX = points.get(0).getLatitude();
        float maxX = points.get(0).getLatitude();
        float minY = points.get(0).getLongitude();
        float maxY = points.get(0).getLongitude();

        for (Point point : points) {
            minX = Math.min(point.getLatitude(), minX);
            maxX = Math.max(point.getLatitude(), maxX);
            minY = Math.min(point.getLongitude(), minY);
            maxY = Math.max(point.getLongitude(), maxY);
        }

        if (p.latitude < minX || p.latitude > maxX || p.longitude < minY || p.longitude > maxY) {
            return false;
        }

        final Point[] polygon = points.toArray(new Point[points.size()]);

        boolean inside = false;
        for (int i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            if ((polygon[i].getLongitude() > p.longitude) != (polygon[j].getLongitude() > p.longitude) && p.latitude <
                    (polygon[j].getLatitude() - polygon[i].getLatitude()) * (p.longitude - polygon[i].getLongitude()) / (polygon[j].getLongitude() - polygon[i].getLongitude()) + polygon[i].getLatitude()) {
                inside = !inside;
            }
        }

        return inside;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        for (Borough borough : boroughs) {
            if (isPointInPolygon(latLng, new ArrayList<>(borough.getPolygon()))) {
                final Intent intent = new Intent(this, BoroughActivity.class);
                intent.putExtra("BoroughCode", borough.getCode());
                startActivity(intent);
                break;
            }
        }
    }
}
