package com.alhud.alhudastore.myaccountp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.myAccount;

public class change_name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_name);

        ImageView imBack13=findViewById(R.id.imBack13);
        imBack13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_name.this, myAccount.class);
                startActivity(i);

            }
        });
        Button change_name_bt=findViewById(R.id.change_name_bt);
        change_name_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_name.this, myAccount.class);
                startActivity(i);

            }
        });
    }
}