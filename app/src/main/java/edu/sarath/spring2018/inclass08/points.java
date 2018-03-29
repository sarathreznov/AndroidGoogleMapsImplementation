package edu.sarath.spring2018.inclass08;

/**
 * Created by sarathreznov on 3/26/2018.
 */

public class points {
    private double latitude;
    private double longitude;

    public points(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "points{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
