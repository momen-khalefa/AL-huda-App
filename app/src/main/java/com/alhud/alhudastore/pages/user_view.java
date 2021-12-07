package com.alhud.alhudastore.pages;

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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.alhud.alhudastore.MainActivity;
import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.reports.MainActivity_report;

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

public class user_view  extends AppCompatActivity {

    String id;
    String Json;
    String Json1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_view);
        id = getIntent().getStringExtra("id");

        RelativeLayout my_acc=findViewById(R.id.my_acc);
        my_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                    Intent i = new Intent(user_view.this, myAccount.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView a_ac=findViewById(R.id.a_ac);
        a_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                    Intent i = new Intent(user_view.this, myAccount.class);
                    i.putExtra("id",id);
                    startActivity(i);                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        RelativeLayout full_repo=findViewById(R.id.full_repo);
        full_repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getreports();
                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView a_ful=findViewById(R.id.a_ful);
        a_ful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getreports();
                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        RelativeLayout get_cardd=findViewById(R.id.get_cardd);
        get_cardd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                        Intent i = new Intent(user_view.this, com.alhud.alhudastore.pages.getcard.class);
                        i.putExtra("id", id);
                        startActivity(i);
                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ImageView a_card=findViewById(R.id.a_card);
        a_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                    Intent i = new Intent(user_view.this, com.alhud.alhudastore.pages.getcard.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(user_view.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ImageView back_303=findViewById(R.id.back_303);
        back_303.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(user_view.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
    public void getreports(){
        new Background_rep().execute();
    }
    class Background_rep extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {

            json_url="https://fuel.alhuda.ps/cards_view_id.php?id="+id;
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
                loadInto_report(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadInto_report(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        String[] reports = new String[jsonArray.length()];
        String[] names = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            reports [i] =obj.getString("id");
            names [i] =obj.getString("name");
        }
        Bundle b=new Bundle();
        b.putStringArray("reports",reports);
        b.putStringArray("names",names);
        Intent i = new Intent(user_view.this, MainActivity_report.class);
        i.putExtras(b);
        i.putExtra("id",id);
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
