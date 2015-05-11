package org.braidner.londonhousing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.entity.Statistic;
import org.braidner.londonhousing.model.StatisticsWard;

import java.util.List;

/**
 * Created by smith / 04.05.2015.
 */
public class BoroughAdapter extends RecyclerView.Adapter<BoroughAdapter.ViewHolder> {

    private List<StatisticsWard> statistics;
    private static DisplayImageOptions displayImageOptions;
    private Context context;

    public BoroughAdapter(List<StatisticsWard> statistics, Context context) {
        this.statistics = statistics;
        this.context = context;
        displayImageOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .build();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.borough_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(statistics.get(position).getWardName());
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setRating(4f);
        ImageLoader.getInstance().displayImage("http://www.ebminsurance.com.au/files/photos/Transport_Banner3.jpg", holder.imageView, displayImageOptions);
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private RatingBar ratingBar;
        private TextView population;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageStat);
            textView = (TextView) itemView.findViewById(R.id.statType);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}
