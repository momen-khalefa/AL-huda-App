package com.alhud.alhudastore.pages;

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
import com.alhud.alhudastore.alreef.MainActivity_menu_alreef;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.myaccountp.change_name;
import com.alhud.alhudastore.redSlider.red_menu;
import com.alhud.alhudastore.riocafee.rio_menu;
import com.alhud.alhudastore.totaloil.MainActivity_menu_totaloil;
import com.alhud.alhudastore.totaloil.total_word;

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

public class total_oil  extends AppCompatActivity {

    String Json="";
    String[] oil_menu;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tota_oil);
        id=getIntent().getStringExtra("id");

        RelativeLayout oil_prod=findViewById(R.id.oil_prod);
        oil_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(total_oil.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView aro1=findViewById(R.id.aro1);
        aro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(total_oil.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout agents_oil=findViewById(R.id.agents_oil);
        agents_oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(total_oil.this, total_word.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        ImageView aro2=findViewById(R.id.aro2);
        aro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(total_oil.this, main.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        ImageView back12=findViewById(R.id.back12);
        back12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(total_oil.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        ImageView  facebook_total= findViewById(R.id.facebook_total);
        facebook_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/totalpalestine/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(total_oil.this, "Check internet connection", Toast.LENGTH_SHORT).show();
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
    public void getJson(){
        new Background().execute();
    }
    class Background extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://fuel.alhuda.ps/total_oil_menu.php/";
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
        oil_menu = new String[jsonArray.length()];
        String url="https://drive.google.com/uc?export=download&id=";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            oil_menu[i] =url+obj.getString("image");
        }
        Bundle b=new Bundle();
        b.putStringArray("array",oil_menu);
        b.putString("id",id);
        Intent i = new Intent(total_oil.this, MainActivity_menu_totaloil.class);
        i.putExtras(b);
        startActivity(i);

    }
}
