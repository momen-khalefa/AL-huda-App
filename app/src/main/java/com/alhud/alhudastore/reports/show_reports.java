package com.alhud.alhudastore.reports;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.MainActivity;
import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.pages.myAccount;
import com.alhud.alhudastore.staiton.MainActivity_staiton;

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

public class show_reports extends AppCompatActivity {
    String Json="";
    String Json1="";
    String id;
    String[] staiton_name;
    String[] time;
    String[] date;
    String[] amount;
    String card_id;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actv_show);
        card_id = getIntent().getStringExtra("cardId");
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        getAccount();
        RelativeLayout btn_result=findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTr();

            }
        });
        ImageView image_back=findViewById(R.id.imBack25);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(show_reports.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

    }
    public void getAccount(){
        new Background().execute();
    }
    class Background extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            String d=getIntent().getStringExtra("cardId");
            json_url="https://fuel.alhuda.ps/cards_view_details.php?id="+d;
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
                loadInto(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadInto(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        JSONObject obj = jsonArray.getJSONObject(0);
        String id = obj.getString("id");
        String car_number = obj.getString("car_number");
        String total = obj.getString("total");
        String fuel_in_lit = obj.getString("fuel_in_lit");
        String Active = obj.getString("Active");
        String fuel_in_nis = obj.getString("fuel_in_nis");

        TextView d1 = findViewById(R.id.d1);
        d1.setText(car_number);
        TextView d2 = findViewById(R.id.d2);
        d2.setText(card_id);
        TextView d3 = findViewById(R.id.d3);
        d3.setText(total);
        TextView d4 = findViewById(R.id.d4);
        if(Active.equals("2")){
            d4.setText("Active");
        }
        else {
            d4.setText("Not Active");
        }
        TextView d5 = findViewById(R.id.d5);
        d5.setText(fuel_in_lit);
        TextView d6 = findViewById(R.id.d6);
        d6.setText(fuel_in_nis);
    }

    public void getTr(){
        new Background_tr().execute();
    }
    class Background_tr extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            String id=getIntent().getStringExtra("cardId");
            json_url="https://fuel.alhuda.ps/transaction.php?id="+id;
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
                loadIntotr(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadIntotr(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        staiton_name = new String[jsonArray.length()];
        time= new String[jsonArray.length()];
        date= new String[jsonArray.length()];
        amount = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            staiton_name [i] =obj.getString("stnname");
            time[i] =obj.getString("ttime ");
            date[i] =obj.getString("tdate");
            amount[i] =obj.getString("total");
        }
        Bundle b=new Bundle();
        b.putStringArray("staiton_name",staiton_name);
        b.putStringArray("time",time);
        b.putStringArray("date",date);
        b.putStringArray("amount",amount);
        Intent i = new Intent(show_reports.this, reports_table.class);
        i.putExtras(b);
        //i.putExtra("id",id);
        startActivity(i);
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
}
