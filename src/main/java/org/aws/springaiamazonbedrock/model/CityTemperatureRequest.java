package org.aws.springaiamazonbedrock.model;

public class CityTemperatureRequest {
    private String city;

    public CityTemperatureRequest() {
    }

    public CityTemperatureRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityTemperatureRequest{" +
                "city='" + city + '\'' +
                '}';
    }
}
