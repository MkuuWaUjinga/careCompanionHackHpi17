package com.care.carecompanionpatientapp;

import android.widget.TextView;
import android.widget.Toast;

import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.List;

/**
 * Created by tfied on 17.06.2017.
 */

public class IBeaconListenerSetup  {
    private ProximityManager proximityManager;
    TextView beacon_tv;
    TextView beaconDist_tv;
    MainActivity mainActivity;
    StatusChangeListener statusChangedListener;
    public interface StatusChangeListener{
        public void statusChanged(boolean beacon_connected);
    }

    public IBeaconListenerSetup(MainActivity mainActivity, StatusChangeListener statusChangedListener){
        this.proximityManager =  ProximityManagerFactory.create(mainActivity);
        beacon_tv = (TextView) mainActivity.findViewById(R.id.tv_beaconStat);
        beaconDist_tv = (TextView) mainActivity.findViewById(R.id.tv_beaconDist);
        this.mainActivity =mainActivity;
        this.statusChangedListener = statusChangedListener;
        setupListener();

    }
    public void setupListener(){
        proximityManager.setIBeaconListener(new IBeaconListener() {
            @Override
            public void onIBeaconDiscovered(IBeaconDevice iBeacon, IBeaconRegion region) {
                //Beacon discovered
                if (isRightBeacon(iBeacon)) {
                    setBeaconStat(true);
                }
            }

            @Override
            public void onIBeaconsUpdated(List<IBeaconDevice> iBeacons, IBeaconRegion region) {
                //Beacons updated
                double d = -1;
                for (int i = 0; i < iBeacons.size(); i++) {
                    if (isRightBeacon(iBeacons.get(i))) {
                        d = iBeacons.get(i).getDistance();
                    }
                }

                beaconDist_tv.setText("\n\nDistance to efdJ:  " + d + " \n\n Nr Devices Connected: " + iBeacons.size());
            }


            @Override
            public void onIBeaconLost(IBeaconDevice iBeacon, IBeaconRegion region) {
                //Beacon lost
                if (isRightBeacon(iBeacon)) {
                    setBeaconStat(false);
                    beaconDist_tv.setText("");
                }
            }
        });
    }
    public boolean isRightBeacon(IBeaconDevice b){

        return b!= null && b.getUniqueId()!=null && b.getUniqueId().equals("efdJ");
    }

    protected void onStartActivity() {

        startScanning();
    }


    protected void onStopActivity() {
        proximityManager.stopScanning();

    }


    protected void onDestroyActivity() {
        proximityManager.disconnect();
        proximityManager = null;

    }


    private void startScanning() {
        proximityManager.connect(new OnServiceReadyListener() {
            @Override
            public void onServiceReady() {
                proximityManager.startScanning();
            }
        });
    }

    public void setBeaconStat(boolean connected){
        statusChangedListener.statusChanged(connected);
        if(beacon_tv!=null){
        beacon_tv.setText("Beacon connected: "+connected);}
    }
}
