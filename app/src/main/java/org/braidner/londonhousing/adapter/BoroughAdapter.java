package org.braidner.londonhousing.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.entity.Statistic;

import java.util.List;

/**
 * Created by smith / 04.05.2015.
 */
public class BoroughAdapter extends RecyclerView.Adapter<BoroughAdapter.ViewHolder> {

    private List<Statistic> statistics;

    public BoroughAdapter(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.borough_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(statistics.get(position).getCrime());
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textStat);
        }
    }
}
