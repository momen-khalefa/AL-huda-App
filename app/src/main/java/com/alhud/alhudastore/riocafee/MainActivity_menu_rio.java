package com.alhud.alhudastore.riocafee;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.price.MainActivity_price;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity_menu_rio extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_menu_rio adapterMenurio;
    String[] webchrz;
    String id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rio);


        Bundle b=this.getIntent().getExtras();
        webchrz=b.getStringArray("array");
        id=b.getString("id");
        recyclerView = findViewById(R.id.storiesListsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterMenurio = new Adapter_menu_rio(this,webchrz); // our adapter takes two string array
        recyclerView.setAdapter(adapterMenurio);
        ImageView im_back_menu=findViewById(R.id.im_back_menu);
        im_back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_menu_rio.this, main.class);
                i.putExtra("id", id);
                startActivity(i);

            }
        });


    }




}
