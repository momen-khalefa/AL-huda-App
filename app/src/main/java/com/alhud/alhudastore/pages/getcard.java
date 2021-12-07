package com.alhud.alhudastore.pages;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;




public class getcard extends AppCompatActivity {

    String car_number;
    String balance;
    String type;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getcard);

        id=getIntent().getStringExtra("id");
        ImageView imBack20=findViewById(R.id.imBack20);
        imBack20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getcard.this, user_view.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
        Button requset=findViewById(R.id.requset);
        requset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    TextView car_number_get = findViewById(R.id.car_number_get);
                    TextView balance_get = findViewById(R.id.balance_get);
                    car_number = "" + car_number_get.getText();
                    balance = "" + balance_get.getText();
                    RadioButton disel_ch = findViewById(R.id.disel_ch);
                    RadioButton g98_ch = findViewById(R.id.g98_ch);
                    RadioButton g95_ch = findViewById(R.id.g95_ch);
                    type = null;
                    if (disel_ch.isChecked()) {
                        type = "1";
                    } else if (g98_ch.isChecked()) {
                        type = "3";
                    } else if (g95_ch.isChecked()) {
                        type = "2";
                    }
                    if (car_number == "") {
                        Toast.makeText(getcard.this, "Please Insert Car Number", Toast.LENGTH_SHORT).show();
                    } else {
                        if (type == null) {
                            Toast.makeText(getcard.this, "Please Choose Fuel Type ", Toast.LENGTH_SHORT).show();
                        } else {
                            if (balance == "") {
                                Toast.makeText(getcard.this, "Please Insert balance", Toast.LENGTH_SHORT).show();
                            } else {
                                WebView myWebView = findViewById(R.id.web);
                                myWebView.loadUrl("https://fuel.alhuda.ps/insertCard.php?id=" + id + "&car_number=" + car_number + "&fuel_type=" + type + "&balance=" + balance);
                                Toast.makeText(getcard.this, "done", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getcard.this, user_view.class);
                                i.putExtra("id", id);
                                startActivity(i);
                            }
                        }
                    }
                }
                else{
                    Toast.makeText(getcard.this, "Check Internet connection ", Toast.LENGTH_SHORT).show();
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
