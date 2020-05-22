package com.example.jadejarajpalsinh.betting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProActivity extends AppCompatActivity {
    Button Addmoney,Withdrawlmoney,gamecolor;
    TextView balance;
    DatabaseReference databaseReference;
    FirebaseUser user;


    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro);
        Withdrawlmoney=(Button)findViewById(R.id.Withdrawlmoney);
        Addmoney=(Button)findViewById(R.id.Addmoney);
        balance=(TextView) findViewById(R.id.balance);
        gamecolor=(Button)findViewById(R.id.gamecolor);

        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
        String balan=getIntent().getStringExtra("balance");
       balance.setText(balan);



      databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String bal = dataSnapshot.child(uid).child("balance").getValue().toString();
               balance.setText(bal);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Withdrawlmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ProActivity.this,WithdrawlActivity.class);
                startActivity(t);
            }
        });

        Addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ProActivity.this,AddmoneyActivity.class);
                startActivity(t);
            }
        });
        gamecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t=new Intent(ProActivity.this,ColorActivity.class);
                startActivity(t);
            }
        });


        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent t =new Intent(ProActivity.this, MainActivity.class);
                t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(t);
            }
        });
    }
}
