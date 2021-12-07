package com.alhud.alhudastore.myaccountp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.myAccount;

public class change_email   extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_change_email);

        ImageView imBack14=findViewById(R.id.imBack14);
        imBack14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_email.this, myAccount.class);
                startActivity(i);

            }
        });
        Button change_email_bt=findViewById(R.id.change_email_bt);
        change_email_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_email.this, myAccount.class);
                startActivity(i);

            }
        });
    }
}