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

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.model.Geometry;
import org.braidner.londonhousing.model.Location;
import org.braidner.londonhousing.model.WardsHolder;
import org.braidner.londonhousing.utils.JsonLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LondonActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private final LatLng london = new LatLng(51.5085300, -0.1257400);
    private List<Location> locations = new ArrayList<>();
    private WardsHolder wardsHolder;
    private Map<Float, Polygon> polygons = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_london);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        try {
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.barking_and_dagenham));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.barnet));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.bexley));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.brent));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.bromley));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.camden));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.city_of_london));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.city_of_westminster));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.croydon));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.ealing));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.enfield));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.greenwich));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hackney));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hammersmith_and_fulham));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.haringey));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.harrow));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.havering));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hillingdon));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.hounslow));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.islington));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.kensington_and_chelsea));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.kingston_upon_thames));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.lambeth));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.lewisham));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.merton));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.newham));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.redbridge));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.richmond_upon_thames));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.southwark));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.sutton));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.tower_hamlets));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.waltham_forest));
//            locations.add(JsonLoader.loadLondonPolygon(this, R.raw.wandsworth));

            wardsHolder = JsonLoader.loadWardsHolder(this, R.raw.wards);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean isPointInPolygon(LatLng p, List<LatLng> points) {
        double minX = points.get(0).latitude;
        double maxX = points.get(0).latitude;
        double minY = points.get(0).longitude;
        double maxY = points.get(0).longitude;

        for (LatLng point : points) {
            minX = Math.min(point.latitude, minX);
            maxX = Math.max(point.latitude, maxX);
            minY = Math.min(point.longitude, minY);
            maxY = Math.max(point.longitude, maxY);
        }

        if (p.latitude < minX || p.latitude > maxX || p.longitude < minY || p.longitude > maxY) {
            return false;
        }

        final LatLng[] polygon = points.toArray(new LatLng[points.size()]);

        boolean inside = false;
        for (int i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
            if ((polygon[i].longitude > p.longitude) != (polygon[j].longitude > p.longitude) &&
                    p.latitude < (polygon[j].latitude - polygon[i].latitude) * (p.longitude - polygon[i].longitude) / (polygon[j].longitude - polygon[i].longitude) + polygon[i].latitude) {
                inside = !inside;
            }
        }

        return inside;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        System.out.println(latLng);
        String boroughName = null;

        final Intent intent = new Intent(this, BoroughActivity.class);
        intent.putExtra("BoroughName", boroughName);
        startActivity(intent);

        for (Map.Entry<Float, Polygon> entry : polygons.entrySet()) {
            final Polygon polygon = entry.getValue();
            if (isPointInPolygon(latLng, polygon.getPoints())) {
                polygon.setVisible(true);
                boroughName = String.valueOf(entry.getKey());
                System.out.println("Polygon id " + entry.getKey());
                return;
            }
        }
    }
}
