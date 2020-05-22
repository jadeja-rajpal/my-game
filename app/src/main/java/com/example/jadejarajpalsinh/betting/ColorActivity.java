package com.example.jadejarajpalsinh.betting;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ColorActivity extends AppCompatActivity {


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
