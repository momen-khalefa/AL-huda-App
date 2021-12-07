package com.alhud.alhudastore.lazmek;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;


public class l_word extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lazmk_word);
        id = getIntent().getStringExtra("id");

        ImageView lazmk_word_back=findViewById(R.id.lazmk_word_back);
        lazmk_word_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(l_word.this, menu.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
