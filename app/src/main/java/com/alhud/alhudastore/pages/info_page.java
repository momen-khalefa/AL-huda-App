package com.alhud.alhudastore.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.myaccountp.change_name;

public class info_page extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        id = getIntent().getStringExtra("id");

        RelativeLayout about_rv=findViewById(R.id.about_rv);
        about_rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info_page.this, about_co.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        ImageView ar1=findViewById(R.id.ar1);
        ar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info_page.this, about_co.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

        RelativeLayout emailvv=findViewById(R.id.emailvv);
        emailvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setType("plain/text")
                        .setData(Uri.parse("info@alhuda.ps"))
                        .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"info@alhuda.ps"});
                startActivity(intent);
            }
        });

        ImageView ar2=findViewById(R.id.ar2);
        ar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW)
                        .setType("plain/text")
                        .setData(Uri.parse("info@alhuda.ps"))
                        .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{"info@alhuda.ps"});
                startActivity(intent);

            }
        });

        RelativeLayout websitevr=findViewById(R.id.websitevr);
        websitevr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.alhuda.ps/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        ImageView ar3=findViewById(R.id.ar3);
        ar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.alhuda.ps/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });


        RelativeLayout phonerl=findViewById(R.id.phonerl);
        phonerl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0592978425";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" +number));
                startActivity(intent);
            }
        });

        ImageView ar4=findViewById(R.id.ar4);
        ar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "0592978425";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" +number));
                startActivity(intent);

            }
        });

        ImageView imBack25=findViewById(R.id.imBack25);
        imBack25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(info_page.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
