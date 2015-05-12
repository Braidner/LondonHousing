package org.braidner.londonhousing.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.adapter.BoroughAdapter;
import org.braidner.londonhousing.adapter.PropertyAdapter;
import org.braidner.londonhousing.api.StatisticsApi;
import org.braidner.londonhousing.entity.WardProperty;
import org.braidner.londonhousing.response.StatisticsResponse;
import org.braidner.londonhousing.model.StatisticsWard;
import org.braidner.londonhousing.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BoroughActivity extends Activity {

    private BoroughAdapter adapter;
    private RecyclerView recyclerView;
    private ListView propertyView;
    private PropertyAdapter propertyAdapter;
    private List<StatisticsWard> statistics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough);

        recyclerView = (RecyclerView) findViewById(R.id.card_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BoroughAdapter(statistics);
        recyclerView.setAdapter(adapter);

        propertyView = (ListView) findViewById(R.id.propertyList);
        propertyAdapter = new PropertyAdapter(this);

        test();
    }

    public void test() {
        final StatisticsApi statisticsApi = ApiUtils.createStatisticsApi();

        statisticsApi.loadStatistics("E05000606", new Callback<StatisticsResponse>() {
            @Override
            public void success(StatisticsResponse statisticsResponse, Response response) {
                statistics.addAll(statisticsResponse.getWards());
                for (StatisticsWard statistic : statistics) {
                    WardProperty property = new WardProperty();
//                    property.setPropertyName();
//                    property.setPropertyRating();
                }
                adapter.notifyDataSetChanged();
//                ttt();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
