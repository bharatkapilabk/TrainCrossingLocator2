package com.example.traincrossinglocator;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOrCreate extends AppCompatActivity {
Button btn_login,btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_create);
        btn_login=findViewById(R.id.btn_login);
        btn_signup=findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginOrCreate.this,LogIn.class);
                startActivity(intent);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginOrCreate.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}
