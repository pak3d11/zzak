package com.zzak.kr.bikecluster.view.speedchek.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.compa.gsk.base.BaseFragment;
import com.zzak.kr.bikecluster.R;

public class FmtSpeedCheck extends BaseFragment {

    private TextView currentSpeed;
    private LocationManager locationManager;
    private LocationListener locationListener;
    double mySpeed, maxSpeed;

    @Override
    protected boolean useMainFragment() {
        return false;
    }

    @Override
    protected int inflateLayout() {

        return R.layout.fmt_speed_check;
    }

    @Override
    protected void viewFindById(View view) {

        currentSpeed = view.findViewById(R.id.current_speed);
    }

    @Override
    protected void viewSetting() {

        maxSpeed = mySpeed = 0;

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new SpeedoActionListener();


        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


    }

    private class SpeedoActionListener implements LocationListener {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {

            if (location != null) {

                mySpeed = location.getSpeed();

                if (mySpeed > maxSpeed) {
                    maxSpeed = mySpeed;
                }

                currentSpeed.setText("\nCurrent Speed : " + mySpeed + "km/h, Max Speed: " + maxSpeed + "km/h");
            }
        }
    }

}
