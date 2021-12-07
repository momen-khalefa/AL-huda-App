package com.alhud.alhudastore.staiton;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.main;
import com.alhud.alhudastore.price.MainActivity_price;
import com.google.android.material.navigation.NavigationView;

public class MainActivity_staiton extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    Adapter adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staiton);
        Bundle b=this.getIntent().getExtras();
        String[] staiton_name=b.getStringArray("staiton_name");
        String[] address=b.getStringArray("address");
        String[] longitude=b.getStringArray("longitude");
        String[] latitude=b.getStringArray("latitude");
        String[] phone=b.getStringArray("phone");
        String[] image=b.getStringArray("image");


        id=getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.storiesListsView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,staiton_name,address,longitude,latitude,phone,image); // our adapter takes two string array
        recyclerView.setAdapter(adapter);

        ImageView im5=findViewById(R.id.im5);
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity_staiton.this, main.class);
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
