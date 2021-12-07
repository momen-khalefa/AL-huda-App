package com.alhud.alhudastore.kasho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.lazmek.l_word;
import com.alhud.alhudastore.lazmek.menu;

public class kasho_word  extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kasho_word);
        id = getIntent().getStringExtra("id");

        ImageView kasho_back=findViewById(R.id.kasho_back);
        kasho_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(kasho_word.this, kasho_menu.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });
    }
}
