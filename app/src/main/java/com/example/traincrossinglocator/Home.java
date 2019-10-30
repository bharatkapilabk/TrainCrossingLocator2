package com.example.traincrossinglocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

public class Home extends AppCompatActivity {
RecyclerView r1;
LinearLayoutManager l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        r1=findViewById(R.id.r1);
        l1=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        r1.setLayoutManager(l1);
        TrainAdapter trainAdapter=new TrainAdapter(this);
        r1.setAdapter(trainAdapter);
            }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}

