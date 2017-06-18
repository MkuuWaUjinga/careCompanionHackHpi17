package com.care.carecompanionpatientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jawbone.upplatformsdk.api.ApiManager;
import com.jawbone.upplatformsdk.api.response.OauthAccessTokenResponse;
import com.jawbone.upplatformsdk.oauth.OauthUtils;
import com.jawbone.upplatformsdk.oauth.OauthWebViewActivity;
import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;
import com.kontakt.sdk.android.ble.connection.OnServiceReadyListener;
import com.kontakt.sdk.android.ble.manager.ProximityManager;
import com.kontakt.sdk.android.ble.manager.ProximityManagerFactory;
import com.kontakt.sdk.android.ble.manager.listeners.IBeaconListener;
import com.kontakt.sdk.android.ble.manager.listeners.simple.SimpleIBeaconListener;
import com.kontakt.sdk.android.common.KontaktSDK;
import com.kontakt.sdk.android.common.profile.IBeaconDevice;
import com.kontakt.sdk.android.common.profile.IBeaconRegion;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


import static com.care.carecompanionpatientapp.JawBoneSetup.CLIENT_ID;
import static com.care.carecompanionpatientapp.JawBoneSetup.OAUTH_REQUEST_CODE;
import static com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants.CLIENT_SECRET;

public class MainActivity extends AppCompatActivity {
    TextView fitbit_tv;
    View layout ;
    IBeaconListenerSetup iBeaconHandler;
    private List<UpPlatformSdkConstants.UpPlatformAuthScope> authScope;
    int lastPulse = 70;
    Geolocation geolocation;
    int beacon_connected;
    double latitude = 0;
    double longitude = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        KontaktSDK.initialize("YOUR API KEY");
        layout = findViewById(R.id.linear_layout);

        fitbit_tv = (TextView) findViewById(R.id.tv_fitbitConnStat);


        geolocation = new Geolocation(this);
        iBeaconHandler = new IBeaconListenerSetup(this, new IBeaconListenerSetup.StatusChangeListener() {
            @Override
            public void statusChanged(boolean beacon_connected) {

                int c = (beacon_connected? Color.parseColor("#00e676") : Color.parseColor("#f44336"));
                layout.setBackgroundColor(c);
                System.out.println("RESULT>>>>"+"Beacon_Status_changed");
                MainActivity.this.beacon_connected = (beacon_connected ? 1 :0 );

                longitude = 0;
                latitude = 0;
                if(!beacon_connected){
                    Location l = geolocation.getLastBestLocation();
                    latitude = l.getLatitude();
                    longitude = l.getLongitude();
                    System.out.println("long: "+longitude+" | Lat: "+latitude);
                }
                HTTPPostRequest.sendHttpRequest(MainActivity.this,MainActivity.this.beacon_connected, lastPulse, latitude, longitude);


            }
        });
        new Thread (){
            public void run(){
                while(true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lastPulse = JawBoneSetup.getRandomPulse(lastPulse, 10);
                    HTTPPostRequest.sendHttpRequest(MainActivity.this,beacon_connected, lastPulse, latitude, longitude);
                }

            }
        }.start();
        for(int i=0;i<100;i++){
          //  System.out.println("Pulse: "+JawBoneSetup.getRandomPulse(140,10));
        }
         new HTTPPostRequest();
/*
        authScope  = new ArrayList<UpPlatformSdkConstants.UpPlatformAuthScope>();
        authScope.add(UpPlatformSdkConstants.UpPlatformAuthScope.ALL);

        Button oAuthAuthorizeButton = (Button) findViewById(R.id.btn_openLink);
        oAuthAuthorizeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntentForWebView();
                startActivityForResult(intent, OAUTH_REQUEST_CODE);
            }
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        iBeaconHandler.onStartActivity();
    }

    @Override
    protected void onStop() {
        iBeaconHandler.onStopActivity();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        iBeaconHandler.onDestroyActivity();
        super.onDestroy();
    }
    public void setFitbitStat(boolean connected){
        fitbit_tv.setText("Fitbit connected: "+connected);
    }


    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {
            if (requestCode == OAUTH_REQUEST_CODE) {
                String code = data.getStringExtra(UpPlatformSdkConstants.ACCESS_CODE);
                if (code != null) {
                    //first clear older accessToken, if it exists..
                    ApiManager.getRequestInterceptor().clearAccessToken();

                    ApiManager.getRestApiInterface().getAccessToken(
                            CLIENT_ID,
                            CLIENT_SECRET,
                            code,
                            accessTokenRequestListener);
                }
            }
        }

    }

    private Callback accessTokenRequestListener = new Callback<OauthAccessTokenResponse>() {
        @Override
        public void success(OauthAccessTokenResponse result, Response response) {

            if (result.access_token != null) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(UpPlatformSdkConstants.UP_PLATFORM_ACCESS_TOKEN, result.access_token);
                editor.putString(UpPlatformSdkConstants.UP_PLATFORM_REFRESH_TOKEN, result.refresh_token);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra(CLIENT_SECRET, CLIENT_SECRET);
                startActivity(intent);

                Log.e(JawBoneSetup.TAG, "accessToken:" + result.access_token);
            } else {
                Log.e(JawBoneSetup.TAG, "accessToken not returned by Oauth call, exiting...");
            }
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Log.e(JawBoneSetup.TAG, "failed to get accessToken:" + retrofitError.getMessage());
        }
    };

    private Intent getIntentForWebView() {
        Uri.Builder builder = OauthUtils.setOauthParameters(CLIENT_ID, JawBoneSetup.OAUTH_CALLBACK_URL, authScope);

        Intent intent = new Intent(OauthWebViewActivity.class.getName());
        intent.putExtra(UpPlatformSdkConstants.AUTH_URI, builder.build());
        return intent;
    }
    */
}
