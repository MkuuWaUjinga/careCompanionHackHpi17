package com.care.carecompanionpatientapp;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by tfied on 18.06.2017.
 */

public class HTTPPostRequest {
    public HTTPPostRequest(){


    }



   public static void sendHttpRequest(final Activity a, final int beaconConnected, final int pulse, final double latitude, final double longitude){
       new Thread(){
           public void run() {
                final String s = sendHttpRequest("username=demo&password=abc123&", beaconConnected, pulse, latitude, longitude);
               System.out.println("HTTPRESULT>>>>" + s);
               a.runOnUiThread(new Runnable(){
                   public void run(){
                       Toast.makeText(a, "result: "+s, Toast.LENGTH_LONG).show();
                   }
               });

           }}.start();
    }

    public static String sendHttpRequest(String params, int beaconConnected, int pulse, double latitude, double longitude) {

        String result = "";
        try {
            URL url = new URL("https://og7h38hfi6.execute-api.us-east-1.amazonaws.com/dev/upload/create?beaconconnected="+beaconConnected+"&pulse="+pulse+"&latitude="+latitude+"&longitude="+longitude);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(((SSLSocketFactory) SSLSocketFactory.getDefault())); // Tell the URLConnection to use a SocketFactory from our SSLContext
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.println(params);
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()), 8192);
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);
            }
            in.close();
            //} catch (IOException e) {
        } catch (IOException e) {
            result = e.toString();
            e.printStackTrace();
        }
        return result;
    }
}
