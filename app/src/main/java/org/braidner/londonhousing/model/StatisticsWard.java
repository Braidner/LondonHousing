package org.braidner.londonhousing.model;

/**
 * Created by smith / 10.05.2015.
 */
public class StatisticsWard {

    private String wardName;

    private String code;

    private String population;

    private String housePrice;

    private String crimeRate;

    private String robberyRate;

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

    public String getCrimeRate() {
        return crimeRate;
    }

    public void setCrimeRate(String crimeRate) {
        this.crimeRate = crimeRate;
    }

    public String getRobberyRate() {
        return robberyRate;
    }

    public void setRobberyRate(String robberyRate) {
        this.robberyRate = robberyRate;
    }
}
