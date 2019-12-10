package com.example.traincrossinglocator;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LogIn extends AppCompatActivity {
Button btn_login;
EditText edt_mail,edt_password;
FirebaseAuth mAuth;
TextView resetpass;
ProgressBar p1;
    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        btn_login=findViewById(R.id.btn_login);
        edt_mail=findViewById(R.id.edt_mail);
        edt_password=findViewById(R.id.edt_password);
        mAuth= FirebaseAuth.getInstance();
        p1=findViewById(R.id.p1);
        resetpass=findViewById(R.id.resetpass);
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.sendPasswordResetEmail(edt_mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LogIn.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LogIn.this, "Failed to send mail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setVisibility(View.VISIBLE);
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

    public static boolean isValidEmail(CharSequence email)
    {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
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
                            Intent intent=new Intent(LogIn.this,NewHome.class);
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
