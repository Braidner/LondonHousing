package org.braidner.londonhousing.model;

/**
 * Created by smith / 10.05.2015.
 */
public class StatisticsWard {

    private String wardName;

    private String code;

    private String population;

    private String housePrice;

    private Float crimeRate;

    private Float transportRate;

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }

    public Float getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(Float crimeRate) {
        this.crimeRate = crimeRate;
    }

    public Float getTransportRate() {
        return transportRate;
    }

    public void setTransportRate(Float transportRate) {
        this.transportRate = transportRate;
    }
}
