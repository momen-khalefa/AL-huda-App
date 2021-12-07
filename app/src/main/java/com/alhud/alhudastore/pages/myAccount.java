package com.alhud.alhudastore.pages;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.MainActivity;
import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.myaccountp.change_email;
import com.alhud.alhudastore.myaccountp.change_name;
import com.alhud.alhudastore.myaccountp.change_pass;
import com.alhud.alhudastore.myaccountp.change_phone;

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

public class myAccount  extends AppCompatActivity {

    String Json;
    String id;
    String name;
    String phone;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);

/*
        ImageView a1=findViewById(R.id.a1);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, change_name.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        ImageView a3=findViewById(R.id.a3);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, change_phone.class);
                i.putExtra("id",id);
                i.putExtra("phone",phone);
                startActivity(i);

            }
        });*/

        ImageView a4=findViewById(R.id.a4);
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, change_pass.class);
                i.putExtra("id",id);
                i.putExtra("password",password);
                startActivity(i);

            }
        });
        RelativeLayout log_out_rv=findViewById(R.id.log_out_rv);
        log_out_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, main.class);
                i.putExtra("id","0");
                startActivity(i);

            }
        });

        ImageView a5=findViewById(R.id.a5);
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, MainActivity.class);
                i.putExtra("id","0");
                startActivity(i);

            }
        });
        getAccount();
        ImageView imBack07=findViewById(R.id.imBack07);
        imBack07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(myAccount.this, user_view.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
    public void getAccount(){
        new Background_ac().execute();
    }
    class Background_ac extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            String d=getIntent().getStringExtra("id");
            json_url="https://fuel.alhuda.ps/getuser_detalis.php?name="+d;
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
        id=obj.getString("id");
        name=obj.getString("name");
        phone=obj.getString("phone");
        password=obj.getString("password");
        TextView Name_show=findViewById(R.id.name_show);
        Name_show.setText(name);
        TextView phone_show=findViewById(R.id.phone_show);
        phone_show.setText(phone);

    }
}
