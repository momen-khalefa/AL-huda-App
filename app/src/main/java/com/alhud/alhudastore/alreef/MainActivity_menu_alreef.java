package com.alhud.alhudastore.alreef;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.riocafee.MainActivity_rio;
import com.google.android.material.navigation.NavigationView;

public class MainActivity_menu_alreef extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    Adapter_menu_alreef adapterMenurio;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alreef);



        Bundle b=this.getIntent().getExtras();
        String[] contents = b.getStringArray("menu");
        id=b.getString("id");

        recyclerView = findViewById(R.id.storiesListsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterMenurio = new Adapter_menu_alreef(this,contents); // our adapter takes two string array
        recyclerView.setAdapter(adapterMenurio);

        ImageView im8=findViewById(R.id.im8);
        im8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_menu_alreef.this, main.class);
                i.putExtra("id",id);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.home){
            Toast.makeText(this, "Home btn Clicked.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
