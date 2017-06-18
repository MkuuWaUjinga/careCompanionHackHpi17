package com.care.carecompanionpatientapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by tfied on 18.06.2017.
 */

public class Geolocation {
    LocationManager locationManager;
    Activity a;
    public Geolocation(Activity a) {
        locationManager = (LocationManager)
                a.getSystemService(Context.LOCATION_SERVICE);
       this.a =a;
    }



    public Location getLastBestLocation() {
       try {
           Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
           Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
           long GPSLocationTime = 0;
           if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }

           long NetLocationTime = 0;

           if (null != locationNet) {
               NetLocationTime = locationNet.getTime();
           }

           if ( 0 < GPSLocationTime - NetLocationTime ) {
               return locationGPS;
           }
           else {
               return locationNet;
           }
       }catch(SecurityException e){
           e.printStackTrace();
       }
       return null;
    }
}
