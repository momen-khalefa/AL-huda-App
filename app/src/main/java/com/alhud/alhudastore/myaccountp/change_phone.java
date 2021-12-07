package com.alhud.alhudastore.myaccountp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.myAccount;

public class change_phone  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_phone);

        ImageView imBack17=findViewById(R.id.imBack17);
        imBack17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_phone.this, myAccount.class);
                startActivity(i);

            }
        });
        Button change_phone_bt=findViewById(R.id.change_phone_bt);
        change_phone_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_phone.this, myAccount.class);
                startActivity(i);

            }
        });
    }
}
