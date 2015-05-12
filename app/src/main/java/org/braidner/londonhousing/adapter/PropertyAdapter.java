package org.braidner.londonhousing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.braidner.londonhousing.R;
import org.braidner.londonhousing.entity.WardProperty;


/**
 * Created by smith / 12.05.2015.
 */
public class PropertyAdapter extends ArrayAdapter<WardProperty> {

    private LayoutInflater inflater;

    public PropertyAdapter(Context context) {
        super(context, 0);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.property_element, parent, false);
            holder = new ViewHolder();
            holder.propertyName = (TextView) convertView.findViewById(R.id.propertyName);
            holder.propertyRating = (RatingBar) convertView.findViewById(R.id.propertyRating);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WardProperty property = getItem(position);
        holder.populateItem(property);

        return convertView;
    }

    static class ViewHolder {
        TextView propertyName;
        RatingBar propertyRating;

        void populateItem(WardProperty property) {
            propertyName.setText(property.getPropertyName());
            propertyRating.setRating(property.getPropertyRating());
        }

    }
}
