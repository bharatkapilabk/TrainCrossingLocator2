package com.example.traincrossinglocator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegWithMob extends AppCompatActivity {
    EditText edt_phn,edt_otp;
    Button btn_generate,btn_verify;
    FirebaseAuth firebaseAuth;
    String codeSent;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_with_mob);
        edt_phn=findViewById(R.id.edt_phn);
        edt_otp=findViewById(R.id.edt_otp);
        btn_verify=findViewById(R.id.btn_verify);
        btn_generate=findViewById(R.id.btn_generate);
        firebaseAuth= FirebaseAuth.getInstance();


        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                codeSent=s;
            }
        };


        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();
            }
        });
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySignInCode();
            }
        });


    }


    private void sendVerificationCode() {
        String phoneNumber=edt_phn.getText().toString();
        if(phoneNumber.isEmpty()){
            edt_phn.setError("Phone Number is required");
            edt_phn.requestFocus();
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks);
        Toast.makeText(getApplicationContext(),"Getting code",Toast.LENGTH_LONG).show();
    }
    private void verifySignInCode() {
            String code=edt_otp.getText().toString();
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
            signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Verified",Toast.LENGTH_LONG).show();
                        } else {
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(getApplicationContext(),"Not Verified",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
