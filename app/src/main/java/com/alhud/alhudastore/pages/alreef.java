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
import com.alhud.alhudastore.lazmek.l_word;
import com.alhud.alhudastore.lazmek.menu;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.myaccountp.change_name;
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

public class alreef extends AppCompatActivity {

    String id;
    String Json="";
    String[] menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alreef);

        id=getIntent().getStringExtra("id");

        RelativeLayout loc_rio=findViewById(R.id.loc_rio);
        loc_rio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(alreef.this, com.alhud.alhudastore.alreef.alreef_word.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

        ImageView lar1=findViewById(R.id.lar1);
        lar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(alreef.this, com.alhud.alhudastore.alreef.alreef_word.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        RelativeLayout menu_rio=findViewById(R.id.menu_rio);
        menu_rio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(alreef.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView lar2=findViewById(R.id.lar2);
        lar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(alreef.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ImageView im10=findViewById(R.id.im10);
        im10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(alreef.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        ImageView  facebook_alreef= findViewById(R.id.facebook_alreef);
        facebook_alreef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/ReefRamallah/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(alreef.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView  insta_alreef= findViewById(R.id.insta_alreef);
        insta_alreef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.instagram.com/reefpalestine/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(alreef.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView  whatsapp_alreef= findViewById(R.id.whatsapp_alreef);
        whatsapp_alreef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://api.whatsapp.com/send?phone=970592030100&fbclid=IwAR1QeEMf4q8iWQVRXVrWCpBOi-zvzPhXSxw3ACiWxjycTqziOOlRoYVEopw";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(alreef.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void getJson(){
        new Background().execute();
    }
    class Background extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            json_url="https://fuel.alhuda.ps/al_reef_menu.php/";
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
        menu = new String[jsonArray.length()];
        String url="https://drive.google.com/uc?export=download&id=";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            menu[i] =url+obj.getString("image");
        }
        Bundle b=new Bundle();
        b.putStringArray("menu",menu);
        b.putString("id",id);
        Intent i = new Intent(alreef.this, MainActivity_menu_alreef.class);
        i.putExtras(b);
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
