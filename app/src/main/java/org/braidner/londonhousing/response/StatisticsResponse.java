package org.braidner.londonhousing.response;

import org.braidner.londonhousing.model.StatisticsWard;

import java.util.List;

/**
 * Created by smith / 10.05.2015.
 */
public class StatisticsResponse {

    private List<StatisticsWard> wards;

    public List<StatisticsWard> getWards() {
        return wards;
    }

    public void setWards(List<StatisticsWard> wards) {
        this.wards = wards;
    }
}
