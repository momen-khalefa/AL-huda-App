package com.alhud.alhudastore.totaloil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.total_oil;

public class total_word extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_word);
        id = getIntent().getStringExtra("id");

        ImageView back_total_word=findViewById(R.id.back_total_word);
        back_total_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(total_word.this, total_oil.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
