package com.example.jadejarajpalsinh.betting;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jadejarajpalsinh.betting.Model.UserInformation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;



public class MainActivity  extends AppCompatActivity {
    EditText editTextPhone, editTextCode,etPassword1,etPassword2;
    private FirebaseDatabase userInfoDatabse;
    private DatabaseReference reference;
    ProgressBar progressBar;


    FirebaseAuth mAuth;

    private FirebaseUser currentUser;
    String codeSent;
    FirebaseUser user;


    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        userInfoDatabse = FirebaseDatabase.getInstance();
        reference=userInfoDatabse.getReference("Users");
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();

        editTextCode = findViewById(R.id.editTextCode);
        editTextPhone = findViewById(R.id.editTextPhone);
        progressBar=findViewById(R.id.progressbar);


        findViewById(R.id.buttonGetVerificationCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode();
            }
        });


        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifySignInCode();
            }
        });
    }

    private void verifySignInCode(){
        String code = editTextCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);

    }

     DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("users");



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user;
                            final String uid;
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            uid=user.getUid();
                            final String number = editTextPhone.getText().toString();
                            final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("users");
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    boolean found=false;

                                    for(DataSnapshot data: dataSnapshot.getChildren())
                                    {
                                       if(data.getKey().equals(uid)){
                                           found=true;
                                           break; }

                                    }
                                    if(found){
                                          Toast.makeText(getApplicationContext(),
                                                  " WELCOME AGAIN", Toast.LENGTH_LONG).show();
                                         Intent t =new Intent(MainActivity.this, ProActivity.class);
                                          t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(t);//h
                                    }
                                    else{
                                        FirebaseUser user;
                                        final String number = editTextPhone.getText().toString();
                                        final String uid;
                                        user=FirebaseAuth.getInstance().getCurrentUser();
                                        uid=user.getUid();
                                        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("users").child(uid);
                                        DatabaseReference mynumber=databaseReference.child("number");

                                        DatabaseReference mybalance=databaseReference.child("balance");
                                        mynumber.setValue(number);
                                        mybalance.setValue(0);

                                        Toast.makeText(getApplicationContext(),
                                                " WELCOME !! NEW USER", Toast.LENGTH_LONG).show();
                                        Intent t =new Intent(MainActivity.this, ProActivity.class);
                                        t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(t);
                                    }



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                           Toast.makeText(getApplicationContext(),
                                    "Login Successfull" +number, Toast.LENGTH_LONG).show();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(),
                                        "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void sendVerificationCode(){

        String phone;
        phone = editTextPhone.getText().toString();

        if(phone.isEmpty()){
            editTextPhone.setError("Phone number is required");
            editTextPhone.requestFocus();
            return;
        }

        if(phone.length() < 10 ){
            editTextPhone.setError("Please enter a valid phone");
            editTextPhone.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(

                "+91" + phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }



    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {


        }


        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(getApplicationContext(),
                    "wait ", Toast.LENGTH_LONG).show();

            codeSent = s;
            progressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent t =new Intent(this, ProActivity.class);
            t.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(t);//here you can open new activity


        }
    }
}
