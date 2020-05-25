package com.example.jadejarajpalsinh.betting;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ColorActivity extends AppCompatActivity {

    private TextView retrieve;
    TextView gamecolorline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        gamecolorline=(TextView)findViewById(R.id.gamecolorline);
        gamecolorline.setSelected(true);
        Button Joingreensheet=findViewById(R.id.Joingreen);
        Button Joinred=findViewById(R.id.Joingred);
        TextView gamerule=findViewById(R.id.gamerule);
        retrieve = findViewById(R.id.retrieve);


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("timer");




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mytime = dataSnapshot.child("countdown").getValue().toString();
                retrieve.setText(mytime);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Joingreensheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox checkBoxcolor = (CheckBox)findViewById(R.id.checkboxcolor);
                if(checkBoxcolor.isChecked()){
                    final AlertDialog.Builder alert = new AlertDialog.Builder(ColorActivity.this);
                    View mView=getLayoutInflater().inflate(R.layout.color_sheet_layout,null);
                    Button colorcancel=(Button)mView.findViewById(R.id.colorcancel);
                    Button colorok=(Button)mView.findViewById(R.id.colorok);
                    alert.setView(mView);
                    final AlertDialog alertDialog=alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    colorcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                        }
                    });
                    colorok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),
                                    "Green Done ", Toast.LENGTH_LONG).show();
                            alertDialog.dismiss();


                        }
                    });
                    alertDialog.show();
                    setFinishOnTouchOutside(false);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Please agree to game rule ", Toast.LENGTH_SHORT).show();

                }

            }
        });
        Joinred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CheckBox checkBoxcolor = (CheckBox)findViewById(R.id.checkboxcolor);
                if(checkBoxcolor.isChecked()){
                    final AlertDialog.Builder alert = new AlertDialog.Builder(ColorActivity.this);
                    View mView=getLayoutInflater().inflate(R.layout.color_sheet_layout,null);
                    Button colorcancel=(Button)mView.findViewById(R.id.colorcancel);
                    Button colorok=(Button)mView.findViewById(R.id.colorok);
                    alert.setView(mView);
                    final AlertDialog alertDialog=alert.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    colorcancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();

                        }
                    });
                    colorok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),
                                    "Green Red ", Toast.LENGTH_LONG).show();
                            alertDialog.dismiss();


                        }
                    });
                    alertDialog.show();
                    setFinishOnTouchOutside(false);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Please agree to game rule", Toast.LENGTH_SHORT).show();

                }

            }
        });
        gamerule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ColorActivity.this);
                View mView=getLayoutInflater().inflate(R.layout.rule_activity,null);
                Button ruleok=(Button)mView.findViewById(R.id.ruleok);

                alert.setView(mView);
                final AlertDialog alertDialog=alert.create();
                alertDialog.setCanceledOnTouchOutside(false);
                ruleok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();
            }
        });



    }
}
