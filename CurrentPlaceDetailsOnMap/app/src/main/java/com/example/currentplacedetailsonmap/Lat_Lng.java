package com.example.currentplacedetailsonmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by akshay on 14/10/17.
 */

public class Lat_Lng {
    public Double latitude;
    public Double longitude;

    public Lat_Lng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Lat_Lng(){

    }


    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}