package in.techware.ladriver.model;

import com.google.android.gms.maps.model.LatLng;

public class PlaceBean extends BaseBean implements Comparable<PlaceBean> {

    private int id;
    private String address;
    private String latitude;
    private String longitude;
    private String name;
    private LatLng latLng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public double getdLatitude() {
        try {
            return Double.parseDouble(latitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double getDLatitude() {
        return getdLatitude();
    }

    public double getdLongitude() {
        try {
            return Double.parseDouble(longitude);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public double getDLongitude() {
        return getdLongitude();
    }

    @Override
    public int compareTo(PlaceBean other) {
        if (id == other.id) {
            return 0;
        } else if (id > other.id) {
            return 1;
        } else {
            return -1;
        }
    }
}
