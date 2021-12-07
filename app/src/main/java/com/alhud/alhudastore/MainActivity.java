package com.alhud.alhudastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alhud.alhudastore.pages.user_view;

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

public class MainActivity extends AppCompatActivity {
    String Json="";
    String result;
    String id;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_log_in);
        Button log_in=findViewById(R.id.btnlogin);
        TextView stauts=findViewById(R.id.tvForgotPass);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected() ) {
                    TextView error = findViewById(R.id.tvForgotPass);
                    error.setText("");
                    TextView email = findViewById(R.id.edtLogin);
                    TextView pas = findViewById(R.id.edtloginpassword);
                    id = "" + email.getText();
                    pass = "" + pas.getText();
                    check();
                    stauts.setText("invalid ID or Password ");
                }
                else{
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
        Button btnlogin2=findViewById(R.id.btnlogin2);
        btnlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    Intent i = new Intent(MainActivity.this, main.class);
                    i.putExtra("id","0");
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView web = findViewById(R.id.web);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "http://www.alhuda.ps/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView  intstagram= findViewById(R.id.insta);
        intstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.instagram.com/alhuda.group/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView  face= findViewById(R.id.facebook);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    String url = "https://www.facebook.com/Alhuda.Group.pal/";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Check internet connection", Toast.LENGTH_SHORT).show();
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
    public void check(){
        new Background().execute();
    }
    class Background extends AsyncTask<Void,Void,String> {

        String json_url;
        @Override
        protected void onPreExecute() {
            TextView email=findViewById(R.id.edtLogin);
            TextView pas=findViewById(R.id.edtloginpassword);
            String id= ""+email.getText();
            String pass= ""+pas.getText();
            json_url="https://fuel.alhuda.ps/log_in_t1.php?name="+id+"&pass="+pass;
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
            JSONObject obj = jsonArray.getJSONObject(0);
            result =obj.getString("staut");
            String id=obj.getString("id");

        if(result.equals("true")){
            Intent i = new Intent(MainActivity.this, user_view.class);
            i.putExtra("id",id);
            startActivity(i);
        }
        else{
            TextView error=findViewById(R.id.tvForgotPass);
            error.setText("invalid ID or Password ");

        }

    }

}