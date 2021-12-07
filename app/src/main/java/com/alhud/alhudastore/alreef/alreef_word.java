package com.alhud.alhudastore.alreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.alreef;


public class alreef_word extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alreef_word);
        id = getIntent().getStringExtra("id");

        ImageView back_reef_word=findViewById(R.id.back_reef_word);
        back_reef_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(alreef_word.this, alreef.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
