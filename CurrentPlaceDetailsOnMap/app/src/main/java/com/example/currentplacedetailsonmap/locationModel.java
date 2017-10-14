package com.example.currentplacedetailsonmap;

import android.location.Address;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by akshay on 14/10/17.
 */

public class locationModel {

    String markLocation;
    Lat_Lng latlng;

    public locationModel(){
    }

    public void setMarkLocation(String markLocation) {
        this.markLocation = markLocation;
    }

    public void setLatlng(double lat, double lon) {
        this.latlng = new Lat_Lng(lat, lon);
    }

    public String getMarkLocation() {

        return markLocation;
    }

    public Lat_Lng getLatlng() {
        return latlng;
    }

}
