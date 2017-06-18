package com.care.carecompanionpatientapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by tfied on 17.06.2017.
 */

public class HTTPJSONPutRequest extends AsyncTask<String, Void, Boolean> {
    Context c;
    public HTTPJSONPutRequest(Context c){
        this.c =c;
    }
    @Override
    protected Boolean doInBackground(String... params) {
        InputStream inputStream = null;
        String result = "";

        try {
            // 1. create HttpClient
            org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
            // 2. make POST request to the given URL
            HttpPut httpPUT = new HttpPut("http://xxx.xx.x.xxx:xxxx/xxxxxxxy/webresources/net.xxxxxx.users/3");
            String json = "";
            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idUser","3");
            jsonObject.put("name","Mark");
            jsonObject.put("pass","1234");
            jsonObject.put("rol","554");
            jsonObject.put("usuario","mark");




            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPUT.setEntity(se);
            // 7. Set some headers to inform server about the type of the content
            httpPUT.setHeader("Accept", "application/json");
            httpPUT.setHeader("Content-type", "application/json");
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPUT);
            // 9. receive response as inputStream
            //                  inputStream = httpResponse.getEntity().getContent();
            //                  // 10. convert inputstream to string
            //                  if(inputStream != null)
            //                      result = convertInputStreamToString(inputStream);
            //                  else
            //                      result = "Did not work!";
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            return Boolean.FALSE;
        }

        return true;
    }
     protected void onPostExecute(Boolean result) {
         Toast.makeText(c, "Request Sended to Amazon: "+ result.booleanValue(), Toast.LENGTH_LONG).show();
    }

}