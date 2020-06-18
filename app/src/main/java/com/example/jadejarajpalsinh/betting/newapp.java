package com.example.jadejarajpalsinh.betting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class newapp extends AppCompatActivity {
    Button btnPlayGame1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newapp);
        btnPlayGame1=(Button)findViewById(R.id.btnPlayGame1);
        btnPlayGame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(newapp.this,newcoin

                        .class);
                startActivity(t);
            }
        });
    }

}
