package com.example.traincrossinglocator;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SignUp extends AppCompatActivity {
LinearLayout l1,l2,l3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l3=findViewById(R.id.l3);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,RegWithGoogle.class);
                startActivity(intent);
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,RegWithMob.class);
                startActivity(intent);
            }
        });
    }
}
