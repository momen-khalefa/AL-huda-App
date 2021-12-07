package com.alhud.alhudastore.myaccountp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alhud.alhudastore.R;
import com.alhud.alhudastore.pages.getcard;
import com.alhud.alhudastore.pages.myAccount;

public class change_pass  extends AppCompatActivity {

    String pas1;
    String pas2;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_reset_pasword);
        id=getIntent().getStringExtra("id");
        ImageView back_ac_p=findViewById(R.id.back_ac_p);
        back_ac_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(change_pass.this, myAccount.class);
                startActivity(i);

            }
        });
        Button change_pass_btn=findViewById(R.id.change_pass_btn);
        change_pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView edtResetPass = findViewById(R.id.edtResetPass);
                TextView edtResetCPass = findViewById(R.id.edtResetCPass);
                pas1 = "" + edtResetPass.getText();
                pas2 = "" + edtResetCPass.getText();
                if(pas1.equals(pas2)){
                    if(pas1.length()>=8){
                        WebView myWebView = findViewById(R.id.reset_pass_web);
                        myWebView.loadUrl("https://fuel.alhuda.ps/update_pass_user.php?id="+id+"&password="+pas1);
                        Toast.makeText(change_pass.this, "Password changed Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(change_pass.this, myAccount.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(change_pass.this, "Password too Short", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(change_pass.this, "Password not match", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}