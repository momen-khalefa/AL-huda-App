package com.alhud.alhudastore.lazmek;

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
import com.alhud.alhudastore.redSlider.red_menu;
import com.alhud.alhudastore.riocafee.MainActivity_menu_rio;

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

public class menu extends AppCompatActivity {
    String Json="";
    String id;
    String[] webchrz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazmk_menu);
        id =getIntent().getStringExtra("id");


        RelativeLayout lazmek_word=findViewById(R.id.lazmek_word);
        lazmek_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, l_word.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });
        ImageView row_l_menu=findViewById(R.id.row_l_menu);
        row_l_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, l_word.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        RelativeLayout lazmek_menu=findViewById(R.id.lazmek_menu);
        lazmek_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ImageView lazmk_menu_row=findViewById(R.id.lazmk_menu_row);
        lazmk_menu_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getJson();
                }
                else{
                    Toast.makeText(menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView lazmk_back=findViewById(R.id.lazmk_back);
        lazmk_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(menu.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        ImageView  facebook_lazmek= findViewById(R.id.facebook_lazmek);
        facebook_lazmek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/Lazmack-459478217497815";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(menu.this, "Check internet connection", Toast.LENGTH_SHORT).show();
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
            json_url="https://fuel.alhuda.ps/lazmek_menu.php/";
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
        Intent i = new Intent(menu.this, MainActivity_menu_rio.class);
        i.putExtras(b);
        startActivity(i);


    }
}
