package com.alhud.alhudastore.redSlider;

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
import com.alhud.alhudastore.pages.alreef;
import com.alhud.alhudastore.riocafee.MainActivity_menu_rio;
import com.alhud.alhudastore.riocafee.rio_menu;

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

public class red_menu extends AppCompatActivity {
    String Json="";
    String id;
    String[] webchrz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_menu);
        id =getIntent().getStringExtra("id");


        RelativeLayout location_rv=findViewById(R.id.Namevr);
        location_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(red_menu.this, red_word.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        ImageView imArrow3=findViewById(R.id.a1);
        imArrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(red_menu.this, red_word.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        RelativeLayout RLmenu=findViewById(R.id.emailvr);
        RLmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(red_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView imArrow4=findViewById(R.id.a2);
        imArrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(red_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView imBack=findViewById(R.id.imBack14);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(red_menu.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        ImageView  facebook_red= findViewById(R.id.facebook_red);
        facebook_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/RED-SuperMarket-232488630267272/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(red_menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
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
            json_url="https://fuel.alhuda.ps/red_menu.php/";
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
        webchrz = new String[jsonArray.length()];
        String url="https://drive.google.com/uc?export=download&id=";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            webchrz[i] =url+obj.getString("image");
        }
        Bundle b=new Bundle();
        b.putStringArray("array",webchrz);
        b.putString("id",id);
        Intent i = new Intent(red_menu.this, MainActivity_menu_rio.class);
        i.putExtras(b);
        startActivity(i);


    }
}