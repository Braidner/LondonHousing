package org.braidner.londonhousing;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.braidner.londonhousing.adapter.BoroughAdapter;
import org.braidner.londonhousing.entity.Statistic;

import java.util.ArrayList;
import java.util.List;


public class BoroughActivity extends Activity {

    private BoroughAdapter adapter;
    private RecyclerView view;
    private List<Statistic> statistics = new ArrayList<>();
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borough);

        Statistic statistic = new Statistic();
        statistic.setCrime("Crime");
        statistic.setHousing("Housing");
        statistic.setTransport("Transport");
        statistics.add(statistic);

        Statistic statistic2 = new Statistic();
        statistic2.setCrime("Transport");
        statistic2.setHousing("Housing");
        statistic2.setTransport("Transport");
        statistics.add(statistic2);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new BoroughAdapter(statistics);

        view = (RecyclerView) findViewById(R.id.card_view);
        view.setHasFixedSize(true);
        view.setAdapter(adapter);
        view.setLayoutManager(manager);
    }
}
