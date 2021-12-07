package com.alhud.alhudastore.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.MainActivity;
import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;

public class about_co extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words);
        id = getIntent().getStringExtra("id");

        ImageView imBack70=findViewById(R.id.imBack70);
        imBack70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(about_co.this, info_page.class);
                i.putExtra("id",id);
                startActivity(i);
            }
        });

    }
}
