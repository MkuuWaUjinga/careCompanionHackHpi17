package com.care.carecompanionpatientapp;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.jawbone.upplatformsdk.api.ApiManager;
import com.jawbone.upplatformsdk.api.response.OauthAccessTokenResponse;
import com.jawbone.upplatformsdk.oauth.OauthUtils;
import com.jawbone.upplatformsdk.oauth.OauthWebViewActivity;
import com.jawbone.upplatformsdk.utils.UpPlatformSdkConstants;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Main activity of the Hello Up test app, it makes the OAuth API
 * call and obtains the access token.
 */
public class JawBoneSetup {

    public static final String TAG = JawBoneSetup.class.getSimpleName();

    public static final int OAUTH_REQUEST_CODE = 25;

    // These are obtained after registering on Jawbone Developer Portal
    // Credentials used here are created for "Test-App1"
    public static final String CLIENT_ID = "_W1Vw3ksfpQ";
    private static final String CLIENT_SECRET = "ed46a27e5d3441317607bac4ea99de9617790637";

    // This has to be identical to the OAuth redirect url setup in Jawbone Developer Portal
    public static final String OAUTH_CALLBACK_URL = "http://localhost/helloup?";

    private List<UpPlatformSdkConstants.UpPlatformAuthScope> authScope;



    public static int getRandomPulse(int lastPulse, int maxDiff){
        if(lastPulse <50||lastPulse>120){ return 91;}
        return (int)((double)lastPulse+(double)maxDiff-(2.0*((double)maxDiff)*Math.random()));
    }
}