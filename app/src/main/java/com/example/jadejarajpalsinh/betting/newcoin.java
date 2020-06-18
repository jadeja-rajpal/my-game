package com.example.jadejarajpalsinh.betting;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class newcoin extends AppCompatActivity {
    Button joinhead , jointail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcoin);
        jointail=(Button)findViewById(R.id.jointail);
        joinhead=(Button)findViewById(R.id.joinhead);




        jointail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    final AlertDialog.Builder alert = new AlertDialog.Builder(newcoin.this);
                    View mView=getLayoutInflater().inflate(R.layout.sheet_layout_pay,null);
                    Button colorcancel=(Button)mView.findViewById(R.id.colorcancel);
                       final EditText  amountpay=(EditText)mView.findViewById(R.id.amountpay);


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
                            String amount = amountpay.getText().toString().trim();
                               if(amount.matches("") || (Integer.parseInt(amount) < 10)) {
                                   Toast.makeText(getApplicationContext(),
                                           "Please enter amount greater than 10 rupees", Toast.LENGTH_LONG).show();

                               }
                          else{
                                   Toast.makeText(getApplicationContext(),
                                           "Tail Done " + amount, Toast.LENGTH_LONG).show();
                                   alertDialog.dismiss();

                            }


                        }
                    });
                    alertDialog.show();
                    setFinishOnTouchOutside(false);


            }
        });
    }
}
