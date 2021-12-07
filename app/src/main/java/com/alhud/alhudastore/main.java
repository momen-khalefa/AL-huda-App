package com.alhud.alhudastore;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.objectes.staiton;
import com.alhud.alhudastore.pages.alreef;
import com.alhud.alhudastore.pages.getcard;
import com.alhud.alhudastore.pages.info_page;
import com.alhud.alhudastore.pages.myAccount;
import com.alhud.alhudastore.pages.total_oil;
import com.alhud.alhudastore.pages.user_view;
import com.alhud.alhudastore.price.MainActivity_price;
import com.alhud.alhudastore.reports.MainActivity_report;
import com.alhud.alhudastore.reports.show_reports;
import com.alhud.alhudastore.riocafee.MainActivity_rio;
import com.alhud.alhudastore.riocafee.rio_menu;
import com.alhud.alhudastore.staiton.MainActivity_staiton;
import com.onesignal.OneSignal;

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

public class main extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=getIntent().getStringExtra("id");

        TextView log=findViewById(R.id.myAccount_text);
        if( id.equals("0")){
            log.setText(R.string.login);
        }

        RelativeLayout rvSnack=findViewById(R.id.rvSnack);
        rvSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                    if(id.equals("0")==false) {
                        Intent i = new Intent(main.this, user_view.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }
                    else{
                        Intent i = new Intent(main.this, MainActivity.class);
                        i.putExtra("id", id);
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RelativeLayout rvShareChat=findViewById(R.id.rvShareChat);
        rvShareChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, com.alhud.alhudastore.lazmek.menu.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RelativeLayout rvprice=findViewById(R.id.rvprice);
        rvprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, com.alhud.alhudastore.kasho.kasho_menu.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout getcard=findViewById(R.id.getcard);
        getcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                        Intent i = new Intent(main.this, com.alhud.alhudastore.redSlider.red_menu.class);
                        i.putExtra("id", id);
                        startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout rvIstaitoin=findViewById(R.id.rvIstaitoin);
        rvIstaitoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, com.alhud.alhudastore.staiton.stiton_menu.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout rvRio=findViewById(R.id.rvRio);
        rvRio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, rio_menu.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout alreef_rv=findViewById(R.id.alreef_rv);
        alreef_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, alreef.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout totaloil=findViewById(R.id.totaloil);
        totaloil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, total_oil.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        RelativeLayout info_rv=findViewById(R.id.info_rv);
        info_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(main.this, info_page.class);
                    i.putExtra("id",id);
                    startActivity(i);
                }
                else{
                    Toast.makeText(main.this, "Check internet connection", Toast.LENGTH_SHORT).show();
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
    }


