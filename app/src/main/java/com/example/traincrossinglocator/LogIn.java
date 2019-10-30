package com.example.traincrossinglocator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LogIn extends AppCompatActivity {
Button btn_login;
EditText edt_mail,edt_password;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btn_login=findViewById(R.id.btn_login);
        edt_mail=findViewById(R.id.edt_mail);
        edt_password=findViewById(R.id.edt_password);
        mAuth= FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String email=edt_mail.getText().toString();
                    String password=edt_password.getText().toString();
                    login_user(email,password);
                }catch (IllegalArgumentException e) {
                    Toast.makeText(LogIn.this, "Enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void login_user(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("FLog", "signInWithEmail:success"+user);
                            Intent intent=new Intent(LogIn.this,Home.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FLog", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogIn.this, "Authentication failed...User Doesn't Exist",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
