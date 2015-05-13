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
import org.braidner.londonhousing.entity.Ward;
import org.braidner.londonhousing.model.StatisticsWard;

import java.util.Collection;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by smith / 04.05.2015.
 */
public class BoroughAdapter extends RecyclerView.Adapter<BoroughAdapter.ViewHolder> {

    private List<StatisticsWard> statistics;
    private Collection<Ward> wards;
    private static DisplayImageOptions displayImageOptions;
    private final String IMAGE_BASE = "http://maps.googleapis.com/maps/api/streetview?";

    public BoroughAdapter(List<StatisticsWard> statistics, Collection<Ward> wards) {
        this.statistics = statistics;
        this.wards = wards;
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
        StatisticsWard ward = statistics.get(position);
//        http://maps.googleapis.com/maps/api/streetview?size=400x400&location=40.720032,%20-73.988354&fov=90&heading=235&pitch=10&sensor=false

        Ward entityWard = new Ward();

        for (Ward tmp : wards) {
            if (tmp.getCode().equals(ward.getCode())) entityWard = tmp;
        }

        String url = IMAGE_BASE + "size=700x300" + "&location=" + entityWard.getLat() + "," + entityWard.getLon() + "&sensor=false&key=AIzaSyDNtZqaVZPJV5hPeoivr7IkZk_gXhbqow8";

        ImageLoader.getInstance().displayImage(url, holder.imageView, displayImageOptions);
        holder.wardName.setText(ward.getWardName());

        holder.transportProperty.setText("Транспорт");
        holder.crimeProperty.setText("Преступность");
        holder.housePriceProperty.setText("Цены на жилье");

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
        private TextView wardName;

        private TextView transportProperty;
        private TextView crimeProperty;
        private TextView housePriceProperty;

        private RatingBar transportRating;
        private RatingBar crimeRating;
        private RatingBar housePriceRating;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageStat);
            wardName = (TextView) itemView.findViewById(R.id.wardName);

            transportProperty = (TextView) itemView.findViewById(R.id.transportProperty);
            crimeProperty = (TextView) itemView.findViewById(R.id.crimeProperty);
            housePriceProperty = (TextView) itemView.findViewById(R.id.housePriceProperty);

            transportRating = (RatingBar) itemView.findViewById(R.id.transportRating);
            crimeRating = (RatingBar) itemView.findViewById(R.id.crimeRating);
            housePriceRating = (RatingBar) itemView.findViewById(R.id.housePriceRating);
        }
    }
}
