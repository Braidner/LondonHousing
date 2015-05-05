package org.braidner.londonhousing.entity;

/**
 * Created by smith / 04.05.2015.
 */
public class Statistic {
    private String crime;
    private String transport;
    private String housing;

    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getHousing() {
        return housing;
    }

    public void setHousing(String housing) {
        this.housing = housing;
    }
}
