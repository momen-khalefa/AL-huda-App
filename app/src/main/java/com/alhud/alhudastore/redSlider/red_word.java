package com.alhud.alhudastore.redSlider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;


public class red_word extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.red_word);
        id = getIntent().getStringExtra("id");

        ImageView back_red_word=findViewById(R.id.back_red_word);
        back_red_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(red_word.this, red_menu.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
