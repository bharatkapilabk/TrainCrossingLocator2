package com.example.traincrossinglocator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Data extends AppCompatActivity {

    TextView tv1,tv2;
    TrainAdapter trainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               String value=dataSnapshot.getValue().toString();
//                tv1.setText(value);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        databaseReference1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
//                Long value1=dataSnapshot1.getValue(Long.class);
//                String s=value1.toString();
//                tv2.setText(s);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
    }
}
