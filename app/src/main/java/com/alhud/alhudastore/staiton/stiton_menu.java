package com.alhud.alhudastore.staiton;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.MainActivity;
import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.price.MainActivity_price;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class stiton_menu extends AppCompatActivity {
    String id;
    String Json="";
    String[] prices;
    String Json1="";
    String[] staiton_name;
    String[] address;
    String[] longitude;
    String[] latitude;
    String[] phone;
    String[] image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fule_staiton);
        id =getIntent().getStringExtra("id");

        RelativeLayout fuel_p=findViewById(R.id.fuel_p);
        fuel_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView p_ar=findViewById(R.id.p_ar);
        p_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        RelativeLayout al_huda_st=findViewById(R.id.al_huda_st);
        al_huda_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getStaiton();
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ImageView h_ar=findViewById(R.id.h_ar);
        h_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isConnected()) {
                    getStaiton();
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView imBack_sta=findViewById(R.id.imBack_sta);
        imBack_sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(stiton_menu.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        ImageView  facebook_st= findViewById(R.id.facebook_st);
        facebook_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/Alhuda.Group.pal/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView  insta_st= findViewById(R.id.insta_st);
        insta_st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.instagram.com/alhuda.group/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(stiton_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    public void getStaiton(){
        new Background_st().execute();
    }
    class Background_st extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://fuel.alhuda.ps/staiton_view.php/";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url= new URL(json_url);
                HttpsURLConnection huc= (HttpsURLConnection) url.openConnection();
                InputStream isr= huc.getInputStream();
                BufferedReader br= new BufferedReader(new InputStreamReader(isr));
                StringBuilder sb= new StringBuilder();
                while((Json1= br.readLine()) != null){
                    sb.append(Json1+"\n");

                }
                br.close();
                isr.close();
                huc.disconnect();
                return sb.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String string) {

            try {
                loadIntoList(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadIntoList(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        staiton_name = new String[jsonArray.length()];
        address= new String[jsonArray.length()];
        longitude= new String[jsonArray.length()];
        latitude= new String[jsonArray.length()];
        phone= new String[jsonArray.length()];
        image= new String[jsonArray.length()];
        String url="https://drive.google.com/uc?export=download&id=";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            staiton_name [i] =obj.getString("staiton_name");
            address[i] =obj.getString("address");
            longitude[i] =obj.getString("longitude");
            latitude[i] =obj.getString("latitude");
            phone[i] =obj.getString("phone");
            image[i] =url+obj.getString("image");
        }
        Bundle b=new Bundle();
        b.putStringArray("staiton_name",staiton_name);
        b.putStringArray("address",address);
        b.putStringArray("longitude",longitude);
        b.putStringArray("latitude",latitude);
        b.putStringArray("phone",phone);
        b.putStringArray("image",image);
        Intent i = new Intent(stiton_menu.this, MainActivity_staiton.class);
        i.putExtras(b);
        i.putExtra("id",id);
        startActivity(i);

    }
    public void getJson(){
        new Background().execute();
    }
    class Background extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://fuel.alhuda.ps/fuel_price.php/";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url= new URL(json_url);
                HttpsURLConnection huc= (HttpsURLConnection) url.openConnection();
                InputStream isr= huc.getInputStream();
                BufferedReader br= new BufferedReader(new InputStreamReader(isr));
                StringBuilder sb= new StringBuilder();
                while((Json= br.readLine()) != null){
                    sb.append(Json+"\n");

                }
                br.close();
                isr.close();
                huc.disconnect();
                return sb.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String string) {

            try {
                loadIntoListView(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        prices = new String[jsonArray.length()];
        String url="https://drive.google.com/uc?export=download&id=";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            prices[i] =url+obj.getString("image");
        }
        Bundle b=new Bundle();
        b.putStringArray("price",prices);
        Intent i = new Intent(stiton_menu.this, MainActivity_price.class);
        i.putExtras(b);
        i.putExtra("id",id);
        startActivity(i);

    }

}
