package org.braidner.londonhousing.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.adapter.BoroughAdapter;
import org.braidner.londonhousing.api.StatisticsApi;
import org.braidner.londonhousing.entity.Borough;
import org.braidner.londonhousing.entity.Ward;
import org.braidner.londonhousing.model.StatisticsWard;
import org.braidner.londonhousing.response.StatisticsResponse;
import org.braidner.londonhousing.utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class BoroughActivity extends Activity {

    private BoroughAdapter adapter;
    private RecyclerView recyclerView;
    private List<StatisticsWard> statistics = new ArrayList<>();
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough);

        recyclerView = (RecyclerView) findViewById(R.id.card_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Intent intent = getIntent();
        final Borough borough = (Borough) intent.getSerializableExtra("borough");

        adapter = new BoroughAdapter(statistics, borough.getWards());
        recyclerView.setAdapter(adapter);

        List<String> codes = new ArrayList<>();

        for (Ward ward : borough.getWards()) {
            codes.add(ward.getCode());
        }

        code = TextUtils.join(",", codes);

        loadWardList();
    }

    private void loadWardList() {
        final StatisticsApi statisticsApi = ApiUtils.createStatisticsApi();

        statisticsApi.loadStatistics(code, new Callback<StatisticsResponse>() {
            @Override
            public void success(StatisticsResponse statisticsResponse, Response response) {
                statistics.addAll(statisticsResponse.getWards());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
