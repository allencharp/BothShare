package com.example.util;

import com.example.listener.LocationRequestLisener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class LocationManagerUtil {
	
	
	public Location getLocation(Context mContext) {  
		Location location = null;
		double latitude = 0d;
		double longitude = 0d;
		try {
			LocationManager locationManager = (LocationManager) mContext
	                .getSystemService(Context.LOCATION_SERVICE);

	        // getting GPS status
	        Boolean isGPSEnabled = locationManager
	                .isProviderEnabled(LocationManager.GPS_PROVIDER);

	        // getting network status
	        Boolean isNetworkEnabled = locationManager
	                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

	        if (!isGPSEnabled && !isNetworkEnabled) {
	            location = null;
	        } else {
	            Boolean canGetLocation = true;
	            if (isNetworkEnabled) {
	                locationManager.requestLocationUpdates(
	                        LocationManager.NETWORK_PROVIDER,
	                        0,
	                        0, new LocationRequestLisener());
	                Log.d("Network", "Network Enabled");
	                if (locationManager != null) {
	                    location = locationManager
	                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	                    if (location != null) {
	                        latitude = location.getLatitude();
	                        longitude = location.getLongitude();
	                    }
	                }
	            }
	            // if GPS Enabled get lat/long using GPS Services
	            if (isGPSEnabled) {
	                if (location == null) {
	                    locationManager.requestLocationUpdates(
	                            LocationManager.GPS_PROVIDER,
	                            0,
	                            0, new LocationRequestLisener());
	                    Log.d("GPS", "GPS Enabled");
	                    if (locationManager != null) {
	                        location = locationManager
	                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	                        if (location != null) {
	                            latitude = location.getLatitude();
	                            longitude = location.getLongitude();
	                        }
	                    }
	                }
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return location;
	}
}
