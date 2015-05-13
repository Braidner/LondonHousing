package org.braidner.londonhousing.adapter;

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
import org.braidner.londonhousing.model.StatisticsWard;

import java.util.List;

/**
 * Created by smith / 04.05.2015.
 */
public class BoroughAdapter extends RecyclerView.Adapter<BoroughAdapter.ViewHolder> {

    private List<StatisticsWard> statistics;
    private static DisplayImageOptions displayImageOptions;

    public BoroughAdapter(List<StatisticsWard> statistics) {
        this.statistics = statistics;
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
        ImageLoader.getInstance().displayImage("http://www.ebminsurance.com.au/files/photos/Transport_Banner3.jpg", holder.imageView, displayImageOptions);

        StatisticsWard ward = statistics.get(position);

        holder.transportProperty.setText("Транспорт");
        holder.crimeProperty.setText("Преступность");
        holder.housePriceProperty.setText("Средняя цена");

        holder.transportRating.setRating(ward.getTransportRate());
        holder.crimeRating.setRating(ward.getCrimeRate());
        holder.housePriceRating.setRating(Float.parseFloat(ward.getHousePrice()));
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        private TextView transportProperty;
        private TextView crimeProperty;
        private TextView housePriceProperty;

        private RatingBar transportRating;
        private RatingBar crimeRating;
        private RatingBar housePriceRating;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageStat);

            transportProperty = (TextView) itemView.findViewById(R.id.transportProperty);
            crimeProperty = (TextView) itemView.findViewById(R.id.crimeProperty);
            housePriceProperty = (TextView) itemView.findViewById(R.id.housePriceProperty);

            transportRating = (RatingBar) itemView.findViewById(R.id.transportRating);
            crimeRating = (RatingBar) itemView.findViewById(R.id.crimeRating);
            housePriceRating = (RatingBar) itemView.findViewById(R.id.housePriceRating);
        }
    }
}
