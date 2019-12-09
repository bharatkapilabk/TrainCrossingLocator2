package com.example.traincrossinglocator;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegWithGoogle extends AppCompatActivity {
Button btn_signup;
EditText edt_mail,edt_password;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_with_google);

        btn_signup=findViewById(R.id.btn_signup);
        edt_mail=findViewById(R.id.edt_mail);
        edt_password=findViewById(R.id.edt_password);
        mAuth=FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String email=edt_mail.getText().toString();
                    String password=edt_password.getText().toString();
                    register_user(email,password);
                }catch (IllegalArgumentException e) {
                    Toast.makeText(RegWithGoogle.this, "Enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void register_user(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FLog", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent=new Intent(RegWithGoogle.this,Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FLog", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegWithGoogle.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
